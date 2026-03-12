package org.kostocka.cardealership.domain.models.employes;

import org.kostocka.cardealership.domain.exception.EntityNotFoundException;
import org.kostocka.cardealership.domain.vo.id.EmployeeId;
import java.util.List;

public class FirstAvailableEmployeeAssignmentStrategy implements  EmployeeAssignmentStrategy
{
    @Override
    public EmployeeId assign(List<EmployeeId> employeeIds)
    {
        if(employeeIds == null || employeeIds.isEmpty())
        {
            throw new EntityNotFoundException("employeeIds cannot be null or empty");
        }
        return employeeIds.get(0);
    }
}
