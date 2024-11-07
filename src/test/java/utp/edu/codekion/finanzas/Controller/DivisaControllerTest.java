package utp.edu.codekion.finanzas.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utp.edu.codekion.finanzas.controller.DivisaController;
import utp.edu.codekion.finanzas.model.Divisa;
import utp.edu.codekion.finanzas.service.IService.IDivisaService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DivisaControllerTest {


    @Mock
    private IDivisaService divisaService;

    @InjectMocks
    private DivisaController divisaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listAll_ReturnsListOfDivisas() {
        List<Divisa> divisas = List.of(new Divisa(), new Divisa());
        when(divisaService.findAll()).thenReturn(divisas);

        List<Divisa> result = divisaController.listAll();

        assertEquals(divisas, result);
    }

    @Test
    void findById_ReturnsDivisa_WhenIdExists() {
        Divisa divisa = new Divisa();
        when(divisaService.findById(1)).thenReturn(divisa);

        Divisa result = divisaController.findById(1);

        assertEquals(divisa, result);
    }

    @Test
    void findById_ReturnsNull_WhenIdDoesNotExist() {
        when(divisaService.findById(1)).thenReturn(null);

        Divisa result = divisaController.findById(1);

        assertEquals(null, result);
    }

}