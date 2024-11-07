package utp.edu.codekion.finanzas.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utp.edu.codekion.finanzas.model.TipoTransaccion;
import utp.edu.codekion.finanzas.repository.TipoTransaccionRepository;
import utp.edu.codekion.finanzas.service.impl.TipoTransaccionServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TipoTransaccionServiceTest {


    @Mock
    private TipoTransaccionRepository tipoTransaccionRepository;

    @InjectMocks
    private TipoTransaccionServiceImpl tipoTransaccionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByIdReturnsTipoTransaccionWhenFound() {
        TipoTransaccion tipoTransaccion = new TipoTransaccion();
        when(tipoTransaccionRepository.findById(1)).thenReturn(Optional.of(tipoTransaccion));

        TipoTransaccion result = tipoTransaccionService.findById(1);

        assertNotNull(result);
        assertEquals(tipoTransaccion, result);
        verify(tipoTransaccionRepository, times(1)).findById(1);
    }

    @Test
    void findByIdReturnsNullWhenNotFound() {
        when(tipoTransaccionRepository.findById(1)).thenReturn(Optional.empty());

        TipoTransaccion result = tipoTransaccionService.findById(1);

        assertNull(result);
        verify(tipoTransaccionRepository, times(1)).findById(1);
    }

}