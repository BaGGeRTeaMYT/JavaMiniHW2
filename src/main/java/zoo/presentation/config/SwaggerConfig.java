package zoo.presentation.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI zooOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Zoo Management API")
                        .description("API для управления зоопарком")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("АНДРЮХА (не спамьте, пжпж)")
                                .email("real_email_adress@pochta.com")));
    }
}