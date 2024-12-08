package Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utp.edu.codekion.finanzas.model.TipoTransaccion;
import utp.edu.codekion.finanzas.repository.TipoTransaccionRepository;
import utp.edu.codekion.finanzas.service.impl.TipoTransaccionServiceImpl;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class TipoTransaccionServiceImplTest {

    @InjectMocks
    private TipoTransaccionServiceImpl tipoTransaccionService;

    @Mock
    private TipoTransaccionRepository tipoTransaccionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById_Found() {
        TipoTransaccion tipoTransaccion = new TipoTransaccion();
        tipoTransaccion.setId(1);
        when(tipoTransaccionRepository.findById(1)).thenReturn(Optional.of(tipoTransaccion));

        TipoTransaccion result = tipoTransaccionService.findById(1);

        assertEquals(tipoTransaccion, result);
    }

    @Test
    public void testFindById_NotFound() {
        when(tipoTransaccionRepository.findById(1)).thenReturn(Optional.empty());

        TipoTransaccion result = tipoTransaccionService.findById(1);

        assertNull(result);
    }
}