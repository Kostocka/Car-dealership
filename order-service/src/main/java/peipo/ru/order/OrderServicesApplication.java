package peipo.ru.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "peipo.ru")
public class OrderServicesApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(OrderServicesApplication.class,  args);
    }
}
