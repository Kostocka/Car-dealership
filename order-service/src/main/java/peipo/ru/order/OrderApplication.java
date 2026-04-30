package peipo.ru.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "peipo.ru")
public class OrderApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(OrderApplication.class);
    }
}
