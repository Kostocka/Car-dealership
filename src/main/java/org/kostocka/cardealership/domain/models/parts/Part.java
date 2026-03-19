package org.kostocka.cardealership.domain.models.parts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.kostocka.cardealership.domain.vo.id.PartId;

@Getter
@AllArgsConstructor
public abstract class Part
{
    private final PartId id;
}
