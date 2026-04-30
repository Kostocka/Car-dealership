package peipo.ru.storage.infrastructure.persistence.entity.cars.parts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.common.vo.BodyType;

@Entity
@Table(name = "body")
@Getter
@Setter
public class BodyEntity extends PartEntity
{
    @Enumerated(EnumType.STRING)
    private BodyType bodyType;
}