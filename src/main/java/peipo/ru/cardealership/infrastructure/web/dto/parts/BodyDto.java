package peipo.ru.cardealership.infrastructure.web.dto.parts;

import lombok.*;
import peipo.ru.cardealership.domain.vo.BodyType;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BodyDto
{
    private UUID id;
    private BodyType bodyType;
}