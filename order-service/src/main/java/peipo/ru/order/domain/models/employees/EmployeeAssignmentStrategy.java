package peipo.ru.order.domain.models.employees;

import java.util.List;
import peipo.ru.common.vo.id.EmployeeId;

public interface EmployeeAssignmentStrategy
{
    EmployeeId assign(List<EmployeeId> employeeIds);
}
