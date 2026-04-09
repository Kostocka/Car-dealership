package peipo.ru.cardealership.infrastructure.web.dto.parts;

import java.util.UUID;
import lombok.*;
import peipo.ru.cardealership.domain.vo.BodyType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BodyDto
{
    private UUID id;
    private BodyType bodyType;
}