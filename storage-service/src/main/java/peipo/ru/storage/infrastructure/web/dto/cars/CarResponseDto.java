package peipo.ru.storage.infrastructure.web.dto.cars;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarResponseDto
{
    private UUID id;
    private CarConfigurationDto configuration;
}