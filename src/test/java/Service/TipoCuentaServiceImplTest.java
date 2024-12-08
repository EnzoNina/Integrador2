package Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utp.edu.codekion.finanzas.model.TipoCuenta;
import utp.edu.codekion.finanzas.repository.TipoCuentaRepository;
import utp.edu.codekion.finanzas.service.impl.TipoCuentaServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;





public class TipoCuentaServiceImplTest {

    @Mock
    private TipoCuentaRepository tipoCuentaRepository;

    @InjectMocks
    private TipoCuentaServiceImpl tipoCuentaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        TipoCuenta tipoCuenta = new TipoCuenta();
        tipoCuenta.setId(1);
        when(tipoCuentaRepository.findById(1)).thenReturn(Optional.of(tipoCuenta));

        TipoCuenta result = tipoCuentaService.findById(1);
        assertEquals(1, result.getId());
    }

    @Test
    public void testFindByIdNotFound() {
        when(tipoCuentaRepository.findById(1)).thenReturn(Optional.empty());

        TipoCuenta result = tipoCuentaService.findById(1);
        assertNull(result);
    }

    @Test
    public void testFindAll() {
        TipoCuenta tipoCuenta1 = new TipoCuenta();
        tipoCuenta1.setId(1);
        TipoCuenta tipoCuenta2 = new TipoCuenta();
        tipoCuenta2.setId(2);
        List<TipoCuenta> tipoCuentas = Arrays.asList(tipoCuenta1, tipoCuenta2);
        when(tipoCuentaRepository.findAll()).thenReturn(tipoCuentas);

        List<TipoCuenta> result = tipoCuentaService.findAll();
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
    }
}