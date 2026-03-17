package org.kostocka.cardealership.domain.services;

import java.util.List;
import lombok.AllArgsConstructor;
import org.kostocka.cardealership.domain.models.employees.EmployeeAssignmentStrategy;
import org.kostocka.cardealership.domain.repository.EmployeeRepository;
import org.kostocka.cardealership.domain.vo.id.EmployeeId;

@AllArgsConstructor
public class EmployeeAssignmentService
{
    private final EmployeeRepository employeeRepository;
    private final EmployeeAssignmentStrategy employeeAssignmentStrategy;

    public EmployeeId assignManager()
    {
        List<EmployeeId> managers = employeeRepository.findAll();
        return employeeAssignmentStrategy.assign(managers);
    }
}
