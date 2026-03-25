package mx.com.qtx.dipArq07m05appWebSB.seguridad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class SeguridadWeb {
    private static final Logger log = LoggerFactory.getLogger(SeguridadWeb.class);

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("Configurando seguridad web");
        http
                .authorizeHttpRequests(auth -> auth.anyRequest()
                                                    .authenticated())

                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
	UserDetailsService userDetailsService() {
        UserDetails userDetailsAlex = User.withDefaultPasswordEncoder()
                                .username("alex")
                                .password("tekamachalko")
                                .roles("USER","AGENTE","ADMIN")
                                .build();
        UserDetails userDetailsDavid = User.withDefaultPasswordEncoder()
                                .username("david")
                                .password("tekolutla")
                                .roles("AGENTE")
                                .build();
        UserDetails userDetailsTavo = User.withDefaultPasswordEncoder()
                                .username("tavo")
                                .password("tlatelolko")
                                .roles("LOGISTICA")
                                .build();		                                    
        return new InMemoryUserDetailsManager(userDetailsAlex, userDetailsDavid, userDetailsTavo);
    }

}