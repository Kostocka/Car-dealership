package org.kostocka.cardealership.infrastructure;

import org.kostocka.cardealership.domain.repository.EmployeeRepository;
import org.kostocka.cardealership.domain.vo.id.EmployeeId;

import java.util.List;

public class InMemoryEmployeeRepository implements EmployeeRepository
{
    private List<EmployeeId> employees;

    @Override
    public List<EmployeeId> findAll() {
        return employees;
    }
}
