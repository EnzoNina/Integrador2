package Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import utp.edu.codekion.finanzas.model.ResumenTransacciones;
import utp.edu.codekion.finanzas.repository.ResumenTransaccionesRepository;
import utp.edu.codekion.finanzas.service.impl.ResumenTransaccionServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;




public class ResumenTransaccionServiceImplTest {

    @InjectMocks
    private ResumenTransaccionServiceImpl resumenTransaccionService;

    @Mock
    private ResumenTransaccionesRepository resumenTransaccionesRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        ResumenTransacciones resumenTransacciones = new ResumenTransacciones();
        resumenTransacciones.setId(1);        

        when(resumenTransaccionesRepository.save(any(ResumenTransacciones.class))).thenReturn(resumenTransacciones);

        ResumenTransacciones result = resumenTransaccionService.save(resumenTransacciones);

        assertEquals(1, result.getId());        
    }
}