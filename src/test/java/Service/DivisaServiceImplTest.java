package Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utp.edu.codekion.finanzas.model.Divisa;
import utp.edu.codekion.finanzas.repository.DivisaRepository;
import utp.edu.codekion.finanzas.service.impl.DivisaServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class DivisaServiceImplTest {

    @Mock
    private DivisaRepository divisaRepository;

    @InjectMocks
    private DivisaServiceImpl divisaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Divisa divisa1 = new Divisa();
        Divisa divisa2 = new Divisa();
        List<Divisa> divisaList = Arrays.asList(divisa1, divisa2);

        when(divisaRepository.findAll()).thenReturn(divisaList);

        List<Divisa> result = divisaService.findAll();
        assertEquals(2, result.size());
        assertEquals(divisa1, result.get(0));
        assertEquals(divisa2, result.get(1));
    }

    @Test
    public void testFindById() {
        Divisa divisa = new Divisa();
        when(divisaRepository.findById(1)).thenReturn(Optional.of(divisa));

        Divisa result = divisaService.findById(1);
        assertEquals(divisa, result);
    }

    @Test
    public void testFindByIdNotFound() {
        when(divisaRepository.findById(1)).thenReturn(Optional.empty());

        Divisa result = divisaService.findById(1);
        assertNull(result);
    }
}