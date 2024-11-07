package utp.edu.codekion.finanzas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utp.edu.codekion.finanzas.model.*;
import utp.edu.codekion.finanzas.model.dto.CuentaBancariaResponseDto;
import utp.edu.codekion.finanzas.model.dto.CuentaRequestDto;
import utp.edu.codekion.finanzas.service.IService.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cuentas-bancarias")
@RequiredArgsConstructor
public class CuentasBancariasController {

    private final ICuentasBancariasService cuentasBancariasService;
    private final IUsuarioService usuarioService;
    private final ITipoCuentaService tipoCuentaService;
    private final ITipoTarjetaService tipoTarjetaService;
    private final IBancoService bancoService;

    //Listamos cuentas por id del usuario
    @GetMapping("/listar/usuario/{id}")
    public ResponseEntity<?> listarCuentasPorUsuario(@PathVariable("id") int id_usuario) {
        List<Cuenta> listaCuentas = cuentasBancariasService.listarCuentasPorUsuario(id_usuario);

        Map<String, Object> response = new HashMap<>();

        //Creamos el dto
        List<CuentaBancariaResponseDto> listaCuentasResponse = new ArrayList<>();

        //Iteramos la lista de cuentas
        for (Cuenta cuenta : listaCuentas) {
            CuentaBancariaResponseDto cuentaResponse = setDatosCuentaBancariaResponseDto(cuenta);
            listaCuentasResponse.add(cuentaResponse);
        }

        response.put("cuentas", listaCuentasResponse);
        response.put("message", "Cuentas listadas correctamente");
        return ResponseEntity.ok(response);

    }

    //Creamos la cuenta bancaria
    @PostMapping("/crear")
    public ResponseEntity<?> crearCuentaBancaria(@RequestBody CuentaRequestDto cuenta) {
        Map<String, Object> response = new HashMap<>();
        //Buscamos las entidades relacionadas
        Usuario usuario = usuarioService.findById(cuenta.getId_usuario());
        Banco banco = bancoService.findById(cuenta.getId_banco());
        TipoCuenta tipoCuenta = tipoCuentaService.findById(cuenta.getId_tipo_cuenta());
        TipoTarjeta tipoTarjeta = tipoTarjetaService.findById(cuenta.getId_tipo_tarjeta());
        //Verificamos que los 4 objetos no sean nulos
        if (usuario == null || banco == null || tipoCuenta == null || tipoTarjeta == null) {
            response.put("message", "No se encontraron los datos necesarios");
            return ResponseEntity.badRequest().body(response);
        }

        Cuenta cuentaGuardada = Cuenta.builder()
                .usuario(usuario)
                .banco(banco)
                .numCuenta(cuenta.getNumero_cuenta())
                .numCuentaInterbancario(cuenta.getNumero_cuenta_interbancario())
                .tipoCuenta(tipoCuenta)
                .numTarjeta(cuenta.getNumero_tarjeta())
                .tipoTarjeta(tipoTarjeta)
                .build();

        Cuenta cuentaSave = cuentasBancariasService.guardarCuenta(cuentaGuardada);

        response.put("cuenta", cuentaSave);
        response.put("message", "Cuenta creada correctamente");
        return ResponseEntity.ok(response);
    }

    private static CuentaBancariaResponseDto setDatosCuentaBancariaResponseDto(Cuenta cuenta) {
        CuentaBancariaResponseDto cuentaResponse = new CuentaBancariaResponseDto();
        cuentaResponse.setId(cuenta.getId());
        cuentaResponse.setNombre_usuario(cuenta.getUsuario().getNombres());
        cuentaResponse.setNombre_banco(cuenta.getBanco().getNombre());
        cuentaResponse.setNumero_cuenta(cuenta.getNumCuenta());
        cuentaResponse.setNumero_cuenta_interbancario(cuenta.getNumCuentaInterbancario());
        cuentaResponse.setTipo_cuenta(cuenta.getTipoCuenta().getDescripcion());
        cuentaResponse.setNumero_tarjeta(cuenta.getNumTarjeta());
        cuentaResponse.setTipo_tarjeta(cuenta.getTipoTarjeta().getDescripcion());
        return cuentaResponse;
    }


}