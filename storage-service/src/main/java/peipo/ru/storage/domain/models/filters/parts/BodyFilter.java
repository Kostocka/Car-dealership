package peipo.ru.storage.domain.models.filters.parts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.models.filters.Filter;
import peipo.ru.common.vo.BodyType;

@AllArgsConstructor
@Getter
public class BodyFilter implements Filter<CarModel>
{
    private final BodyType type;

    @Override
    public boolean isSatisfiedBy(CarModel entity)
    {
        return entity.getBody().getType() == this.type;
    }
}