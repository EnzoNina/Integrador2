package Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utp.edu.codekion.finanzas.model.Cuenta;
import utp.edu.codekion.finanzas.model.Transacciones;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.dto.CategoriaGastoDto;
import utp.edu.codekion.finanzas.model.dto.IngresoMesDto;
import utp.edu.codekion.finanzas.repository.TransaccionRepository;
import utp.edu.codekion.finanzas.service.impl.TransaccionesServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransaccionesServiceImplTest {

    @Mock
    private TransaccionRepository transaccionRepository;

    @InjectMocks
    private TransaccionesServiceImpl transaccionesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Transacciones> transaccionesList = Arrays.asList(new Transacciones(), new Transacciones());
        when(transaccionRepository.findAll()).thenReturn(transaccionesList);

        List<Transacciones> result = transaccionesService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    void testFindByUsuario() {
        Usuario usuario = new Usuario();
        List<Transacciones> transaccionesList = Arrays.asList(new Transacciones(), new Transacciones());
        when(transaccionRepository.findByUsuario(usuario)).thenReturn(transaccionesList);

        List<Transacciones> result = transaccionesService.findByUsuario(usuario);
        assertEquals(2, result.size());
    }

    @Test
    void testFindById() {
        Transacciones transaccion = new Transacciones();
        when(transaccionRepository.findById(1)).thenReturn(Optional.of(transaccion));

        Transacciones result = transaccionesService.findById(1);
        assertNotNull(result);
    }

    @Test
    void testSave() {
        Transacciones transaccion = new Transacciones();
        when(transaccionRepository.save(transaccion)).thenReturn(transaccion);

        Transacciones result = transaccionesService.save(transaccion);
        assertNotNull(result);
    }

    @Test
    void testDelete() {
        Transacciones transaccion = new Transacciones();
        when(transaccionRepository.findById(1)).thenReturn(Optional.of(transaccion));
        doNothing().when(transaccionRepository).delete(transaccion);

        transaccionesService.delete(1);
        verify(transaccionRepository, times(1)).delete(transaccion);
    }

    @Test
    void testSumarTransaccionesPorCategoriaAndUsuario() {
        Usuario usuario = new Usuario();
        when(transaccionRepository.sumarTransaccionesPorCategoriaAndUsuario(1, usuario)).thenReturn(BigDecimal.TEN);

        BigDecimal result = transaccionesService.sumarTransaccionesPorCategoriaAndUsuario(1, usuario);
        assertEquals(BigDecimal.TEN, result);
    }

    @Test
    void testBalanceTotal() {
        when(transaccionRepository.balanceTotal(1, 1)).thenReturn(BigDecimal.TEN);

        BigDecimal result = transaccionesService.balanceTotal(1, 1);
        assertEquals(BigDecimal.TEN, result);
    }

    @Test
    void testIngresosPorMes() {
        List<IngresoMesDto> ingresosList = Arrays.asList(new IngresoMesDto(), new IngresoMesDto());
        when(transaccionRepository.ingresosPorMes(1)).thenReturn(ingresosList);

        List<IngresoMesDto> result = transaccionesService.ingresosPorMes(1);
        assertEquals(2, result.size());
    }

    @Test
    void testGastosPorMes() {
        List<IngresoMesDto> gastosList = Arrays.asList(new IngresoMesDto(), new IngresoMesDto());
        when(transaccionRepository.gastosPorMes(1)).thenReturn(gastosList);

        List<IngresoMesDto> result = transaccionesService.gastosPorMes(1);
        assertEquals(2, result.size());
    }

    @Test
    void testTransaccionesRecientes() {
        List<Transacciones> transaccionesList = Arrays.asList(new Transacciones(), new Transacciones());
        when(transaccionRepository.transaccionesRecientes(1)).thenReturn(transaccionesList);

        List<Transacciones> result = transaccionesService.transaccionesRecientes(1);
        assertEquals(2, result.size());
    }

    @Test
    void testGastosPorCategoria() {
        List<CategoriaGastoDto> categoriaGastoList = Arrays.asList(new CategoriaGastoDto(), new CategoriaGastoDto());
        when(transaccionRepository.gastosPorCategoria(1)).thenReturn(categoriaGastoList);

        List<CategoriaGastoDto> result = transaccionesService.gastosPorCategoria(1);
        assertEquals(2, result.size());
    }

    @Test
    void testFindByFechaTransaccionBetween() {
        List<Transacciones> transaccionesList = Arrays.asList(new Transacciones(), new Transacciones());
        when(transaccionRepository.findByFechaTransaccionBetween(LocalDate.now(), LocalDate.now()))
                .thenReturn(transaccionesList);

        List<Transacciones> result = transaccionesService.findByFechaTransaccionBetween(LocalDate.now(),
                LocalDate.now());
        assertEquals(2, result.size());
    }

    @Test
    void testTotalIngresosEntreFechas() {
        when(transaccionRepository.totalIngresosEntreFechas(LocalDate.now(), LocalDate.now(), 1))
                .thenReturn(BigDecimal.TEN);

        BigDecimal result = transaccionesService.totalIngresosEntreFechas(LocalDate.now(), LocalDate.now(), 1);
        assertEquals(BigDecimal.TEN, result);
    }

    @Test
    void testTotalEgresosEntreFechas() {
        when(transaccionRepository.totalEgresosEntreFechas(LocalDate.now(), LocalDate.now(), 1))
                .thenReturn(BigDecimal.TEN);

        BigDecimal result = transaccionesService.totalEgresosEntreFechas(LocalDate.now(), LocalDate.now(), 1);
        assertEquals(BigDecimal.TEN, result);
    }

    @Test
    void testFindByUsuarioAndCuenta() {
        Usuario usuario = new Usuario();
        Cuenta cuenta = new Cuenta();
        List<Transacciones> transaccionesList = Arrays.asList(new Transacciones(), new Transacciones());
        when(transaccionRepository.findByUsuarioAndCuenta(usuario, cuenta)).thenReturn(transaccionesList);

        List<Transacciones> result = transaccionesService.findByUsuarioAndCuenta(usuario, cuenta);
        assertEquals(2, result.size());
    }

    @Test
    void testIngresosPorMesAndCuenta() {
        List<IngresoMesDto> ingresosList = Arrays.asList(new IngresoMesDto(), new IngresoMesDto());
        when(transaccionRepository.ingresosPorMesAndCuenta(1, 1)).thenReturn(ingresosList);

        List<IngresoMesDto> result = transaccionesService.ingresosPorMesAndCuenta(1, 1);
        assertEquals(2, result.size());
    }

    @Test
    void testGastosPorMesAndCuenta() {
        List<IngresoMesDto> gastosList = Arrays.asList(new IngresoMesDto(), new IngresoMesDto());
        when(transaccionRepository.gastosPorMesAndCuenta(1, 1)).thenReturn(gastosList);

        List<IngresoMesDto> result = transaccionesService.gastosPorMesAndCuenta(1, 1);
        assertEquals(2, result.size());
    }

    @Test
    void testTransaccionesRecientesAndCuenta() {
        List<Transacciones> transaccionesList = Arrays.asList(new Transacciones(), new Transacciones());
        when(transaccionRepository.transaccionesRecientesAndCuenta(1, 1)).thenReturn(transaccionesList);

        List<Transacciones> result = transaccionesService.transaccionesRecientesAndCuenta(1, 1);
        assertEquals(2, result.size());
    }

    @Test
    void testGastosPorCategoriaAndCuenta() {
        List<CategoriaGastoDto> categoriaGastoList = Arrays.asList(new CategoriaGastoDto(), new CategoriaGastoDto());
        when(transaccionRepository.gastosPorCategoriaAndCuenta(1, 1)).thenReturn(categoriaGastoList);

        List<CategoriaGastoDto> result = transaccionesService.gastosPorCategoriaAndCuenta(1, 1);
        assertEquals(2, result.size());
    }
}