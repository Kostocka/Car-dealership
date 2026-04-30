package peipo.ru.storage.infrastructure.web.dto.parts.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateWheelsRequest
{
    private int size;
}
