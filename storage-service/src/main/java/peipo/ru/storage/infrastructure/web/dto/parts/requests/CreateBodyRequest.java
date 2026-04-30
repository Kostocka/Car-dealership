package peipo.ru.storage.infrastructure.web.dto.parts.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peipo.ru.common.vo.BodyType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBodyRequest
{
    private BodyType bodyType;
}
