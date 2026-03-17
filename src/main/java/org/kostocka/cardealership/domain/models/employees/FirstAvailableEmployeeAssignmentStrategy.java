package org.kostocka.cardealership.domain.models.employees;

import java.util.List;
import org.kostocka.cardealership.domain.exception.EntityNotFoundException;
import org.kostocka.cardealership.domain.vo.id.EmployeeId;

public class FirstAvailableEmployeeAssignmentStrategy implements  EmployeeAssignmentStrategy
{
    @Override
    public EmployeeId assign(List<EmployeeId> employeeIds)
    {
        if (employeeIds == null || employeeIds.isEmpty())
        {
            throw new EntityNotFoundException("employeeIds cannot be null or empty");
        }
        return employeeIds.getFirst();
    }
}
