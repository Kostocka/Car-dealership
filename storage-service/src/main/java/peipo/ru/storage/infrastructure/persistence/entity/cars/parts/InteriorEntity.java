package peipo.ru.storage.infrastructure.persistence.entity.cars.parts;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "interior")
@Getter
@Setter
public class InteriorEntity extends PartEntity
{
    private String material;
}
