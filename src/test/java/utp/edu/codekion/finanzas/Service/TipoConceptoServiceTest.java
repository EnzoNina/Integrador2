package utp.edu.codekion.finanzas.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utp.edu.codekion.finanzas.model.TipoConcepto;
import utp.edu.codekion.finanzas.repository.TipoConceptoRepository;
import utp.edu.codekion.finanzas.service.impl.TipoConceptoServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TipoConceptoServiceTest {


    @Mock
    private TipoConceptoRepository tipoConceptoRepository;

    @InjectMocks
    private TipoConceptoServiceImpl tipoConceptoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllReturnsAllTipoConceptos() {
        TipoConcepto tipoConcepto1 = new TipoConcepto();
        TipoConcepto tipoConcepto2 = new TipoConcepto();
        when(tipoConceptoRepository.findAll()).thenReturn(Arrays.asList(tipoConcepto1, tipoConcepto2));

        List<TipoConcepto> result = tipoConceptoService.findAll();

        assertEquals(2, result.size());
        verify(tipoConceptoRepository, times(1)).findAll();
    }

    @Test
    void findByIdReturnsTipoConceptoWhenFound() {
        TipoConcepto tipoConcepto = new TipoConcepto();
        when(tipoConceptoRepository.findById(1)).thenReturn(Optional.of(tipoConcepto));

        TipoConcepto result = tipoConceptoService.findById(1);

        assertNotNull(result);
        assertEquals(tipoConcepto, result);
        verify(tipoConceptoRepository, times(1)).findById(1);
    }

    @Test
    void findByIdReturnsNullWhenNotFound() {
        when(tipoConceptoRepository.findById(1)).thenReturn(Optional.empty());

        TipoConcepto result = tipoConceptoService.findById(1);

        assertNull(result);
        verify(tipoConceptoRepository, times(1)).findById(1);
    }

}