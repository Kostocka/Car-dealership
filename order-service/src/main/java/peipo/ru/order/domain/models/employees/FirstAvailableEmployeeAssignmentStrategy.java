package peipo.ru.order.domain.models.employees;

import java.util.List;
import org.springframework.stereotype.Component;
import peipo.ru.common.vo.id.EmployeeId;

@Component
public class FirstAvailableEmployeeAssignmentStrategy implements  EmployeeAssignmentStrategy
{
    @Override
    public EmployeeId assign(List<EmployeeId> employeeIds)
    {
        return EmployeeId.generate();
    }
}
