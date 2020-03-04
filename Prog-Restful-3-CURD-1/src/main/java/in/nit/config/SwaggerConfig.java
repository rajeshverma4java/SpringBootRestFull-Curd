package in.nit.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Configuration;

import com.sun.xml.bind.v2.schemagen.xmlschema.Appinfo;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public Docket configure() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.basePackage("in.nit.controller.rest"))
				.paths(PathSelectors.regex("/rest.*"))
				.build().apiInfo(apiInfo());
		
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfo("Nit BOot app", "sample desc",
				"3.1", "http://nareshit.in", new Contact("RR", "http://nareshit.in",
						"naresh@gmail.com"), "Nit Licence", "http://nareshit.in", new ArrayList<>());
		
	}
}

