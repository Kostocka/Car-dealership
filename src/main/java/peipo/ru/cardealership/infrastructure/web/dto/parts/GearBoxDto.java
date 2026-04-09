package peipo.ru.cardealership.infrastructure.web.dto.parts;

import lombok.*;
import peipo.ru.cardealership.domain.vo.GearBoxType;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GearBoxDto
{
    private UUID id;
    private GearBoxType gearBoxType;
}