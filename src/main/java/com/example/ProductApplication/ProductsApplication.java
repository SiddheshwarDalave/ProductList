package com.example.ProductApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ControllerAdvice;

// OpenAPI for showing the application detail on Swagger
@OpenAPIDefinition(
		info = @Info(
				title = "Product Application",
				description = "This is Spring Boot Application",
				version = "v1",
				summary = "This is REST API application which is used for Product and Categroy maintance",
				termsOfService = "2025",
				//license = ""
				//extensions = "",

				contact = @Contact(
						name = "Siddheshwar Dalave",
						email = "sidd.dalave35@gmail.com",
						url = "www.google.com"

				)
		)
)
@SpringBootApplication
public class ProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}

}
