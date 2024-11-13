package com.project.api_gateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    @Value("${spring.jwt.secret}")
    private String secretKey;

    public AuthorizationHeaderFilter() {
        super(Config.class);
    }

    public static class Config {
        //
    }

    @Override
    public GatewayFilter apply(Config config) {

        // ServerWebExchange 파라미터는 필터가 동작하는 동안 현재 요청 및 응답에 대한 정보를 제공한다.
        // 비동기 서버 Netty 에서는 동기 서버(ex:tomcat)와 다르게 request/response 객체를 선언할 때 Server~ 를 사용한다.
        GatewayFilter filter = (exchange, chain) -> {

            String path = exchange.getRequest().getURI().getPath();

            ServerHttpRequest request = exchange.getRequest();

            // Header에 AUTHORIZATION에 관련된 값이 있는지 확인
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "no authorization header", HttpStatus.UNAUTHORIZED);
            }


            String token = extractToken(exchange);

            Claims claims;
            try {
                claims = extractClaims(token);
            } catch (ExpiredJwtException e) {
                return onError(exchange, "토큰 시간 만료이 만료되었습니다.", HttpStatus.UNAUTHORIZED);
            } catch (Exception e) {
                return onError(exchange, "잘못된 토큰입니다.", HttpStatus.UNAUTHORIZED);
            }


            if (!validateToken(claims, exchange)) {
                return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
            }


            return chain.filter(exchange);
        };

        return filter;
    }



    // Mono, Flux -> Spring WebFlux
    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);

        // Content-Type 설정
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        // Body에 추가할 문자열 생성
        String jsonResponse = String.format("{\"error\": \"%s\"}", message);

        // DataBuffer에 JSON 문자열 담기
        DataBuffer buffer = response.bufferFactory()
                .wrap(jsonResponse.getBytes(StandardCharsets.UTF_8));

        log.error(message);

        // 응답에 Body 쓰기
        return response.writeWith(Mono.just(buffer));
    }


    // 토큰 분리
    private String extractToken(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    // 토큰 암호화 제거
    private Claims extractClaims(String token) {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        var secretKeyBytes = Keys.hmacShaKeyFor(bytes);
//            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));

        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(secretKeyBytes)
                .build()
                .parseClaimsJws(token);

        return claimsJws.getBody();
    }

    // 토큰 검증
    // ServerWebExchange : 현재 HTTP 요청/응답 교환을 나타내는 객체로 요청 헤더를 수정할 수 있음
    private boolean validateToken(Claims claims, ServerWebExchange exchange) {
        try {

            log.info("#####payload :: " + claims.toString());

            // 요청 헤더 수정 (Userid,role 추가)
            exchange.getRequest().mutate()
                    .header("X-UserId", claims.get("userId").toString())
                    .header("X-Role", claims.get("role").toString())
                    .build();
            // 추가적인 검증 로직 (예: 토큰 만료 여부 확인 등)을 여기에 추가할 수 있습니다.
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
