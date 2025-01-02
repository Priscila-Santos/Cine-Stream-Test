//package com.test.cine_stream_test.security;
//
//import com.test.cine_stream_test.service.UsuarioService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class AuthenticationManagerConfig {
//
//    private final UsuarioService usuarioService;
//    private final PasswordEncoder passwordEncoder;
//
//    public AuthenticationManagerConfig(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
//        this.usuarioService = usuarioService;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(usuarioService::loadUserByUsername)
//                .passwordEncoder(passwordEncoder);
//        return auth.build();
//    }
//}
