package edu.ntnu.tobiasth.backend.security

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig: WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        if (http == null) { return }

        http.cors().and() // Enable cross-origin.
            .csrf().disable() // Disable forgery protection.
            .addFilterAfter(JWTAuthFilter(), UsernamePasswordAuthenticationFilter::class.java) // Add custom auth.
            .headers()
            .frameOptions().sameOrigin() // Set frame-options header.
            .and()
            .authorizeRequests() // Requests to authorize will follow.
            .antMatchers("/h2/**").permitAll() // Allow all H2 console access.
            .antMatchers(HttpMethod.POST,"/token", "/register").permitAll() // Allow all POSTs to urls.
            .anyRequest().authenticated() // Allow any request that passes the JWTAuthFilter.
    }
}