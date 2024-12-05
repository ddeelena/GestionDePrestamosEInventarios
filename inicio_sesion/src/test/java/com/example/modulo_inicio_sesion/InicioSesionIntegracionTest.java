package com.example.modulo_inicio_sesion;

import com.example.modulo_inicio_sesion.entities.ServiceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InicioSesionIntegracionTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testLoginSuccess() {
        // Prepara la URL de prueba con parámetros válidos
        String url = "/auth/inicio-sesion?correo=test@example.com&pass=12345";

        // Realiza la solicitud GET
        ResponseEntity<ServiceResponse> response = restTemplate.getForEntity(url, ServiceResponse.class);

        // Verifica el estado y la respuesta
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo("FOUND");
    }

    @Test
    public void testLoginFailure() {
        // Prepara la URL de prueba con parámetros inválidos
        String url = "/auth/inicio-sesion?correo=invalid@example.com&pass=wrongpassword";

        // Realiza la solicitud GET
        ResponseEntity<ServiceResponse> response = restTemplate.getForEntity(url, ServiceResponse.class);

        // Verifica el estado y la respuesta
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isNotEqualTo("FOUND");
    }
}
