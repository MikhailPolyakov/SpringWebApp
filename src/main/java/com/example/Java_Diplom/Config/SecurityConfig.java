package com.example.Java_Diplom.Config;


//import com.example.Java_Diplom.contollers.CustomFilter;
import com.example.Java_Diplom.models.Users;

//import com.example.Java_Diplom.security.CustomAuthenticationFailureHandler;
import com.example.Java_Diplom.security.CustomFilter;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {



    //для указания каким способос шифровать
    @Bean
    public PasswordEncoder getPasswordEncoder(){
return new BCryptPasswordEncoder();
      //  return new BCryptPasswordEncoder();
    }
    //@Bean
    //public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
   //     return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
 //   }
    @Bean
    public CustomFilter customAuthFilter(){
       return new CustomFilter();
    }

    //@Bean
    //SessionRegistry sessionRegistry()
    //{ return new SessionRegistryImpl(); }


    //настриваем форму для логина
    //какая статнцпчка за вход за ошибки
    //конфигурируем авторизацию
    //process_login" можем указать любой адрес куда отправляем  с формы
    ///auth/login?error параметр придет в наш контроллер и далее на предсталвение
    //antMatcher указывает,что доступа всем людям
    //permit all указывает,что всем доступна
    //anyRequest().authenticated(). указывает что все остальные странциы досутпны только
    //после аутентификации
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//отключаем токены для запросов(защита от межсайтовой подлелки запросов)
http.authorizeRequests().
        antMatchers("/admin/**").hasAnyRole("ADMIN","SUPER_ADMIN").
        antMatchers("/tryr").hasAuthority("READ").
        antMatchers("/auth/**","/registration/**","/error","/process","/auth/login/error").permitAll().
        anyRequest().authenticated().and()
        .formLogin().loginPage("/auth/login").
        loginProcessingUrl("/auth/login").  defaultSuccessUrl("/",true).
failureForwardUrl("/auth/login").and().logout().logoutUrl("/logout").logoutSuccessUrl("/auth/login")
        .and().sessionManagement()
                .maximumSessions(1).maxSessionsPreventsLogin(true).sessionRegistry(sessionRegistry());//.and().invalidSessionUrl(("/auth/login"));
        return http.build();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
       return new SessionRegistryImpl();
 }


@Bean
public HttpSessionEventPublisher httpSessionEventPublisher() {
    return new HttpSessionEventPublisher();
}

}
