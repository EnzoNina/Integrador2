package utp.edu.codekion.finanzas.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utp.edu.codekion.finanzas.controller.ConceptoController;
import utp.edu.codekion.finanzas.model.TipoConcepto;
import utp.edu.codekion.finanzas.service.IService.ITipoConceptoService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ConceptoControllerTest {


    @Mock
    private ITipoConceptoService tipoConceptoService;

    @InjectMocks
    private ConceptoController conceptoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listAll_ReturnsListOfTipoConceptos() {
        List<TipoConcepto> tipoConceptos = List.of(new TipoConcepto(), new TipoConcepto());
        when(tipoConceptoService.findAll()).thenReturn(tipoConceptos);

        List<TipoConcepto> result = conceptoController.listAll();

        assertEquals(tipoConceptos, result);
    }

    @Test
    void findById_ReturnsTipoConcepto_WhenIdExists() {
        TipoConcepto tipoConcepto = new TipoConcepto();
        when(tipoConceptoService.findById(1)).thenReturn(tipoConcepto);

        TipoConcepto result = conceptoController.findById(1);

        assertEquals(tipoConcepto, result);
    }

    @Test
    void findById_ReturnsNull_WhenIdDoesNotExist() {
        when(tipoConceptoService.findById(1)).thenReturn(null);

        TipoConcepto result = conceptoController.findById(1);

        assertEquals(null, result);
    }

}