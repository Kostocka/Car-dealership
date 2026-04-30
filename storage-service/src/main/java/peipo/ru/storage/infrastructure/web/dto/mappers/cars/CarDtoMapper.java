package peipo.ru.storage.infrastructure.web.dto.mappers.cars;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.storage.domain.models.Car;
import peipo.ru.common.vo.id.CarId;
import peipo.ru.storage.infrastructure.web.dto.cars.CarResponseDto;
import peipo.ru.storage.infrastructure.web.dto.cars.CreateCarRequest;

@Component
@RequiredArgsConstructor
public class CarDtoMapper
{
    private final CarModelDtoMapper carModelDtoMapper;

    public Car toDomain(CreateCarRequest request)
    {
        return new Car(
                CarId.generate(),
                carModelDtoMapper.toDomain(request.getConfiguration())
        );
    }

    public CarResponseDto toDto(Car car)
    {
        return new CarResponseDto(
                car.getCarId().id(),
                carModelDtoMapper.toDto(car.getConfiguration())
        );
    }
}
