package utp.edu.codekion.finanzas.Service;

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ResumenTransaccionServiceTest {

    @Mock
    private DivisaRepository divisaRepository;

    @InjectMocks
    private DivisaServiceImpl divisaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllReturnsAllDivisas() {
        Divisa divisa1 = new Divisa();
        Divisa divisa2 = new Divisa();
        when(divisaRepository.findAll()).thenReturn(Arrays.asList(divisa1, divisa2));

        List<Divisa> result = divisaService.findAll();

        assertEquals(2, result.size());
        verify(divisaRepository, times(1)).findAll();
    }

    @Test
    void findByIdReturnsDivisaWhenFound() {
        Divisa divisa = new Divisa();
        when(divisaRepository.findById(1)).thenReturn(Optional.of(divisa));

        Divisa result = divisaService.findById(1);

        assertNotNull(result);
        assertEquals(divisa, result);
        verify(divisaRepository, times(1)).findById(1);
    }

    @Test
    void findByIdReturnsNullWhenNotFound() {
        when(divisaRepository.findById(1)).thenReturn(Optional.empty());

        Divisa result = divisaService.findById(1);

        assertNull(result);
        verify(divisaRepository, times(1)).findById(1);
    }

}