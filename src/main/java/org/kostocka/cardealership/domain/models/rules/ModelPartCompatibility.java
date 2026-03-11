package org.kostocka.cardealership.domain.models.rules;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.kostocka.cardealership.domain.vo.id.CarModelId;
import org.kostocka.cardealership.domain.vo.id.PartId;

@Getter
@AllArgsConstructor
public class ModelPartCompatibility
{
    private PartId partId;
    private CarModelId carModelId;
}
