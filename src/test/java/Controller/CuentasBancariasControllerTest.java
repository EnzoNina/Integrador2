package Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import utp.edu.codekion.finanzas.controller.CuentasBancariasController;
import utp.edu.codekion.finanzas.model.*;
import utp.edu.codekion.finanzas.model.dto.CuentaRequestDto;
import utp.edu.codekion.finanzas.service.IService.*;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CuentasBancariasControllerTest {

    @InjectMocks
    private CuentasBancariasController cuentasBancariasController;

    @Mock
    private ICuentasBancariasService cuentasBancariasService;

    @Mock
    private IUsuarioService usuarioService;

    @Mock
    private ITipoCuentaService tipoCuentaService;

    @Mock
    private ITipoTarjetaService tipoTarjetaService;

    @Mock
    private IBancoService bancoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testCrearCuentaBancaria() {
        CuentaRequestDto cuentaRequestDto = new CuentaRequestDto();
        cuentaRequestDto.setId_usuario(1);
        cuentaRequestDto.setId_banco(1);
        cuentaRequestDto.setId_tipo_cuenta(1);
        cuentaRequestDto.setId_tipo_tarjeta(1);

        Usuario usuario = new Usuario();
        Banco banco = new Banco();
        TipoCuenta tipoCuenta = new TipoCuenta();
        TipoTarjeta tipoTarjeta = new TipoTarjeta();

        when(usuarioService.findById(cuentaRequestDto.getId_usuario())).thenReturn(usuario);
        when(bancoService.findById(cuentaRequestDto.getId_banco())).thenReturn(banco);
        when(tipoCuentaService.findById(cuentaRequestDto.getId_tipo_cuenta())).thenReturn(tipoCuenta);
        when(tipoTarjetaService.findById(cuentaRequestDto.getId_tipo_tarjeta())).thenReturn(tipoTarjeta);

        Cuenta cuentaGuardada = new Cuenta();
        when(cuentasBancariasService.guardarCuenta(any(Cuenta.class))).thenReturn(cuentaGuardada);

        ResponseEntity<?> response = cuentasBancariasController.crearCuentaBancaria(cuentaRequestDto);

        assertEquals(200, response.getStatusCodeValue());
        verify(cuentasBancariasService, times(1)).guardarCuenta(any(Cuenta.class));
    }

    @Test
    public void testCrearCuentaBancariaDatosFaltantes() {
        CuentaRequestDto cuentaRequestDto = new CuentaRequestDto();
        cuentaRequestDto.setId_usuario(1);
        cuentaRequestDto.setId_banco(1);
        cuentaRequestDto.setId_tipo_cuenta(1);
        cuentaRequestDto.setId_tipo_tarjeta(1);

        when(usuarioService.findById(cuentaRequestDto.getId_usuario())).thenReturn(null);

        ResponseEntity<?> response = cuentasBancariasController.crearCuentaBancaria(cuentaRequestDto);

        assertEquals(400, response.getStatusCodeValue());
        verify(cuentasBancariasService, never()).guardarCuenta(any(Cuenta.class));
    }
}