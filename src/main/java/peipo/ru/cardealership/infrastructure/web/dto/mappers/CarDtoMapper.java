package peipo.ru.cardealership.infrastructure.web.dto.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.Car;
import peipo.ru.cardealership.domain.vo.id.CarId;
import peipo.ru.cardealership.infrastructure.web.dto.cars.CarResponseDto;
import peipo.ru.cardealership.infrastructure.web.dto.cars.CreateCarRequest;

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
