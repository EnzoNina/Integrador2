package Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import utp.edu.codekion.finanzas.controller.BancoController;
import utp.edu.codekion.finanzas.model.Banco;
import utp.edu.codekion.finanzas.service.IService.IBancoService;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BancoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IBancoService bancoService;

    @InjectMocks
    private BancoController bancoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bancoController).build();
    }

    @Test
    public void testFindAll() throws Exception {
        Banco banco1 = new Banco(1, "Banco 1", "Banco 1", "Banco 1", "Banco 1");
        Banco banco2 = new Banco(2, "Banco 2", "Banco 1", "Banco 1", "Banco 1");
        List<Banco> bancos = Arrays.asList(banco1, banco2);

        when(bancoService.findAll()).thenReturn(bancos);

        mockMvc.perform(get("/banco/get"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'name':'Banco 1'},{'id':2,'name':'Banco 2'}]"));
    }
}