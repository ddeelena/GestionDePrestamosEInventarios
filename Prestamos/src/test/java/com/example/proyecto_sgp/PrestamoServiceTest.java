package com.example.proyecto_sgp;

import com.example.proyecto_sgp.Prestamos_service.entity.Prestamo;
import com.example.proyecto_sgp.Prestamos_service.entity.Usuario;
import com.example.proyecto_sgp.Prestamos_service.repository.PrestamoRepository;
import com.example.proyecto_sgp.Prestamos_service.repository.UsuarioRepository;
import com.example.proyecto_sgp.inventario.ElementosDti.ElementosDti;
import com.example.proyecto_sgp.inventario.Enums.EstadosElementos;
import com.example.proyecto_sgp.inventario.Enums.TipoDeElementos;
import com.example.proyecto_sgp.inventario.repository.InventarioRepository;
import com.example.proyecto_sgp.Prestamos_service.service.PrestamoService;
import com.example.proyecto_sgp.Prestamos_service.excepciones.ResourceNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PrestamoServiceTest {

    @Mock
    private PrestamoRepository prestamoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private PrestamoService prestamoService;

    public PrestamoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSolicitarRecurso_UsuarioNoEncontrado() {
        String usuarioId = "123";
        String recursoId = "456";
        String ubicacion = "Aula 101";
        String sede = "Principal";

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> prestamoService.solicitarRecurso(usuarioId, recursoId, ubicacion, sede));

        assertEquals("Usuario con ID 123 no encontrado", exception.getMessage());
    }

@Test
void testSolicitarRecurso_RecursoNoDisponible() {
    String usuarioId = "123";
    String recursoId = "456";
    String ubicacion = "Aula 101";
    String sede = "Principal";

    Usuario usuario = new Usuario();
    usuario.setId(usuarioId);

    // Implementación mínima de una subclase concreta para pruebas
    class ElementoDtiConcreto extends ElementosDti {
        public ElementoDtiConcreto(String identificacion, String nombre, String descripcion, TipoDeElementos tipo, EstadosElementos estado, LocalDate fechaCreacion) {
            super(identificacion, nombre, descripcion, tipo, estado, fechaCreacion);
        }

        @Override
        public TipoDeElementos getTipo() {
            return tipo;
        }
    }

    // instancia concreta de ElementosDti
    ElementoDtiConcreto elementoDti = new ElementoDtiConcreto(
        recursoId,
        "Computador",
        "Computador Asus",
        TipoDeElementos.Computador,
        EstadosElementos.EN_PRESTAMO,
        LocalDate.now()
    );

    when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));

    when(inventarioRepository.findById(recursoId)).thenReturn(Optional.of(elementoDti));

    RuntimeException exception = assertThrows(RuntimeException.class,
            () -> prestamoService.solicitarRecurso(usuarioId, recursoId, ubicacion, sede));

    assertEquals("El recurso con ID 456 no está disponible.", exception.getMessage());
}

    

    @Test
    void testAprobarPrestamo_Exitoso() {
        String prestamoId = "789";
        String aprobadoPor = "admin";

        Prestamo prestamo = new Prestamo();
        prestamo.setId(prestamoId);
        prestamo.setEstado("Pendiente");

        when(prestamoRepository.findById(prestamoId)).thenReturn(Optional.of(prestamo));

        prestamoService.aprobarPrestamo(prestamoId, aprobadoPor);

        verify(prestamoRepository, times(1)).save(prestamo);
        assertEquals("Aprobado", prestamo.getEstado());
        assertEquals("admin", prestamo.getModificadoPor());
    }
}
