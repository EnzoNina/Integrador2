package Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utp.edu.codekion.finanzas.model.Categoria;
import utp.edu.codekion.finanzas.repository.CategoriaRepository;
import utp.edu.codekion.finanzas.service.impl.CategoriaServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoriaServiceImplTest {

    private CategoriaServiceImpl categoriaService;
    private CategoriaRepository categoriaRepository;

    @BeforeEach
    void setUp() {
        categoriaRepository = mock(CategoriaRepository.class);
        categoriaService = new CategoriaServiceImpl(categoriaRepository);
    }

    @Test
    void findAll_returnsAllCategories() {
        List<Categoria> categories = List.of(new Categoria(), new Categoria());
        when(categoriaRepository.findAll()).thenReturn(categories);

        List<Categoria> result = categoriaService.findAll();

        assertEquals(2, result.size());
        verify(categoriaRepository, times(1)).findAll();
    }


    @Test
    void findById_returnsCategoryWhenFound() {
        Categoria category = new Categoria();
        when(categoriaRepository.findById(1)).thenReturn(Optional.of(category));

        Categoria result = categoriaService.findById(1);

        assertNotNull(result);
        assertEquals(category, result);
        verify(categoriaRepository, times(1)).findById(1);
    }

    @Test
    void findById_returnsNullWhenNotFound() {
        when(categoriaRepository.findById(1)).thenReturn(Optional.empty());

        Categoria result = categoriaService.findById(1);

        assertNull(result);
        verify(categoriaRepository, times(1)).findById(1);
    }

    @Test
    void findById_returnsNullWhenIdIsNull() {
        Categoria result = categoriaService.findById(null);

        assertNull(result);
        verify(categoriaRepository, never()).findById(any());
    }

    @Test
    void save_savesAndReturnsCategory() {
        Categoria category = new Categoria();
        when(categoriaRepository.save(category)).thenReturn(category);

        Categoria result = categoriaService.save(category);

        assertNotNull(result);
        assertEquals(category, result);
        verify(categoriaRepository, times(1)).save(category);
    }

    @Test
    void save_throwsExceptionWhenCategoryIsNull() {
        assertThrows(IllegalArgumentException.class, () -> categoriaService.save(null));
        verify(categoriaRepository, never()).save(any());
    }

    @Test
    void delete_deletesCategory() {
        Categoria category = new Categoria();

        categoriaService.delete(category);

        verify(categoriaRepository, times(1)).delete(category);
    }

    @Test
    void delete_throwsExceptionWhenCategoryIsNull() {
        assertThrows(IllegalArgumentException.class, () -> categoriaService.delete(null));
        verify(categoriaRepository, never()).delete(any());
    }
}