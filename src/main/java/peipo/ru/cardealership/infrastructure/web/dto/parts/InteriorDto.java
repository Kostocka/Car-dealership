package peipo.ru.cardealership.infrastructure.web.dto.parts;

import java.util.UUID;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InteriorDto
{
    private UUID id;
    private String material;
}