package peipo.ru.common.dto.parts.requests;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddPartCompatibilityRequest
{
    private UUID firstPart;
    private UUID secondPart;
}
