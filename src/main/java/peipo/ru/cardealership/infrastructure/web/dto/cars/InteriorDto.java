package peipo.ru.cardealership.infrastructure.web.dto.cars;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InteriorDto
{
    private UUID id;
    private String material;
}