package peipo.ru.cardealership.infrastructure.inMemory;

import java.util.ArrayList;
import java.util.List;
import peipo.ru.cardealership.domain.repository.EmployeeRepository;
import peipo.ru.cardealership.domain.vo.id.EmployeeId;

public class InMemoryEmployeeRepository implements EmployeeRepository
{
    private final List<EmployeeId> employees = new ArrayList<>();

    @Override
    public List<EmployeeId> findAll()
    {
        return employees;
    }

    @Override
    public void save(EmployeeId id)
    {
        employees.add(id);
    }
}
