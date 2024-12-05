package co.edu.cue.validacionUsuarios;


import co.edu.cue.validacionUsuarios.model.Usuario;
import co.edu.cue.validacionUsuarios.model.enums.Estados;
import co.edu.cue.validacionUsuarios.repository.UsuarioRepository;
import co.edu.cue.validacionUsuarios.request.UsuarioRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ValidacionUsuariosIntegracionTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        usuarioRepository.deleteAll();
    }

    @Test
    void testAgregarUsuario() {
        // Preparar datos de prueba
        UsuarioRequest request = new UsuarioRequest();
        request.setId("1");
        request.setNombre("Juan Pérez");
        request.setEmail("juan.perez@example.com");
        request.setDescripcion("Usuario de prueba");

        // Ejecutar la petición
        ResponseEntity<?> response = restTemplate.postForEntity("/usuario", request, Object.class);

        // Validar respuesta
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // Verificar datos en la base de datos
        Optional<Usuario> usuarioGuardado = usuarioRepository.findById("1");
        assertThat(usuarioGuardado).isPresent();
        assertThat(usuarioGuardado.get().getNombre()).isEqualTo("Juan Pérez");
        assertThat(usuarioGuardado.get().getEmail()).isEqualTo("juan.perez@example.com");
        assertThat(usuarioGuardado.get().getCondicion()).isEqualTo(Estados.ACTIVO);
    }

    @Test
    void testConsultarUsuario() {
        // Insertar datos de prueba en la base de datos
        Usuario usuario = new Usuario("1", "Juan Pérez", "juan.perez@example.com", Estados.ACTIVO, "Usuario de prueba", null, null, null);
        usuarioRepository.save(usuario);

        // Ejecutar la petición
        ResponseEntity<Usuario> response = restTemplate.getForEntity("/usuario/1", Usuario.class);

        // Validar respuesta
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getNombre()).isEqualTo("Juan Pérez");
    }

    @Test
    void testActualizarUsuario() {
        // Insertar datos de prueba en la base de datos
        Usuario usuario = new Usuario("1", "Juan Pérez", "juan.perez@example.com", Estados.ACTIVO, "Usuario de prueba", null, null, null);
        usuarioRepository.save(usuario);

        // Preparar datos de actualización
        UsuarioRequest request = new UsuarioRequest();
        request.setNombre("Juan Actualizado");
        request.setEmail("juan.actualizado@example.com");
        request.setDescripcion("Descripción actualizada");

        // Ejecutar la petición
        restTemplate.put("/usuario/1", request);

        // Validar cambios en la base de datos
        Optional<Usuario> usuarioActualizado = usuarioRepository.findById("1");
        assertThat(usuarioActualizado).isPresent();
        assertThat(usuarioActualizado.get().getNombre()).isEqualTo("Juan Actualizado");
    }

    @Test
    void testEliminarUsuario() {
        // Insertar datos de prueba en la base de datos
        Usuario usuario = new Usuario("1", "Juan Pérez", "juan.perez@example.com", Estados.ACTIVO, "Usuario de prueba", null, null, null);
        usuarioRepository.save(usuario);

        // Ejecutar la petición
        restTemplate.delete("/usuario/1");

        // Validar que el usuario fue marcado como inactivo
        Optional<Usuario> usuarioEliminado = usuarioRepository.findById("1");
        assertThat(usuarioEliminado).isPresent();
        assertThat(usuarioEliminado.get().getCondicion()).isEqualTo(Estados.INACTIVO);
    }
}