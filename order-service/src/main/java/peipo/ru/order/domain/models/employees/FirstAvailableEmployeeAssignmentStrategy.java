package peipo.ru.order.domain.models.employees;

import java.util.List;
import org.springframework.stereotype.Component;
import peipo.ru.order.domain.vo.id.EmployeeId;

@Component
public class FirstAvailableEmployeeAssignmentStrategy implements  EmployeeAssignmentStrategy
{
    @Override
    public EmployeeId assign(List<EmployeeId> employeeIds)
    {
        return EmployeeId.generate();
    }
}
