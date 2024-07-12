package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;

    public WebSecurityConfig(SuccessUserHandler successUserHandler) {
        this.successUserHandler = successUserHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated()
                .antMatchers("/users").authenticated()
                .antMatchers("/admin/**")
                .hasRole("ADMIN")

                .and()
                .formLogin().successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

/*    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        authenticationProvider.setUserDetailsService();
        return authenticationProvider;
    }*/

    // аутентификация inMemory
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("2")
//              .password("2")
                .password("{bcrypt}$2y$10$THbjr4CE3jnV3fKAdewDc.mNELyJciRmk9CbpVCfFGfclfFtzBHXu")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("1")
//              .password("1")
                .password("{bcrypt}$2y$10$xUfqEqXXiziHNJDhdTQ3R.tBvx/uxqs3InZQSyhzyODggTLIMlwC.")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}