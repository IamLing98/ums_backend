package com.linkdoan.backend.handler;

import com.linkdoan.backend.config.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class CustomHandshakeInterceptor implements HandshakeInterceptor {

    private JwtTokenUtil jwtTokenUtil;

    public CustomHandshakeInterceptor(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        try {
            String accessToken = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest().getParameter("access_token");
            String login = jwtTokenUtil.getUsernameFromToken(accessToken);
            System.out.println("login: "+ login);
            map.put("login", login);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
            return false;
        }
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }

}
