package com.nisum.registro.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.junit.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.Assert.*;

class TokenTest {

    private static final Logger logger = LogManager.getLogger();

    @Test
    void createJWT() {
        String jwtId = "NISUM1234";
        String jwtIssuer = "JWT Token";
        String jwtSubject = "Nombre";
        int jwtTimeToLive = 800000;

        String jwt = Token.createJWT(
                jwtId, // claim = jti
                jwtIssuer, // claim = iss
                jwtSubject, // claim = sub
                jwtTimeToLive // used to calculate expiration (claim = exp)
        );

        logger.info("jwt = \"" + jwt.toString() + "\"");

        Claims claims = Token.decodeJWT(jwt);

        logger.info("claims = " + claims.toString());

        assertEquals(jwtId, claims.getId());
        assertEquals(jwtIssuer, claims.getIssuer());
        assertEquals(jwtSubject, claims.getSubject());
    }

    @Test(expected = MalformedJwtException.class)
    public void decodeShouldFail() {
        String notAJwt = "No es un JWT";
        // This will fail with expected exception listed above
        Claims claims = Token.decodeJWT(notAJwt);
    }

    @Test(expected = SignatureException.class)
    void decodeJWT() {
        String jwtId = "NISUM1234";
        String jwtIssuer = "JWT Token";
        String jwtSubject = "Nombre";
        int jwtTimeToLive = 800000;

        String jwt = Token.createJWT(
                jwtId, // claim = jti
                jwtIssuer, // claim = iss
                jwtSubject, // claim = sub
                jwtTimeToLive // used to calculate expiration (claim = exp)
        );

        logger.info("jwt = \"" + jwt.toString() + "\"");

        // tamper with the JWT

        StringBuilder tamperedJwt = new StringBuilder(jwt);
        tamperedJwt.setCharAt(22, 'I');

        logger.info("tamperedJwt = \"" + tamperedJwt.toString() + "\"");

        assertNotEquals(jwt, tamperedJwt);

        // this will fail with a SignatureException

        Token.decodeJWT(tamperedJwt.toString());
    }
}