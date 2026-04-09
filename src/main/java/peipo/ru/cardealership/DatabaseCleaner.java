package peipo.ru.cardealership;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseCleaner
{
    private final JdbcTemplate jdbcTemplate;

    public void clean()
    {
        jdbcTemplate.execute(
            """
            TRUNCATE TABLE
                body,
                engine,
                gearbox,
                interior,
                wheels,
                car_model,
                car,
                configured_car_order,
                stock_car_order,
                test_drive,
                part_stock,
                part_price,
                part_compatibility,
                model_part_compatibility
            CASCADE
            """);
    }
}