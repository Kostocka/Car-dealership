package peipo.ru.storage.infrastructure.web.dto.parts;

import java.util.UUID;
import lombok.*;
import peipo.ru.common.vo.BodyType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BodyDto
{
    private UUID id;
    private BodyType bodyType;
}