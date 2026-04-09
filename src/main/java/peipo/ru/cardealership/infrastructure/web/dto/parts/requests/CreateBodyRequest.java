package peipo.ru.cardealership.infrastructure.web.dto.parts.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peipo.ru.cardealership.domain.vo.BodyType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBodyRequest
{
    private BodyType bodyType;
}
