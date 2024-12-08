package Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utp.edu.codekion.finanzas.model.Cuenta;
import utp.edu.codekion.finanzas.repository.CuentaBancariasRepository;
import utp.edu.codekion.finanzas.service.impl.CuentasBancariasServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CuentasBancariasServiceImplTest {

    private CuentasBancariasServiceImpl cuentasBancariasService;
    private CuentaBancariasRepository cuentaBancariasRepository;

    @BeforeEach
    void setUp() {
        cuentaBancariasRepository = mock(CuentaBancariasRepository.class);
        cuentasBancariasService = new CuentasBancariasServiceImpl(cuentaBancariasRepository);
    }

    @Test
    void listarCuentasPorUsuario_returnsCuentasForGivenUsuario() {
        List<Cuenta> cuentas = List.of(new Cuenta(), new Cuenta());
        when(cuentaBancariasRepository.listarCuentasPorUsuario(1)).thenReturn(cuentas);

        List<Cuenta> result = cuentasBancariasService.listarCuentasPorUsuario(1);

        assertEquals(2, result.size());
        verify(cuentaBancariasRepository, times(1)).listarCuentasPorUsuario(1);
    }

    @Test
    void listarCuentasPorUsuario_returnsEmptyListWhenNoCuentas() {
        when(cuentaBancariasRepository.listarCuentasPorUsuario(1)).thenReturn(List.of());

        List<Cuenta> result = cuentasBancariasService.listarCuentasPorUsuario(1);

        assertTrue(result.isEmpty());
        verify(cuentaBancariasRepository, times(1)).listarCuentasPorUsuario(1);
    }

    @Test
    void obtenerCuentaPorId_returnsCuentaWhenFound() {
        Cuenta cuenta = new Cuenta();
        when(cuentaBancariasRepository.findById(1)).thenReturn(Optional.of(cuenta));

        Cuenta result = cuentasBancariasService.obtenerCuentaPorId(1);

        assertNotNull(result);
        assertEquals(cuenta, result);
        verify(cuentaBancariasRepository, times(1)).findById(1);
    }

    @Test
    void obtenerCuentaPorId_returnsNullWhenNotFound() {
        when(cuentaBancariasRepository.findById(1)).thenReturn(Optional.empty());

        Cuenta result = cuentasBancariasService.obtenerCuentaPorId(1);

        assertNull(result);
        verify(cuentaBancariasRepository, times(1)).findById(1);
    }

    @Test
    void guardarCuenta_savesAndReturnsCuenta() {
        Cuenta cuenta = new Cuenta();
        when(cuentaBancariasRepository.save(cuenta)).thenReturn(cuenta);

        Cuenta result = cuentasBancariasService.guardarCuenta(cuenta);

        assertNotNull(result);
        assertEquals(cuenta, result);
        verify(cuentaBancariasRepository, times(1)).save(cuenta);
    }
}