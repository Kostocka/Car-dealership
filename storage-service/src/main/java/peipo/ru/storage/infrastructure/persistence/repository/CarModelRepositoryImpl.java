package peipo.ru.storage.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.repository.CarModelRepository;
import peipo.ru.storage.infrastructure.persistence.jparepositorys.CarModelJpaRepository;
import peipo.ru.storage.infrastructure.persistence.mappers.cars.CarModelMapper;

@Repository
@RequiredArgsConstructor
public class CarModelRepositoryImpl implements CarModelRepository
{
    private final CarModelJpaRepository carModelJpaRepository;
    private final CarModelMapper carModelMapper;

    @Override
    public List<CarModel> findAll()
    {
        return carModelJpaRepository.findAll()
                .stream()
                .map(carModelMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<CarModel> findById(CarModelId id)
    {
        return carModelJpaRepository.findById(id.id())
                .map(carModelMapper::toDomain);
    }

    @Override
    public CarModel save(CarModel entity)
    {
        return carModelMapper.toDomain(carModelJpaRepository.save(carModelMapper.toEntity(entity)));
    }

    @Override
    public void delete(CarModelId id)
    {
        carModelJpaRepository.deleteById(id.id());
    }
}
