package Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import utp.edu.codekion.finanzas.model.Banco;
import utp.edu.codekion.finanzas.repository.BancoRepository;
import utp.edu.codekion.finanzas.service.impl.BancoServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BancoServiceImplTest {

    private BancoServiceImpl bancoService;
    private BancoRepository bancoRepository;

    @BeforeEach
    void setUp() {
        bancoRepository = mock(BancoRepository.class);
        bancoService = new BancoServiceImpl(bancoRepository);
    }

    @Test
    void findById_returnsBancoWhenExists() {
        Banco banco = new Banco();
        banco.setId(1);
        when(bancoRepository.findById(1)).thenReturn(Optional.of(banco));

        Banco result = bancoService.findById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void findById_returnsNullWhenNotExists() {
        when(bancoRepository.findById(1)).thenReturn(Optional.empty());

        Banco result = bancoService.findById(1);
        assertNull(result);
    }

    @Test
    void findAll_returnsListOfBancos() {
        Banco banco1 = new Banco();
        banco1.setId(1);
        Banco banco2 = new Banco();
        banco2.setId(2);
        when(bancoRepository.findAll()).thenReturn(Arrays.asList(banco1, banco2));

        List<Banco> result = bancoService.findAll();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void findAll_returnsEmptyListWhenNoBancos() {
        when(bancoRepository.findAll()).thenReturn(Arrays.asList());

        List<Banco> result = bancoService.findAll();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}