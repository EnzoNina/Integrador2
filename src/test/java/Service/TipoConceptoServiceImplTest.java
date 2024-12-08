package Service;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;





public class TipoConceptoServiceImplTest {

    @Mock
    private TipoConceptoRepository tipoConceptoRepository;

    @InjectMocks
    private TipoConceptoServiceImpl tipoConceptoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        TipoConcepto tipoConcepto1 = new TipoConcepto();
        TipoConcepto tipoConcepto2 = new TipoConcepto();
        List<TipoConcepto> tipoConceptoList = Arrays.asList(tipoConcepto1, tipoConcepto2);

        when(tipoConceptoRepository.findAll()).thenReturn(tipoConceptoList);

        List<TipoConcepto> result = tipoConceptoService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        TipoConcepto tipoConcepto = new TipoConcepto();
        when(tipoConceptoRepository.findById(1)).thenReturn(Optional.of(tipoConcepto));

        TipoConcepto result = tipoConceptoService.findById(1);
        assertEquals(tipoConcepto, result);
    }

    @Test
    public void testFindByIdNotFound() {
        when(tipoConceptoRepository.findById(1)).thenReturn(Optional.empty());

        TipoConcepto result = tipoConceptoService.findById(1);
        assertNull(result);
    }
}