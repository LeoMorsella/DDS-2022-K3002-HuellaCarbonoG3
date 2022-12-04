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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/js/**", "/css/**.").permitAll()
                .antMatchers("/","/index").permitAll()
                .antMatchers("/organizacion/recomendaciones").permitAll()
                .antMatchers("/organizacion/miembros").permitAll()
                .antMatchers("/{id}/areas").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/menuUsuario")
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