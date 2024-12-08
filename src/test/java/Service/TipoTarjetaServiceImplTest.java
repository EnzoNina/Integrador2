package Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utp.edu.codekion.finanzas.model.TipoTarjeta;
import utp.edu.codekion.finanzas.repository.TipoTarjetaRepository;
import utp.edu.codekion.finanzas.service.impl.TipoTarjetaServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;





public class TipoTarjetaServiceImplTest {

    @InjectMocks
    private TipoTarjetaServiceImpl tipoTarjetaService;

    @Mock
    private TipoTarjetaRepository tipoTarjetaRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById_Found() {
        TipoTarjeta tipoTarjeta = new TipoTarjeta();
        tipoTarjeta.setId(1);
        when(tipoTarjetaRepository.findById(1)).thenReturn(Optional.of(tipoTarjeta));

        TipoTarjeta result = tipoTarjetaService.findById(1);
        assertEquals(1, result.getId());
    }

    @Test
    public void testFindById_NotFound() {
        when(tipoTarjetaRepository.findById(1)).thenReturn(Optional.empty());

        TipoTarjeta result = tipoTarjetaService.findById(1);
        assertNull(result);
    }

    @Test
    public void testFindAll() {
        TipoTarjeta tipoTarjeta1 = new TipoTarjeta();
        tipoTarjeta1.setId(1);
        TipoTarjeta tipoTarjeta2 = new TipoTarjeta();
        tipoTarjeta2.setId(2);
        List<TipoTarjeta> tipoTarjetas = Arrays.asList(tipoTarjeta1, tipoTarjeta2);
        when(tipoTarjetaRepository.findAll()).thenReturn(tipoTarjetas);

        List<TipoTarjeta> result = tipoTarjetaService.findAll();
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
    }
}