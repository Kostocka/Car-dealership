package peipo.ru.cardealership.infrastructure.web.dto.parts;

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