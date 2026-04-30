package peipo.ru.storage.infrastructure.web.dto.cars;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest
{
    private CarConfigurationDto configuration;
}
