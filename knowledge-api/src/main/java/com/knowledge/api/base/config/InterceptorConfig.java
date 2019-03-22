package com.knowledge.api.base.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 跨域配置
 * 
 * @author francis
 *
 */
@Configuration
@EnableSwagger2
public class InterceptorConfig implements WebMvcConfigurer {

	@Bean
	public AuthenticationInterceptor getTokenInterceptor() {
		return new AuthenticationInterceptor();
	}

/*	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowCredentials(true)
				.allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS").maxAge(3600);
	}
	private CorsConfiguration buildConfig() {
	CorsConfiguration corsConfiguration = new CorsConfiguration();
    List<String> list = new ArrayList<>();
    list.add("*");
    corsConfiguration.setAllowedOrigins(list);
    
    // 请求常用的三种配置，*代表允许所有，当时你也可以自定义属性（比如header只能带什么，只能是post方式等等）
    
    corsConfiguration.addAllowedOrigin("*");  
    corsConfiguration.addAllowedHeader("*"); 
    corsConfiguration.addAllowedMethod("*");  
    return corsConfiguration;
}*/
/*@Bean
public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", buildConfig());
    return new CorsFilter((CorsConfigurationSource) source);
}
*/

private CorsConfiguration buildConfig() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    //  你需要跨域的地址  注意这里的 127.0.0.1 != localhost
    // * 表示对所有的地址都可以访问
    corsConfiguration.addAllowedOrigin("*");
    //  跨域的请求头
    corsConfiguration.addAllowedHeader("*"); // 2
    //  跨域的请求方法
    corsConfiguration.addAllowedMethod("*"); // 3
    //加上了这一句，大致意思是可以携带 cookie
    //最终的结果是可以 在跨域请求的时候获取同一个 session
    corsConfiguration.setAllowCredentials(true);
    return corsConfiguration;
}
@Bean
public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //配置 可以访问的地址
    source.registerCorsConfiguration("/**", buildConfig()); // 4
    return new CorsFilter(source);
}


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getTokenInterceptor())
				// 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
				// .addPathPatterns("/")
				.excludePathPatterns("/swagger-resources/**", 
						"/webjars/**",
						"/v2/**", 
						"/swagger-ui.html/**",
						"/api/**"
					);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	@Bean
	public Docket createRestApi() {
		ParameterBuilder tokenPar = new ParameterBuilder();  
        List<Parameter> pars = new ArrayList<Parameter>();  
        tokenPar.name("token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();  
        pars.add(tokenPar.build()); 
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("api")
				.apiInfo(apiInfo())
				.tags(new Tag("Knowledge", "Knowledge"), getTags())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.knowledge"))
				.paths(PathSelectors.any())
				.build()
				.globalOperationParameters(pars);  
				
	}

	/**
	 * @return
	 */
	private Tag[] getTags() {
		Tag[] tags = {};
		return tags;
	}

	/**
	 * @return
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("紫荊知識網").version("1.0").build();
	}

	/**
	 * 防止@EnableMvc把默认的静态资源路径覆盖了，手动设置的方式
	 * 
	 * @param registry
	 */
/*	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/").setCachePeriod(0);
	}*/
}
