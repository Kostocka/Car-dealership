package org.kostocka.cardealership.infrastructure;

import java.util.ArrayList;
import java.util.List;
import org.kostocka.cardealership.domain.repository.EmployeeRepository;
import org.kostocka.cardealership.domain.vo.id.EmployeeId;

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
