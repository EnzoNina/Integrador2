package utp.edu.codekion.finanzas.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import utp.edu.codekion.finanzas.service.IService.IAuthenticacionService;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

// Se implementa la interfaz IAuthenticacionService
//Se usa el principio OCP

@Component
public class AuthenticationServiceImpl implements IAuthenticacionService {

    private final SecretKey key;

    public AuthenticationServiceImpl(@Value("${app.jwtSecret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    @Override
    public String getToken(UserDetails user) {
        return authenticate(new HashMap<>(), user);
    }

    @Override
    public String authenticate(HashMap<String, Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))//Agregamos la fecha de creacion
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 365)) // Agregamos la fecha de expiracion (1 año)
                .signWith(getKey())//Agregamos la firma
                .compact();
    }

    public SecretKey getKey() {
        return Keys.hmacShaKeyFor(key.getEncoded());
    }

    @Override
    public String getUsernameByToken(String token) {
        return getClaims(token, Claims::getSubject);
    }

    @Override
    public <T> T getClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);//Obtenemos los claims
        return claimsResolver.apply(claims);//Retornamos el claim
    }

    @Override
    public Claims getAllClaims(String token) {
        //Creamos la clave secreta
        SecretKey secretKey = Jwts.SIG.HS512.key().build();
        //Obtenemos todos los claims
        return Jwts
                .parser()//Creamos el parser
                .verifyWith(getKey()) // Verificamos con la clave secreta
                .build()//Construimos
                .parseSignedClaims(token)//Decodificamos el token y verificamos si la firma es correcta
                .getPayload();//Obtenemos los claims
    }

    @Override
    public Date getExpirationDate(String token) {
        return getClaims(token, Claims::getExpiration);//Obtenemos la fecha de expiracionreturn null;
    }

    @Override
    public Boolean isTokenValid(String token, UserDetails user) {
        final String username = getUsernameByToken(token);//Obtenemos el clienteAcceso
        return (username.equals(user.getUsername()) && !isTokenExpired(token));//Retornamos si el token es válido
    }

    @Override
    public Boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date());//Retornamos si el token está expirado
    }
}
