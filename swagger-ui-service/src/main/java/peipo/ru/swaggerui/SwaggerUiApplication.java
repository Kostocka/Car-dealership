package peipo.ru.swaggerui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "peipo.ru")
public class SwaggerUiApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SwaggerUiApplication.class, args);
    }
}