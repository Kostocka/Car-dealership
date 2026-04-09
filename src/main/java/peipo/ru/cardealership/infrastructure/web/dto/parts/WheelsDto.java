package peipo.ru.cardealership.infrastructure.web.dto.parts;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WheelsDto
{
    private UUID id;
    private int size;
}