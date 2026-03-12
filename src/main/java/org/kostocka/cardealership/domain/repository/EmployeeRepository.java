package org.kostocka.cardealership.domain.repository;

import org.kostocka.cardealership.domain.vo.id.EmployeeId;

import java.util.List;

public interface EmployeeRepository
{
    List<EmployeeId> findAll();
}
