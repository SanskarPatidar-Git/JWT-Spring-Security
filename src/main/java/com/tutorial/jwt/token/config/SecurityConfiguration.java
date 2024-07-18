package com.tutorial.jwt.token.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.tutorial.jwt.token.helper.JwtAuthenticationFilter;

import java.util.List;

/* SecurityConfig - Secure your API by using restrictions.
 * The custom authentication is ready, and the remaining thing is to define what criteria an incoming request must match before being forwarded to application middleware. 
 * We want the following criteria:
 * 
 * 1. csrf.disable()   
 * 			There is no need to provide the CSRF token because we will use it.
 * 2. auth.requestMatchers("/auth/**").permitAll() 
 * 			The request URL path matching /auth/signup and /auth/login doesn't require authentication.
 * 3. auth.requestMatchers("/users/**").authenticated()
 * 			Any other request URL path must be authenticated.
 * 4. sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
 * 			The request is STATELESS, meaning every request must be treated as a new one, even if it comes from the same client or has been received earlier.
 * 5. authenticationProvider(authenticationProvider)
 * 			Must use the custom authentication provider, and they must be executed before the authentication middleware.
 * 6. configuration.setAllowedMethods(List.of("GET","POST"))
 * 			The CORS configuration must allow only POST and GET requests.
 * 			You can pass additional info like headers , methods , origins from which application can be accessed.

*/

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
    private AuthenticationProvider authenticationProvider;
	
	@Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    	 return http.csrf(csrf -> csrf.disable())
                 .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll())
                 .authorizeHttpRequests(auth -> auth.requestMatchers("/users/**").authenticated())
                 .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .authenticationProvider(authenticationProvider)
                 .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                 .build();
    }

    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        //configuration.setAllowedOrigins(List.of("http://localhost:8005"));
        configuration.setAllowedMethods(List.of("GET","POST"));
        configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);

        return source;
    }
}
