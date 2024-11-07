package utp.edu.codekion.finanzas.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utp.edu.codekion.finanzas.controller.FrecuenciaController;
import utp.edu.codekion.finanzas.model.Frecuencia;
import utp.edu.codekion.finanzas.service.IService.IFrecuenciaService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FrecuenciaControllerTest {


    @Mock
    private IFrecuenciaService frecuenciaService;

    @InjectMocks
    private FrecuenciaController frecuenciaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listAll_ReturnsListOfFrecuencias() {
        List<Frecuencia> frecuencias = List.of(new Frecuencia(), new Frecuencia());
        when(frecuenciaService.findAll()).thenReturn(frecuencias);

        List<Frecuencia> result = frecuenciaController.listAll();

        assertEquals(frecuencias, result);
    }

    @Test
    void findById_ReturnsFrecuencia_WhenIdExists() {
        Frecuencia frecuencia = new Frecuencia();
        when(frecuenciaService.findById(1)).thenReturn(frecuencia);

        Frecuencia result = frecuenciaController.findById(1);

        assertEquals(frecuencia, result);
    }

    @Test
    void findById_ReturnsNull_WhenIdDoesNotExist() {
        when(frecuenciaService.findById(1)).thenReturn(null);

        Frecuencia result = frecuenciaController.findById(1);

        assertEquals(null, result);
    }

}