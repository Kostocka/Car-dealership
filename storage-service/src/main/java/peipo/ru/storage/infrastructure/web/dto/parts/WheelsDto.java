package peipo.ru.storage.infrastructure.web.dto.parts;

import java.util.UUID;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WheelsDto
{
    private UUID id;
    private int size;
}