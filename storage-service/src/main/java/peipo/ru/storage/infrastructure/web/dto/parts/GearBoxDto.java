package peipo.ru.storage.infrastructure.web.dto.parts;

import java.util.UUID;
import lombok.*;
import peipo.ru.common.vo.GearBoxType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GearBoxDto
{
    private UUID id;
    private GearBoxType gearBoxType;
}