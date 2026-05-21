package peipo.ru.common.dto.parts;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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