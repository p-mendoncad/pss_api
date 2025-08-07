package com.example.pssapi.config;

import com.example.pssapi.security.JwtAuthFilter;
import com.example.pssapi.security.JwtService;
import com.example.pssapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, usuarioService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/**")
                .permitAll()
                .antMatchers("/api/v1/clientes/**")
                .authenticated()
                .antMatchers("/api/v1/pets/**")
                .authenticated()
                .antMatchers("/api/v1/agendamentos/**")
                .permitAll()
                .antMatchers("/api/v1/funcionarios/**")
                .permitAll()
                .antMatchers("/api/v1/produtos/**")
                .permitAll()
                .antMatchers("/api/v1/servicos/**")
                .permitAll()
                .antMatchers("/api/v1/racas/**")
                .permitAll()
                .antMatchers("/api/v1/setores/**")
                .permitAll()
                .antMatchers("/api/v1/vendas/**")
                .permitAll()
                .antMatchers("/api/v1/itemVendas/**")
                .permitAll()
                .antMatchers("/api/v1/usuarios/**")
                .permitAll()
                .antMatchers("/api/v1/cargos/**")
                .hasRole("ADMIN")
                .antMatchers("/api/v1/fornecedores/**")
                .hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}