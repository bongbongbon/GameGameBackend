package com.project.api_gateway.config;

import com.project.api_gateway.filter.AuthorizationHeaderFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, AuthorizationHeaderFilter authFilter) {
        return builder.routes()
                .route("create_quiz_route", r -> r.path("/api/quizzes/create/**")
                        .filters(f -> f.filter(authFilter.apply(new AuthorizationHeaderFilter.Config())))
                        .uri("lb://QUIZ-SERVICE"))
                .route("read_quiz_route", r -> r.path("/api/quizzes/get/**")
                        .uri("lb://QUIZ-SERVICE"))
                .route("read_quiz_route", r -> r.path("/api/quizzes/getAll/**")
                        .uri("lb://QUIZ-SERVICE"))
                .route("quiz_checkAnswer", r -> r.path("/api/quizzes/checkAnswer/**")
                        .uri("lb://QUIZ-SERVICE"))
                .route("auth_register", r -> r.path("/api/auth/register/**")
                        .uri("lb://AUTH-SERVICE"))
                .route("auth_login", r -> r.path("/api/auth/login/**")
                        .uri("lb://AUTH-SERVICE"))
                .route("auth_login_user", r -> r.path("/api/auth/me/**")
                        .uri("lb://AUTH-SERVICE"))
                .build();
    }
}
