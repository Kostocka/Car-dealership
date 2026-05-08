package peipo.ru.storage.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import peipo.ru.common.vo.id.CarId;
import peipo.ru.storage.domain.models.Car;
import peipo.ru.storage.domain.models.CarModel;
import peipo.ru.storage.domain.models.filters.Filter;
import peipo.ru.storage.domain.repository.CarRepository;
import peipo.ru.storage.infrastructure.persistence.entity.cars.CarEntity;
import peipo.ru.storage.infrastructure.persistence.jparepositorys.CarJpaRepository;
import peipo.ru.storage.infrastructure.persistence.mappers.FilterMapper;
import peipo.ru.storage.infrastructure.persistence.mappers.cars.CarMapper;

@Repository
@RequiredArgsConstructor
public class CarRepositoryImpl implements CarRepository
{
    private final CarJpaRepository carJpaRepository;
    private final CarMapper carMapper;
    private final FilterMapper filterMapper;

    @Override
    public List<Car> findAll(Filter<CarModel> filter)
    {
        Specification<CarEntity> spec = filterMapper.resolve(filter);

        return carJpaRepository.findAll(spec)
                .stream()
                .map(carMapper::toDomain)
                .toList();
    }

    @Override
    public List<Car> findAll()
    {
        return carJpaRepository.findAll()
                .stream()
                .map(carMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Car> findById(CarId id)
    {
        return carJpaRepository.findById(id.id())
                .map(carMapper::toDomain);
    }

    @Override
    public Car save(Car entity)
    {
        return carMapper.toDomain(carJpaRepository.save(carMapper.toEntity(entity)));
    }

    @Override
    public void delete(CarId id)
    {
        carJpaRepository.deleteById(id.id());
    }
}
