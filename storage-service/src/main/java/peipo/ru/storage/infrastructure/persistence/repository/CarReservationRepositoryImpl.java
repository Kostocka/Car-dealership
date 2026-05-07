package peipo.ru.storage.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.common.vo.id.CarId;
import peipo.ru.common.vo.id.OrderId;
import peipo.ru.storage.domain.models.CarReservation;
import peipo.ru.storage.domain.models.ReservationStatus;
import peipo.ru.storage.domain.repository.CarReservationRepository;
import peipo.ru.storage.infrastructure.persistence.jparepositorys.CarReservationJpaRepository;
import peipo.ru.storage.infrastructure.persistence.mappers.cars.CarReservationMapper;

@Repository
@AllArgsConstructor
public class CarReservationRepositoryImpl implements CarReservationRepository
{
    private final CarReservationJpaRepository jpa;
    private final CarReservationMapper mapper;

    @Override
    public boolean existsActiveByCarId(CarId carId)
    {
        return jpa.existsByCarIdAndStatus(
                carId.id(),
                ReservationStatus.ACTIVE
        );
    }

    @Override
    public Optional<CarReservation> findActiveByCarId(CarId carId)
    {
        return jpa.findByCarIdAndStatus(
                        carId.id(),
                        ReservationStatus.ACTIVE
                )
                .map(mapper::toDomain);
    }

    @Override
    public Optional<CarReservation> findById(UUID id)
    {
        return jpa.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<CarReservation> findAll()
    {
        return jpa.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public CarReservation save(CarReservation reservation)
    {
        return mapper.toDomain(
                jpa.save(mapper.toEntity(reservation))
        );
    }

    @Override
    public void delete(UUID id)
    {
        jpa.deleteById(id);
    }

    @Override
    public Optional<CarReservation> findByOrderId(OrderId orderId)
    {
        return jpa.findByOrderId(orderId.id())
                .map(mapper::toDomain);
    }
}