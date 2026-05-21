package peipo.ru.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SeederConfig
{
    private final DatabaseSeeder databaseSeeder;

    @Bean
    public CommandLineRunner init()
    {
        return args -> databaseSeeder.seed();
    }
}