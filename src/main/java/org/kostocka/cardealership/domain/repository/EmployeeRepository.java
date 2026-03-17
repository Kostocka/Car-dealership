package org.kostocka.cardealership.domain.repository;

import java.util.List;
import org.kostocka.cardealership.domain.vo.id.EmployeeId;

public interface EmployeeRepository
{
    List<EmployeeId> findAll();

    void save(EmployeeId id);
}
