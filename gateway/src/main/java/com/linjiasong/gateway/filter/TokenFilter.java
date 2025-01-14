package com.linjiasong.gateway.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linjiasong
 * @date 2025/1/14 下午5:32
 */

@Component
public class TokenFilter implements GlobalFilter, Ordered {
    private static final String SECRET_KEY = "linjiasong";
    private static final long DEFAULT_EXPIRATION_TIME = 7L * 24 * 60 * 60 * 1000; // 7天

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String bearerToken = headers.getFirst("Authorization");

        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            System.out.println("Authorization token is missing or invalid");
            return handleUnauthorized(exchange, "Authorization token is missing or invalid");
        }

        String token = bearerToken.substring("Bearer ".length());
        try {
            if(!validateToken(token)){
                return handleUnauthorized(exchange, "Token has expired");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    private Mono<Void> handleUnauthorized(ServerWebExchange exchange, String message) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().add("Content-Type", "application/json");

        String body = String.format("{\"msg\":\"%s\",\"code\":\"401\"}", message);
        DataBuffer buffer = exchange.getResponse().bufferFactory()
                .wrap(body.getBytes(StandardCharsets.UTF_8));

        return exchange.getResponse().writeWith(Flux.just(buffer));
    }

    public static void main(String[] args) throws Exception {
        String s = generateToken("linjiasong");
        System.out.println(s);
    }

    public static String generateToken(String subject) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + DEFAULT_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // 校验 token 是否过期和有效
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
