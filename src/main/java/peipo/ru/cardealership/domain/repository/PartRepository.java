package peipo.ru.cardealership.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import peipo.ru.cardealership.domain.models.parts.Part;
import peipo.ru.cardealership.domain.vo.id.PartId;

public interface PartRepository<T extends Part> extends JpaRepository<T, PartId>, JpaSpecificationExecutor<T>
{
}