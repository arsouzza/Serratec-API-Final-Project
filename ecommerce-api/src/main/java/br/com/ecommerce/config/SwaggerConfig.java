package br.com.ecommerce.config;

import java.util.List;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
	
	@Value("${dominio.openapi.dev-uri}")
	private String devUri;
	@Value("${dominio.openapi.prod-uri}")
	private String devProd;

	@Bean
	OpenAPI myOpenApi() {
		Server devServer = new Server();
		devServer.setUrl(devUri);
		devServer.setDescription("uri do servidor de Dev");

		Server prodServer = new Server();
		prodServer.setUrl(devProd);
		prodServer.setDescription("uri do servidor de Prod");

		Contact contato = new Contact();
		contato.setEmail("Will@gmail.com");
		contato.setName("William");
		contato.setUrl("www.meudominio.com");

		Info info = new Info().title("API DO E-COMMERCE").version("1.0").contact(contato)
				.termsOfService("Api usada para o e-commerce");

		return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
	}

}