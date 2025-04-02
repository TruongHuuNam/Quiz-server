package com.truonghuunam.backend_test__online.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("*")); // Hoặc domain frontend
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowCredentials(true); // Quan trọng nếu frontend cần gửi cookie/token
                    return config;
                }))
                .csrf(csrf -> csrf.disable()) // Nếu không cần CSRF protection
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/v3/api-docs.yaml",
                                "/actuator/**",
                                "/public/**",
                                "/api/public/**")
                        .permitAll() // Cho phép truy cập các API công khai
                        .anyRequest().authenticated()) // Còn lại yêu cầu authentication
                .formLogin(login -> login.disable()) // Vô hiệu hóa login form mặc định
                .httpBasic(basic -> basic.disable()); // Vô hiệu hóa Basic Auth nếu không cần

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*")); // Hoặc domain cụ thể
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true); // Đảm bảo hỗ trợ gửi cookie/token nếu cần
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
