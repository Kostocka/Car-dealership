package peipo.ru.cardealership.infrastructure.persistence.entity.parts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.cardealership.domain.vo.BodyType;

@Entity
@Table(name = "body")
@Getter
@Setter
public class BodyEntity extends PartEntity
{
    @Enumerated(EnumType.STRING)
    private BodyType bodyType;
}