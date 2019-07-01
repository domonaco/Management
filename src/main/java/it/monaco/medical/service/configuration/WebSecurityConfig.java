package it.monaco.medical.service.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.GzipResourceResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.util.UrlPathHelper;

@EnableWebSecurity
@ComponentScan
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("home");
        registry.addViewController("/search").setViewName("search");
        registry.addViewController("/insert").setViewName("insert");
        registry.addViewController("/searchResult").setViewName("searchResult");
        registry.addViewController("/searchResultError").setViewName("searchResultError+");
        registry.addViewController("/home").setViewName("home");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**").allowedOrigins("*");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/register").permitAll().antMatchers("/login").permitAll();

        http.csrf().disable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/", "classpath:/resources/").setCachePeriod(31556926);
        registry.addResourceHandler("/resources/static/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(3600).resourceChain(true).addResolver(new GzipResourceResolver()).addResolver(new PathResourceResolver());
        registry.addResourceHandler("/ania/sita/home").addResourceLocations("/index.html");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

    }

    @Bean
    public ViewResolver viewResolver() {

        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
        viewResolver.setViewClass(InternalResourceView.class);
        return viewResolver;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

        UrlPathHelper pathHelper = new UrlPathHelper();
        pathHelper.setRemoveSemicolonContent(false); // For @MatrixVariable's
        configurer.setUrlPathHelper(pathHelper);
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

        configurer.setDefaultTimeout(3000);
        configurer.registerCallableInterceptors(new TimeoutCallableProcessingInterceptor());
    }

    @Bean
    public MultipartResolver multipartResolver() {

        return new CommonsMultipartResolver();
    }

    @Bean
    public ErrorPageFilter errorPageFilter() {

        return new ErrorPageFilter();
    }

    @Bean
    public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}

