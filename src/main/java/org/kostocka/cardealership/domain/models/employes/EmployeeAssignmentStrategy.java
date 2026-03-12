package org.kostocka.cardealership.domain.models.employes;

import org.kostocka.cardealership.domain.vo.id.EmployeeId;

import java.util.List;

public interface EmployeeAssignmentStrategy
{
    EmployeeId assign(List<EmployeeId> employeeIds);
}
