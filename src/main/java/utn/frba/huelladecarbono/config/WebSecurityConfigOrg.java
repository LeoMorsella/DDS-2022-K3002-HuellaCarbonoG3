package utn.frba.huelladecarbono.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import utn.frba.huelladecarbono.service.IUsuarioService;

@Configuration
@EnableWebSecurity
@Order(2)
public class WebSecurityConfigOrg extends WebSecurityConfigurerAdapter{

    String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**","/static/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .antMatchers(resources).permitAll()
                .antMatchers("/static/css/**").permitAll()
                .antMatchers("/organizacion/recomendaciones").permitAll()
                .and()
                .formLogin()
                .loginPage("/loginOrganizacion")
                .permitAll()
                .defaultSuccessUrl("/organizacion/recomendaciones")
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