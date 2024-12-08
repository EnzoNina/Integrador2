package Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utp.edu.codekion.finanzas.model.Frecuencia;
import utp.edu.codekion.finanzas.repository.FrecuenciaRepository;
import utp.edu.codekion.finanzas.service.impl.FrecuenciaServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;





public class FrecuenciaServiceImplTest {

    @Mock
    private FrecuenciaRepository frecuenciaRepository;

    @InjectMocks
    private FrecuenciaServiceImpl frecuenciaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Frecuencia frecuencia1 = new Frecuencia();
        Frecuencia frecuencia2 = new Frecuencia();
        List<Frecuencia> frecuencias = Arrays.asList(frecuencia1, frecuencia2);

        when(frecuenciaRepository.findAll()).thenReturn(frecuencias);

        List<Frecuencia> result = frecuenciaService.findAll();
        assertEquals(2, result.size());
        assertEquals(frecuencia1, result.get(0));
        assertEquals(frecuencia2, result.get(1));
    }

    @Test
    public void testFindById() {
        Frecuencia frecuencia = new Frecuencia();
        when(frecuenciaRepository.findById(1)).thenReturn(Optional.of(frecuencia));

        Frecuencia result = frecuenciaService.findById(1);
        assertEquals(frecuencia, result);
    }

    @Test
    public void testFindByIdNotFound() {
        when(frecuenciaRepository.findById(1)).thenReturn(Optional.empty());

        Frecuencia result = frecuenciaService.findById(1);
        assertNull(result);
    }
}