package org.kostocka.cardealership.domain.sevices;

import lombok.AllArgsConstructor;
import org.kostocka.cardealership.domain.models.employes.EmployeeAssignmentStrategy;
import org.kostocka.cardealership.domain.repository.EmployeeRepository;
import org.kostocka.cardealership.domain.vo.id.EmployeeId;

import java.util.List;

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
