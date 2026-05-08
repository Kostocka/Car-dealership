package peipo.ru.storage;

import java.util.List;
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
        List<String> tables = jdbcTemplate.queryForList(
                """
                SELECT tablename 
                FROM pg_tables 
                WHERE schemaname = 'public' 
                AND tablename NOT IN ('flyway_schema_history', 'schema_version')
                """,
                String.class
        );

        String truncateQuery = "TRUNCATE TABLE "
                +
                String.join(", ", tables)
                +
                " CASCADE";

        jdbcTemplate.execute(truncateQuery);
    }
}