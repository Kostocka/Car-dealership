package peipo.ru.cardealership.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import peipo.ru.cardealership.domain.vo.id.EmployeeId;

public interface EmployeeRepository
{
    List<EmployeeId> findAll();

    void save(EmployeeId id);
}
