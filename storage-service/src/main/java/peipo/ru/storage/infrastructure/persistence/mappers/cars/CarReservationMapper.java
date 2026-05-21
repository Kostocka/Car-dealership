package peipo.ru.storage.infrastructure.persistence.mappers.cars;

import org.springframework.stereotype.Component;
import peipo.ru.common.vo.id.CarId;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.storage.domain.models.CarReservation;
import peipo.ru.storage.infrastructure.persistence.entity.cars.CarReservationEntity;

@Component
public class CarReservationMapper
{
    public CarReservation toDomain(CarReservationEntity entity)
    {
        return new CarReservation(
                entity.getId(),
                new CarId(entity.getCarId()),
                new OrderId(entity.getOrderId()),
                entity.getCreatedAt(),
                entity.getStatus()
        );
    }

    public CarReservationEntity toEntity(CarReservation domain)
    {
        CarReservationEntity entity = new CarReservationEntity();

        entity.setId(domain.getId());
        entity.setCarId(domain.getCarId().id());
        entity.setOrderId(domain.getOrderId().id());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setStatus(domain.getStatus());

        return entity;
    }
}