package peipo.ru.cardealership.infrastructure.web.dto.parts.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddPartCompatibilityRequest
{
    private UUID firstPart;
    private UUID secondPart;
}
