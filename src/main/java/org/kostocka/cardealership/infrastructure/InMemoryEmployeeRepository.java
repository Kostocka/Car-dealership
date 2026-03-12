package org.kostocka.cardealership.infrastructure;

import org.kostocka.cardealership.domain.repository.EmployeeRepository;
import org.kostocka.cardealership.domain.vo.id.EmployeeId;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEmployeeRepository implements EmployeeRepository
{
    private final List<EmployeeId> employees = new ArrayList<>();

    @Override
    public List<EmployeeId> findAll() {
        return employees;
    }
}
