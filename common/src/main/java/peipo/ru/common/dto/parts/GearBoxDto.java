package peipo.ru.common.dto.parts;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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