package mx.com.qtx.dipArq07m05appWebSB.webSeguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfiguracionSeguridad {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Paginas PUBLICAS
                .requestMatchers("/MenuCategorias.html","/","/categoria/**").permitAll()
                .requestMatchers("/css/**","/images/**").permitAll()
                // Paginas PRIVADAS
                .requestMatchers("/carrito/**").hasAnyRole("CLIENTE","ADMIN")
                .requestMatchers("/monitoreo/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/**","/v3/**","/swagger-ui/**").hasRole("AGENTE")
                .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults())
            .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        UserDetails userAlex = User
                .withDefaultPasswordEncoder()
                .username("alex")
                .password("tekamachalko")
                .roles("AGENTE","ADMIN")
                .build();
        UserDetails userDavid = User
                .withDefaultPasswordEncoder()
                .username("david")
                .password("tekolutla")
                .roles("CLIENTE")
                .build();
        UserDetails userTavo = User
                .withDefaultPasswordEncoder()
                .username("tavo")
                .password("tlatelolko")
                .roles("AGENTE")
                .build();
        return new InMemoryUserDetailsManager(userAlex, userDavid, userTavo);
    }

}
