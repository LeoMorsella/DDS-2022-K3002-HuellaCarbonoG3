package utn.frba.huelladecarbono.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import utn.frba.huelladecarbono.service.IUsuarioService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**","/static/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(resources).permitAll()
                .antMatchers("/static/css/**").permitAll()
                .antMatchers("/","/index").permitAll()
                .antMatchers("/organizacion/recomendaciones").permitAll()
                .antMatchers("/organizacion/miembros").permitAll()
                .antMatchers("/organizacion/orgRecomendaciones").permitAll()
                .antMatchers("/organizacion/contactos").permitAll()
                .antMatchers("/organizaciones/{id}/contactosMail").permitAll()
                .antMatchers("/organizaciones/{id}/contactosWp").permitAll()
                .antMatchers("/{id}/areas").permitAll()
                .antMatchers("/organizaciones/{ID}").permitAll()
                .antMatchers("/organizacion/datosInternos").permitAll()
                .antMatchers("/organizaciones/areas/{id}").permitAll()
                .antMatchers("/miembros/{id}").permitAll()
                .antMatchers("/miembros").permitAll()
                .antMatchers("/miembro/datosPersonales").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/miembroDatosPersonales").permitAll()
                .antMatchers("/miembro/crear").permitAll()
                .antMatchers("/orgMiembros").permitAll()
                .antMatchers("/orgRecomendaciones").permitAll()
                .antMatchers("/ubicacion/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/miembroDatosPersonales")
                .failureUrl("/login?error=true")
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout");
    }

    @Autowired
    IUsuarioService userDetailsService;

    //Registra el service para usuarios y el encriptador de contrasenaº
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

}