package utp.edu.codekion.finanzas.Service;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import utp.edu.codekion.finanzas.service.impl.AuthenticationServiceImpl;

import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AuthenticationServiceTest {


    private AuthenticationServiceImpl authenticationService;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authenticationService = new AuthenticationServiceImpl("mySecretKey12345678901234567890123456789012");
    }

    @Test
    void getToken_returnsValidToken() {
        when(userDetails.getUsername()).thenReturn("testUser");

        String token = authenticationService.getToken(userDetails);

        assertNotNull(token);
        assertEquals("testUser", authenticationService.getUsernameByToken(token));
    }

    @Test
    void authenticate_withExtraClaims_returnsValidToken() {
        when(userDetails.getUsername()).thenReturn("testUser");
        HashMap<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", "admin");

        String token = authenticationService.authenticate(extraClaims, userDetails);

        assertNotNull(token);
        Claims claims = authenticationService.getAllClaims(token);
        assertEquals("testUser", claims.getSubject());
        assertEquals("admin", claims.get("role"));
    }

    @Test
    void getUsernameByToken_returnsCorrectUsername() {
        when(userDetails.getUsername()).thenReturn("testUser");
        String token = authenticationService.getToken(userDetails);

        String username = authenticationService.getUsernameByToken(token);

        assertEquals("testUser", username);
    }

    @Test
    void getClaims_returnsCorrectClaims() {
        when(userDetails.getUsername()).thenReturn("testUser");
        String token = authenticationService.getToken(userDetails);

        Claims claims = authenticationService.getAllClaims(token);

        assertEquals("testUser", claims.getSubject());
    }

    @Test
    void getExpirationDate_returnsCorrectExpirationDate() {
        when(userDetails.getUsername()).thenReturn("testUser");
        String token = authenticationService.getToken(userDetails);

        Date expirationDate = authenticationService.getExpirationDate(token);

        assertTrue(expirationDate.after(new Date()));
    }

    @Test
    void isTokenValid_withValidToken_returnsTrue() {
        when(userDetails.getUsername()).thenReturn("testUser");
        String token = authenticationService.getToken(userDetails);

        boolean isValid = authenticationService.isTokenValid(token, userDetails);

        assertTrue(isValid);
    }


}
