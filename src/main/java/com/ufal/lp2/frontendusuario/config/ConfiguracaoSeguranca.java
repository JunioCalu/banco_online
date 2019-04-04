package com.ufal.lp2.frontendusuario.config;

import java.security.SecureRandom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ufal.lp2.frontendusuario.service.ServicoUsuarioImpl.ServicoSegurancaUsuario;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter {
	private static final Logger L = LogManager.getLogger(ConfiguracaoSeguranca.class);

	private static final String[] PUBLIC_MATCHERS = {
            "/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/",
            "/sobre/**",
            "/contato/**",
            "/error",
            "/inscrever",
            "/h2-console/**"
    };

    @Autowired
    private ServicoSegurancaUsuario servicoSegurancaUsuario;

    private static final String SALT = "salt"; // Salt should be protected carefully

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    		L.debug("Iniciando ConfiguracaoSeguranca.configuracao...");
        http
                .authorizeRequests().
                antMatchers(PUBLIC_MATCHERS).
                permitAll().anyRequest().authenticated();

        http
                .csrf().disable().cors().disable()
                .formLogin().failureUrl("/error").defaultSuccessUrl("/frontendUsuario").loginPage("/index").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/index?logout").deleteCookies("lembre-me").permitAll()
                .and()
                .rememberMe();

        http.headers().frameOptions().disable(); // para habilitar o h2 console

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(servicoSegurancaUsuario).passwordEncoder(passwordEncoder());
        L.debug("encerrando ConfiguracaoSeguranca.configureGlobal...");
    }
}
