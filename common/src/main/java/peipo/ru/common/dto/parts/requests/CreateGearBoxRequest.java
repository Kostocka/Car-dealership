package peipo.ru.common.dto.parts.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peipo.ru.common.vo.GearBoxType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateGearBoxRequest
{
    private GearBoxType gearBoxType;
}
