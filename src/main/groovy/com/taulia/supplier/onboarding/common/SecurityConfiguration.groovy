package com.taulia.supplier.onboarding.common

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

import static org.springframework.security.config.Customizer.withDefaults
import static org.springframework.web.cors.CorsConfiguration.ALL

/**
 * @see <a
 *     href="https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter">
 *     security</a> for replacing WebSecurityConfigurerAdapter
 */
@Configuration
class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.requestMatcher(EndpointRequest.toAnyEndpoint())
        // by default users accessing the actuator endpoints should have the endpoint admin role
        //        .authorizeRequests((requests) -> requests.anyRequest().hasRole("ENDPOINT_ADMIN"))
                .authorizeRequests(requests -> requests.anyRequest().permitAll())
                .authorizeHttpRequests(
                        authz ->
                                authz
                                        .antMatchers(
                                                "/actuator/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated())
                .httpBasic(withDefaults())
                .csrf().disable()
                .cors()

        return http.build()
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource()
        CorsConfiguration config = new CorsConfiguration()
        config.setAllowedMethods(
                Arrays.asList(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.HEAD.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.OPTIONS.name()))
        config.setAllowedHeaders(Collections.singletonList(ALL))
        config.setAllowedOrigins(Collections.singletonList(ALL))
        config.setMaxAge(1800L)
        source.registerCorsConfiguration("/**", config)
        return source
    }
}
