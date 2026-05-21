package peipo.ru.order.domain.services;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peipo.ru.common.vo.id.EmployeeId;
import peipo.ru.order.domain.models.employees.EmployeeAssignmentStrategy;
import peipo.ru.order.domain.repository.EmployeeRepository;

@Service
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
