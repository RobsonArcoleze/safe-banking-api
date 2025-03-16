package br.com.tokiomarine.safebankingapi.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Safebanking API")
                .description("Safebanking API - API de Agendamento de Tranfências Bancárias")
                .version("1.0"));

    }
}
