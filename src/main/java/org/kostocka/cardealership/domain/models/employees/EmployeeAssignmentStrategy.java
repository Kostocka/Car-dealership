package org.kostocka.cardealership.domain.models.employees;

import java.util.List;
import org.kostocka.cardealership.domain.vo.id.EmployeeId;

public interface EmployeeAssignmentStrategy
{
    EmployeeId assign(List<EmployeeId> employeeIds);
}
