package peipo.ru.order.domain.repository;

import java.util.List;
import peipo.ru.common.vo.id.EmployeeId;

public interface EmployeeRepository
{
    List<EmployeeId> findAll();

    void save(EmployeeId id);
}
