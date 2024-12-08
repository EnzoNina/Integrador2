package Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import utp.edu.codekion.finanzas.controller.ConceptoController;
import utp.edu.codekion.finanzas.model.TipoConcepto;
import utp.edu.codekion.finanzas.service.IService.ITipoConceptoService;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ConceptoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ITipoConceptoService tipoConceptoService;

    @InjectMocks
    private ConceptoController conceptoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(conceptoController).build();
    }

    @Test
    public void testListAll() throws Exception {
        TipoConcepto tipo1 = new TipoConcepto(1, "Concepto 1");
        TipoConcepto tipo2 = new TipoConcepto(2, "Concepto 2");
        List<TipoConcepto> tipoConceptos = Arrays.asList(tipo1, tipo2);

        when(tipoConceptoService.findAll()).thenReturn(tipoConceptos);

        mockMvc.perform(get("/concepto/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Concepto 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Concepto 2"));
    }

    @Test
    public void testFindById() throws Exception {
        TipoConcepto tipo = new TipoConcepto(1, "Concepto 1");

        when(tipoConceptoService.findById(1)).thenReturn(tipo);

        mockMvc.perform(get("/concepto/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Concepto 1"));
    }
}