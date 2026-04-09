package peipo.ru.cardealership.infrastructure.web.dto.parts.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peipo.ru.cardealership.domain.vo.GearBoxType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateGearBoxRequest
{
    private GearBoxType gearBoxType;
}
