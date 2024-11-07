package utp.edu.codekion.finanzas.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

public class TransaccionesServiceTest {

    @Mock
    private TransaccionRepository transaccionRepository;

    @InjectMocks
    private TransaccionesServiceImpl transaccionesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllReturnsAllTransacciones() {
        Transacciones transaccion1 = new Transacciones();
        Transacciones transaccion2 = new Transacciones();
        when(transaccionRepository.findAll()).thenReturn(Arrays.asList(transaccion1, transaccion2));

        List<Transacciones> result = transaccionesService.findAll();

        assertEquals(2, result.size());
        verify(transaccionRepository, times(1)).findAll();
    }

    @Test
    void findByUsuarioReturnsTransacciones() {
        Usuario usuario = new Usuario();
        Transacciones transaccion1 = new Transacciones();
        Transacciones transaccion2 = new Transacciones();
        when(transaccionRepository.findByUsuario(usuario)).thenReturn(Arrays.asList(transaccion1, transaccion2));

        List<Transacciones> result = transaccionesService.findByUsuario(usuario);

        assertEquals(2, result.size());
        verify(transaccionRepository, times(1)).findByUsuario(usuario);
    }

    @Test
    void findByIdReturnsTransaccionWhenFound() {
        Transacciones transaccion = new Transacciones();
        when(transaccionRepository.findById(1)).thenReturn(Optional.of(transaccion));

        Transacciones result = transaccionesService.findById(1);

        assertNotNull(result);
        assertEquals(transaccion, result);
        verify(transaccionRepository, times(1)).findById(1);
    }

    @Test
    void findByIdReturnsNullWhenNotFound() {
        when(transaccionRepository.findById(1)).thenReturn(Optional.empty());

        Transacciones result = transaccionesService.findById(1);

        assertNull(result);
        verify(transaccionRepository, times(1)).findById(1);
    }

    @Test
    void savePersistsTransaccion() {
        Transacciones transaccion = new Transacciones();
        when(transaccionRepository.save(transaccion)).thenReturn(transaccion);

        Transacciones result = transaccionesService.save(transaccion);

        assertNotNull(result);
        assertEquals(transaccion, result);
        verify(transaccionRepository, times(1)).save(transaccion);
    }

    @Test
    void deleteRemovesTransaccion() {
        Transacciones transaccion = new Transacciones();
        when(transaccionRepository.findById(1)).thenReturn(Optional.of(transaccion));

        transaccionesService.delete(1);

        verify(transaccionRepository, times(1)).delete(transaccion);
    }

    @Test
    void sumarTransaccionesPorCategoriaAndUsuarioReturnsSum() {
        Usuario usuario = new Usuario();
        when(transaccionRepository.sumarTransaccionesPorCategoriaAndUsuario(1, usuario)).thenReturn(BigDecimal.TEN);

        BigDecimal result = transaccionesService.sumarTransaccionesPorCategoriaAndUsuario(1, usuario);

        assertEquals(BigDecimal.TEN, result);
        verify(transaccionRepository, times(1)).sumarTransaccionesPorCategoriaAndUsuario(1, usuario);
    }

    @Test
    void balanceTotalReturnsBalance() {
        when(transaccionRepository.balanceTotal(1)).thenReturn(BigDecimal.TEN);

        BigDecimal result = transaccionesService.balanceTotal(1);

        assertEquals(BigDecimal.TEN, result);
        verify(transaccionRepository, times(1)).balanceTotal(1);
    }

    @Test
    void ingresosPorMesReturnsIngresos() {
        IngresoMesDto ingresoMesDto = new IngresoMesDto();
        when(transaccionRepository.ingresosPorMes(1)).thenReturn(Arrays.asList(ingresoMesDto));

        List<IngresoMesDto> result = transaccionesService.ingresosPorMes(1);

        assertEquals(1, result.size());
        verify(transaccionRepository, times(1)).ingresosPorMes(1);
    }

    @Test
    void gastosPorMesReturnsGastos() {
        IngresoMesDto gastoMesDto = new IngresoMesDto();
        when(transaccionRepository.gastosPorMes(1)).thenReturn(Arrays.asList(gastoMesDto));

        List<IngresoMesDto> result = transaccionesService.gastosPorMes(1);

        assertEquals(1, result.size());
        verify(transaccionRepository, times(1)).gastosPorMes(1);
    }

    @Test
    void transaccionesRecientesReturnsTransacciones() {
        Transacciones transaccion = new Transacciones();
        when(transaccionRepository.transaccionesRecientes(1)).thenReturn(Arrays.asList(transaccion));

        List<Transacciones> result = transaccionesService.transaccionesRecientes(1);

        assertEquals(1, result.size());
        verify(transaccionRepository, times(1)).transaccionesRecientes(1);
    }

    @Test
    void gastosPorCategoriaReturnsGastos() {
        CategoriaGastoDto categoriaGastoDto = new CategoriaGastoDto();
        when(transaccionRepository.gastosPorCategoria(1)).thenReturn(Arrays.asList(categoriaGastoDto));

        List<CategoriaGastoDto> result = transaccionesService.gastosPorCategoria(1);

        assertEquals(1, result.size());
        verify(transaccionRepository, times(1)).gastosPorCategoria(1);
    }

    @Test
    void findByFechaTransaccionBetweenReturnsTransacciones() {
        Transacciones transaccion = new Transacciones();
        LocalDate startDate = LocalDate.now().minusDays(10);
        LocalDate endDate = LocalDate.now();
        when(transaccionRepository.findByFechaTransaccionBetween(startDate, endDate)).thenReturn(Arrays.asList(transaccion));

        List<Transacciones> result = transaccionesService.findByFechaTransaccionBetween(startDate, endDate);

        assertEquals(1, result.size());
        verify(transaccionRepository, times(1)).findByFechaTransaccionBetween(startDate, endDate);
    }

    @Test
    void totalIngresosEntreFechasReturnsTotalIngresos() {
        LocalDate startDate = LocalDate.now().minusDays(10);
        LocalDate endDate = LocalDate.now();
        when(transaccionRepository.totalIngresosEntreFechas(startDate, endDate,1)).thenReturn(BigDecimal.TEN);

        BigDecimal result = transaccionesService.totalIngresosEntreFechas(startDate, endDate,1);

        assertEquals(BigDecimal.TEN, result);
        verify(transaccionRepository, times(1)).totalIngresosEntreFechas(startDate, endDate,1);
    }

    @Test
    void totalEgresosEntreFechasReturnsTotalEgresos() {
        LocalDate startDate = LocalDate.now().minusDays(10);
        LocalDate endDate = LocalDate.now();
        when(transaccionRepository.totalEgresosEntreFechas(startDate, endDate,1)).thenReturn(BigDecimal.TEN);

        BigDecimal result = transaccionesService.totalEgresosEntreFechas(startDate, endDate,1);

        assertEquals(BigDecimal.TEN, result);
        verify(transaccionRepository, times(1)).totalEgresosEntreFechas(startDate, endDate,1);
    }

}