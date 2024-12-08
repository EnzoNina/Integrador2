package Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import utp.edu.codekion.finanzas.controller.CategoriasController;
import utp.edu.codekion.finanzas.model.Categoria;
import utp.edu.codekion.finanzas.model.dto.CategoriaRequestDto;
import utp.edu.codekion.finanzas.service.IService.ICategoriaService;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoriasControllerTest {

    @Mock
    private ICategoriaService categoriaService;

    @InjectMocks
    private CategoriasController categoriasController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarCategorias() {
        Categoria categoria1 = new Categoria();
        categoria1.setDescripcion("Categoria 1");
        Categoria categoria2 = new Categoria();
        categoria2.setDescripcion("Categoria 2");

        when(categoriaService.findAll()).thenReturn(Arrays.asList(categoria1, categoria2));

        List<Categoria> categorias = categoriasController.listarCategorias();

        assertEquals(2, categorias.size());
        assertEquals("Categoria 1", categorias.get(0).getDescripcion());
        assertEquals("Categoria 2", categorias.get(1).getDescripcion());
    }

    @Test
    public void testCrearCategoria() {
        CategoriaRequestDto dto = new CategoriaRequestDto();
        dto.setDescripcion("Nueva Categoria");

        Categoria categoria = new Categoria();
        categoria.setDescripcion(dto.getDescripcion());

        when(categoriaService.save(any(Categoria.class))).thenReturn(categoria);

        ResponseEntity<?> responseEntity = categoriasController.crearCategoria(dto);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Categoria creada correctamente", ((Map<String, Object>) responseEntity.getBody()).get("mensaje"));
        assertEquals(categoria, ((Map<String, Object>) responseEntity.getBody()).get("categoria"));
    }

}