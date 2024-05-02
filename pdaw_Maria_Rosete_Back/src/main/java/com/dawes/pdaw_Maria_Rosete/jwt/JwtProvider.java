package com.dawes.pdaw_Maria_Rosete.jwt;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtProvider {

    private static final Key LLAVE_SECRETA = new SecretKeySpec("F@cturacionSR12023F@cturacionSR12023F@cturacionSR12023".getBytes(), SignatureAlgorithm.HS512.getJcaName());
    private static final long TIEMPO_EXPIRACION = 3600_000; // 1 hora de expiración

    public static String generarTokenJWT(String nombreUsuario, Integer idUsuario) {
        String token = Jwts.builder()
                .setSubject(nombreUsuario)
                .claim("userId", idUsuario) // Incluir el ID del usuario como una reclamación
                .setExpiration(Date.from(Instant.now().plusMillis(TIEMPO_EXPIRACION)))
                .signWith(LLAVE_SECRETA)
                .compact();
        
        // Imprimir el token JWT en la consola para verificar que se esté generando correctamente
        System.out.println("Token JWT generado: " + token);

        return token;
    }

    public static boolean validarTokenJWT(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(LLAVE_SECRETA).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Integer getIdUsuario(String token) {
        Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(LLAVE_SECRETA).build().parseClaimsJws(token);
        return jws.getBody().get("userId", Integer.class); // Obtener el ID del usuario del token
    }

    public static String getNombreUsuario(String token) {
        Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(LLAVE_SECRETA).build().parseClaimsJws(token);
        return jws.getBody().getSubject();
    }
}

