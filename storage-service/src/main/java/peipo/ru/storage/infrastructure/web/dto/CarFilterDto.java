package peipo.ru.storage.infrastructure.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarFilterDto
{
    private String brand;
    private String model;
    private String gearBox;
    private String color;
    private Integer minHorsePower;
    private Integer maxHorsePower;
}
