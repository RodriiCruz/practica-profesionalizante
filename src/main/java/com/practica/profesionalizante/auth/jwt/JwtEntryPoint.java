package com.practica.profesionalizante.auth.jwt;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

	private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        logger.error("Falla en el metodo commence");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"No Autorizado");
    }

    private void mostrarCabecera(HttpServletRequest request){
        Iterator it = request.getHeaderNames().asIterator();
        while (it.hasNext()){
            String data = (String)it.next();
            System.out.println(data + "   :    " + request.getHeader(data));
        }
    }
}
