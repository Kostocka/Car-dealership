package peipo.ru.storage.infrastructure.web.dto.parts.requests;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddModelPartCompatibilityRequest
{
    private UUID partId;
    private UUID modelId;
}