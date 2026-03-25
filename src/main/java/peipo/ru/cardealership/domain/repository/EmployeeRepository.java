package peipo.ru.cardealership.domain.repository;

import java.util.List;

import peipo.ru.cardealership.domain.vo.id.EmployeeId;

public interface EmployeeRepository
{
    List<EmployeeId> findAll();

    void save(EmployeeId id);
}
