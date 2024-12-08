package Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import utp.edu.codekion.finanzas.controller.DivisaController;
import utp.edu.codekion.finanzas.model.Divisa;
import utp.edu.codekion.finanzas.service.IService.IDivisaService;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;





public class DivisaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IDivisaService divisaService;

    @InjectMocks
    private DivisaController divisaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(divisaController).build();
    }

    @Test
    public void testListAll() throws Exception {
        Divisa divisa1 = new Divisa(1, "USD", "United States Dollar","A");
        Divisa divisa2 = new Divisa(2, "EUR", "Euro","2");
        List<Divisa> divisaList = Arrays.asList(divisa1, divisa2);

        when(divisaService.findAll()).thenReturn(divisaList);

        mockMvc.perform(get("/divisa/listar")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'code':'USD','name':'United States Dollar'},{'id':2,'code':'EUR','name':'Euro'}]"));
    }

    @Test
    public void testFindById() throws Exception {
        Divisa divisa = new Divisa(1, "USD", "United States Dollar","2");

        when(divisaService.findById(1)).thenReturn(divisa);

        mockMvc.perform(get("/divisa/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':1,'code':'USD','name':'United States Dollar'}"));
    }
}