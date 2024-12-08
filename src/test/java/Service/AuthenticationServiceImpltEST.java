package Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import utp.edu.codekion.finanzas.service.IService.IAuthenticacionService;
import utp.edu.codekion.finanzas.service.impl.AuthenticationServiceImpl;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationServiceImplTest {

    private AuthenticationServiceImpl authenticationService;
    private UserDetails userDetails;
    private String secret = "mysecretkeymysecretkeymysecretkeymysecretkey";

    @BeforeEach
    void setUp() {
        authenticationService = new AuthenticationServiceImpl(secret);
        userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("testUser");
    }

    @Test
    void getToken_returnsValidToken() {
        String token = authenticationService.getToken(userDetails);
        assertNotNull(token);
        assertEquals("testUser", authenticationService.getUsernameByToken(token));
    }

    @Test
    void authenticate_returnsTokenWithExtraClaims() {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", "admin");
        String token = authenticationService.authenticate(claims, userDetails);
        Claims parsedClaims = authenticationService.getAllClaims(token);
        assertEquals("admin", parsedClaims.get("role"));
    }

    @Test
    void getUsernameByToken_returnsCorrectUsername() {
        String token = authenticationService.getToken(userDetails);
        String username = authenticationService.getUsernameByToken(token);
        assertEquals("testUser", username);
    }

    @Test
    void getClaims_returnsCorrectClaims() {
        String token = authenticationService.getToken(userDetails);
        Claims claims = authenticationService.getAllClaims(token);
        assertEquals("testUser", claims.getSubject());
    }

    @Test
    void getExpirationDate_returnsCorrectExpirationDate() {
        String token = authenticationService.getToken(userDetails);
        Date expirationDate = authenticationService.getExpirationDate(token);
        assertTrue(expirationDate.after(new Date()));
    }

    @Test
    void isTokenValid_returnsTrueForValidToken() {
        String token = authenticationService.getToken(userDetails);
        assertTrue(authenticationService.isTokenValid(token, userDetails));
    }

    @Test
    void isTokenExpired_returnsFalseForValidToken() {
        String token = authenticationService.getToken(userDetails);
        assertFalse(authenticationService.isTokenExpired(token));
    }


}