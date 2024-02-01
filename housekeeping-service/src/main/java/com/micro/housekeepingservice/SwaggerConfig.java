package com.micro.housekeepingservice;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {

        @Value("${bezkoder.openapi.dev-url}")
        private String devUrl;

        @Value("${bezkoder.openapi.prod-url}")
        private String prodUrl;

        @Bean
        public OpenAPI myOpenAPI() {
            Server devServer = new Server();
            devServer.setUrl(devUrl);
            devServer.setDescription("Server URL in Development environment");

            Server prodServer = new Server();
            prodServer.setUrl(prodUrl);
            prodServer.setDescription("Server URL in Production environment");

            Contact contact = new Contact();
            contact.setEmail("amey@gmail.com");
            contact.setName("Book-My-Con");
            contact.setUrl("https://www.bezkoder.com");

            License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

            Info info = new Info()
                    .title("Housekeeping")
                    .version("1.3.0")
                    .contact(contact)
                    .description("This API exposes endpoints to manage BookMyCon.");

            return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
        }
    }


