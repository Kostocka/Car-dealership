package peipo.ru.cardealership.infrastructure.repositorys;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peipo.ru.cardealership.domain.models.TestDrive;
import peipo.ru.cardealership.domain.repository.TestDriveRepository;
import peipo.ru.cardealership.domain.vo.id.TestDriveId;
import peipo.ru.cardealership.infrastructure.jparepositorys.TestDriveJpaRepository;
import peipo.ru.cardealership.infrastructure.mapper.TestDriveMapper;

@Repository
@RequiredArgsConstructor
public class TestDriveRepositoryImpl implements TestDriveRepository
{
    private final TestDriveJpaRepository testDriveJpaRepository;
    private final TestDriveMapper testDriveMapper;

    @Override
    public Optional<TestDrive> findById(TestDriveId id)
    {
        return testDriveJpaRepository.findById(id.id()).map(testDriveMapper::toDomain);
    }

    @Override
    public List<TestDrive> findAll()
    {
        return testDriveJpaRepository.findAll()
                .stream()
                .map(testDriveMapper::toDomain)
                .toList();
    }

    @Override
    public void save(TestDrive entity)
    {
        testDriveJpaRepository.save(testDriveMapper.toEntity(entity));
    }

    @Override
    public void delete(TestDriveId id)
    {
        testDriveJpaRepository.deleteById(id.id());
    }
}
