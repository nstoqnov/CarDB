package com.example.CarDB.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private CustomUserDetailsService cuds;

    @Autowired
    public SecurityConfig(CustomUserDetailsService cuds){
        this.cuds = cuds;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
   //@Bean
   //public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
   //    http.csrf().disable()
   //            .authorizeHttpRequests()
   //            .requestMatchers("/", "/home","/trips","/register","/login")
   //            .permitAll()
   //            .and()
   //            .formLogin(form -> form
   //                    .loginPage("/login").defaultSuccessUrl("/trips")
   //                    .loginProcessingUrl("/login")
   //                    .failureUrl("/login?error=true")
   //                    .permitAll()).logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
   //                    .permitAll());

   //    return http.build();

    //}
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/login", "/register", "/trips", "/css/**", "/js/**")
                .permitAll()
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/trips")
                        .loginProcessingUrl("/login")
                        .failureUrl("/login?error=true")
                        .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                );

        return http.build();
    }

    public void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(cuds).passwordEncoder(passwordEncoder());
    }
}