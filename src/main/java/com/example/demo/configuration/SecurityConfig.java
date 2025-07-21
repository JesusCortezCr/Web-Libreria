package com.example.demo.configuration;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Autowired
        private UsuarioRepository usuarioRepository;

        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public UserDetailsService userDetailsService() {
                return email -> {
                        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(email);
                        Usuario usuario = usuarioOpt
                                        .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

                        String roleName = usuario.getRol().getNombre();
                        if (!roleName.startsWith("ROLE_")) {
                                roleName = "ROLE_" + roleName;
                        }

                        return new User(
                                        usuario.getCorreo(),
                                        usuario.getContrasenia(),
                                        Collections.singletonList(new SimpleGrantedAuthority(roleName)));
                };
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/cliente/nuevo-cliente", "/cliente/registrar-cliente",
                                                                "/login", "/css/**", "/js/**", "/img/**",
                                                                "/sobre-nosotros",
                                                                "/contactanos",       
                                                                "/contactanos/**",
                                                                "/guardar-contacto",
                                                                "/articulos",
                                                                "/articulos/**",
                                                                "/pages/**"
                                                                )
                                                .permitAll()
                                                .anyRequest().authenticated())
                                .formLogin(login -> login
                                                .loginPage("/login")
                                                .usernameParameter("correo")
                                                .passwordParameter("contrasenia")
                                                .defaultSuccessUrl("/", true)
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutSuccessUrl("/login?logout")
                                                .permitAll());
                return http.build();
        }

        
        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
             "/img/**",
                        "/css/**",
                        "/js/**",
                        "/webjars/**"
        );
}
  
}
