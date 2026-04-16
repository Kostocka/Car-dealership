package peipo.ru.cardealership.domain.models.employees;

import java.util.List;
import peipo.ru.cardealership.domain.vo.id.EmployeeId;

public interface EmployeeAssignmentStrategy
{
    EmployeeId assign(List<EmployeeId> employeeIds);
}
