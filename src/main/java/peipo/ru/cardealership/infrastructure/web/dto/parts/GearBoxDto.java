package peipo.ru.cardealership.infrastructure.web.dto.parts;

import java.util.UUID;
import lombok.*;
import peipo.ru.cardealership.domain.vo.GearBoxType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GearBoxDto
{
    private UUID id;
    private GearBoxType gearBoxType;
}