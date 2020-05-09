package com.group3.Login.Services;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.cors();
		
		http.authorizeRequests()
		.antMatchers("/adminMainPageRequest",
					"/adminLogout","/addCoursePageRequest",
					"/addCourse","/DeleteCoursePage",
					"/deleteCourse","/grantInstructorPage",
					"/GrantRoleRequest","/ViewCoursesPage").hasRole("ADMIN")
		
		.antMatchers("/selectCourse").hasAnyRole("TA","INSTRUCTOR","STUDENT")
		.antMatchers("/importCSV","/upload-csv-file",
					"/showAllStudents","/searchStudent","/addTA").hasAnyRole("TA","INSTRUCTOR")
		
		
		.antMatchers("/","/formSubmit").permitAll()
		.and()
		.formLogin()
			.loginPage("/login")
			.usernameParameter("email")
			.successHandler(myAuthenticationSuccessHandler())
			.permitAll()
			.and()
		.logout()
			.permitAll()
		.and()
		.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
		}
		

	  @Override
	  public void configure(AuthenticationManagerBuilder builder)
	          throws Exception {
	      builder.userDetailsService(new UserService());
	  }
	  
	  @Bean
	  public AccessDeniedHandler accessDeniedHandler(){
	      return new CustomAccessDeniedHandler();
	  }
	  
	 
	  @Bean
	  public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
          return new LoginAuthenticationSuccessHandler();
	  }
	 
}