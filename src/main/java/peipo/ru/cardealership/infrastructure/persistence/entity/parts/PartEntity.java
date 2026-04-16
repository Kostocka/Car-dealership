package peipo.ru.cardealership.infrastructure.persistence.entity.parts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.cardealership.infrastructure.persistence.entity.BaseEntity;

@Getter
@Setter
@MappedSuperclass
public abstract class PartEntity extends BaseEntity
{}