package peipo.ru.order.infrastructure.persistence.repositorys;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import peipo.ru.order.domain.repository.EmployeeRepository;
import peipo.ru.common.vo.id.EmployeeId;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository
{
    private final List<EmployeeId> employes = new ArrayList<>();

    @Override
    public List<EmployeeId> findAll()
    {
        return employes;
    }

    @Override
    public void save(EmployeeId id)
    {
        employes.add(id);
    }
}
