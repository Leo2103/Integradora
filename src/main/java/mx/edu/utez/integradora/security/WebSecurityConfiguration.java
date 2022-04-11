package mx.edu.utez.integradora.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private SimpleAuthenticationSuccessHandler successHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT correo, contrasenia, enabled FROM users WHERE correo = ?")
                .authoritiesByUsernameQuery("SELECT u.correo, r.authority FROM user_role AS ur "
                        +" JOIN users AS u ON u.id= ur.user_id "
                        +" JOIN role AS r ON r.id=ur.role_id WHERE u.correo= ? ");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(
                "/css/**", "/js/**", "/image/**", "/images/**").permitAll()
                .antMatchers("/", "/login","/crear","/encriptar/**").permitAll()
                .antMatchers("/administrador/**").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/ventanilla/**").hasAnyAuthority("ROLE_VENTANILLA", "ROLE_ADMIN")
                .antMatchers("/solicitante/**").hasAnyAuthority("ROLE_USER")
                .anyRequest().authenticated()
                .and().formLogin().successHandler(successHandler).loginPage("/login").permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

}
