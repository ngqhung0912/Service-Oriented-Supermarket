package com.project.supermarketapigateway.config;

import com.project.supermarketapigateway.service.CustomerWelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import reactor.core.publisher.Mono;

import java.net.URI;

@Configuration
@EnableWebFluxSecurity
public class AuthenticationConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

//        ServerAuthenticationSuccessHandler authenticationSuccessHandler = (webFilterExchange, authentication) -> {
//        DefaultOidcUser oauthUser = (DefaultOidcUser) authentication.getPrincipal();
//        return Mono.empty();
//        };
        RedirectServerAuthenticationSuccessHandler authenticationSuccessHandler = new RedirectServerAuthenticationSuccessHandler("/");
        http.csrf()
                .disable()
                .authorizeExchange()
                .anyExchange()
                .authenticated()
                .and()
                .oauth2Login()
                .authenticationSuccessHandler(authenticationSuccessHandler);
        return http.build();
    }
}

