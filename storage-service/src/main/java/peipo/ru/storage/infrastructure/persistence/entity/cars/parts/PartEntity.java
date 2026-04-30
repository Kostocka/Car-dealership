package peipo.ru.storage.infrastructure.persistence.entity.cars.parts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import peipo.ru.storage.infrastructure.persistence.entity.BaseEntity;

@Getter
@Setter
@MappedSuperclass
public abstract class PartEntity extends BaseEntity
{}