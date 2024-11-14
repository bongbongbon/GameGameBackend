package com.project.api_gateway.config;


import com.project.api_gateway.filter.AuthorizationHeaderFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    private final AuthorizationHeaderFilter authorizationHeaderFilter;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()

                // AUTH GET요청 인증인가 없이 진행
                .route("public-route", r -> r.path("/api/v1/auth/**")
                        .and()
                        .method("POST")
                        .filters(f -> f.removeRequestHeader("Cookie"))
                        .uri("lb://AUTH-SERVICE"))

                // AUTH GET요청 인증인가 없이 진행
                .route("public-route", r -> r.path("/api/v1/auth/{userId}")
                        .and()
                        .method("GET")
                        .filters(f -> f.removeRequestHeader("Cookie"))
                        .uri("lb://AUTH-SERVICE"))

                // AUTH 관련된 로직 인증인가
                .route("user-get-all-route", r -> r.path("/api/v1/auth/**", "/auth-service/v3/api-docs")
                        .and()
                        .method("GET", "PATCH", "DELETE", "PUT")
                        .filters(f -> f
                                .removeRequestHeader("Cookie")
                                .filter(authorizationHeaderFilter.apply(
                                        new AuthorizationHeaderFilter.Config())))  // 필터 팩토리로 필터 생성
                        .uri("lb://AUTH-SERVICE"))

//                 TEAM GET요청 인증인가 없이 진행
                .route("public-route", r -> r.path("/api/v1/teams/**")
                        .and()
                        .method("GET")
                        .filters(f -> f.removeRequestHeader("Cookie"))
                        .uri("lb://TEAM-SERVICE"))

                // TEAM command에 관련된 로직 인증인가
                .route("user-get-all-route", r -> r.path("/api/v1/teams/**", "/team-service/v3/api-docs")
                        .and()
                        .method("POST", "PATCH", "DELETE", "PUT")
                        .filters(f -> f
                                .removeRequestHeader("Cookie")
                                .filter(authorizationHeaderFilter.apply(
                                        new AuthorizationHeaderFilter.Config())))  // 필터 팩토리로 필터 생성
                        .uri("lb://TEAM-SERVICE"))


                // RESUME command에 관련된 로직 인증인가
                .route("user-get-all-route", r -> r.path("/api/v1/resumes/my", "/resume-service/v3/api-docs")
                        .and()
                        .method("GET")
                        .filters(f -> f
                                .removeRequestHeader("Cookie")
                                .filter(authorizationHeaderFilter.apply(
                                        new AuthorizationHeaderFilter.Config())))  // 필터 팩토리로 필터 생성
                        .uri("lb://RESUME-SERVICE"))


                // RESUME GET요청 인증인가 없이 진행
                .route("public-route", r -> r.path("/api/v1/resumes/**")
                        .and()
                        .method("GET")
                        .filters(f -> f.removeRequestHeader("Cookie"))
                        .uri("lb://RESUME-SERVICE"))


                // RESUME command에 관련된 로직 인증인가
                .route("user-get-all-route", r -> r.path("/api/v1/resumes/**", "/resume-service/v3/api-docs")
                .and()
                .method("POST", "PATCH", "DELETE", "PUT")
                .filters(f -> f
                        .removeRequestHeader("Cookie")
                        .filter(authorizationHeaderFilter.apply(
                                new AuthorizationHeaderFilter.Config())))  // 필터 팩토리로 필터 생성
                .uri("lb://RESUME-SERVICE"))
                .build();



    }
}
