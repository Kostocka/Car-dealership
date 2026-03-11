package org.kostocka.cardealership.domain.models.rules;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.kostocka.cardealership.domain.vo.id.PartId;

@Getter
@AllArgsConstructor
public class PartCompatibility
{
    private final PartId fisrtPartId;
    private final PartId secondPartId;
}
