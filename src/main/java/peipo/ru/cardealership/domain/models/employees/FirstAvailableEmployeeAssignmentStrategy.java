package peipo.ru.cardealership.domain.models.employees;

import java.util.List;

import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.exception.EntityNotFoundException;
import peipo.ru.cardealership.domain.vo.id.EmployeeId;

@Component
public class FirstAvailableEmployeeAssignmentStrategy implements  EmployeeAssignmentStrategy
{
    @Override
    public EmployeeId assign(List<EmployeeId> employeeIds)
    {
        return EmployeeId.generate();
    }
}
