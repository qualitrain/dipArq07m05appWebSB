package mx.com.qtx.dipArq07m05appWebSB.webSeguridad;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import mx.com.qtx.dipArq07m05appWebSB.web.monitoreo.FiltroMonitoreoWeb;
import mx.com.qtx.dipArq07m05appWebSB.web.monitoreo.ServicioMonitoreo;

@Configuration
public class ConfiguracionSeguridad {
    private final ServicioMonitoreo servicioMonitoreo;
    private static final Logger log = LoggerFactory.getLogger(ConfiguracionSeguridad.class);

    public ConfiguracionSeguridad(ServicioMonitoreo servicioMonitoreo) {
        this.servicioMonitoreo = servicioMonitoreo;
    }

    @Bean
    @ConfigurationProperties("spring.datasource.security")
    public DataSourceProperties seguridadProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public AuthenticationManager publicarAuthenticationManager(AuthenticationConfiguration authConfig) {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DataSource seguridadDataSource(@Qualifier("seguridadProperties") DataSourceProperties seguridadProperties) {
        log.info("Creando DataSource para seguridad");
        return seguridadProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Paginas PUBLICAS
                        .requestMatchers("/MenuCategorias.html", "/", "/categoria/**").permitAll()
                        .requestMatchers("/css/**", "/images/**").permitAll()
                        // Paginas PRIVADAS
                        .requestMatchers("/carrito/**").hasAnyRole("CLIENTE", "ADMIN")
                        .requestMatchers("/monitoreo/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/**", "/v3/**", "/swagger-ui/**").hasRole("AGENTE")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .addFilterAfter(new FiltroMonitoreoWeb(servicioMonitoreo),
                        SecurityContextHolderAwareRequestFilter.class);

        return http.build();
    }

    // @Bean
    UserDetailsService userDetailsService() {
        UserDetails userAlex = User
                .withDefaultPasswordEncoder()
                .username("alex")
                .password("tekamachalko")
                .roles("AGENTE", "ADMIN")
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

    @Bean
    UserDetailsManager getGestorBdUsuarios(@Qualifier("seguridadDataSource") DataSource dataSource) {
        UserDetails usuarioAlex = User.withUsername("alex")
                .password("{bcrypt}$2a$10$YCRvR3nqYDHuojZsg4W.AejFi5gGv..4U/Ne4qexYanAFFfBQfN3q")
                .roles("USER", "AGENTE", "ADMIN")
                .build();
        UserDetails usuarioDavid = User.withUsername("david")
                .password("{bcrypt}$2a$10$/0fVLP6pUQTik4gY3s/gS.vkWHgh6YufNp.dsbh.CU0KiuOHT8hdm").roles("AGENTE")
                .build();
        UserDetails usuarioTavo = User.withUsername("tavo")
                .password("{bcrypt}$2a$10$NieJQXCisKwNGZLi7imneekU7hcn57ClvRj4MASWJIusqsCPbH/Bi").roles("LOGISTICA")
                .build();
        JdbcUserDetailsManager gestorUsuariosBD = new JdbcUserDetailsManager(dataSource);
        if (gestorUsuariosBD.userExists(usuarioAlex.getUsername()) == false)
            gestorUsuariosBD.createUser(usuarioAlex);
        if (gestorUsuariosBD.userExists(usuarioDavid.getUsername()) == false)
            gestorUsuariosBD.createUser(usuarioDavid);
        if (gestorUsuariosBD.userExists(usuarioTavo.getUsername()) == false)
            gestorUsuariosBD.createUser(usuarioTavo);
        return gestorUsuariosBD;
    }

}
