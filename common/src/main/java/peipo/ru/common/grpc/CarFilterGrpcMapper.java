package peipo.ru.common.grpc;

import org.springframework.stereotype.Component;
import peipo.ru.common.dto.CarFilterDto;

@Component
public class CarFilterGrpcMapper
{
    public CarFilterDto toDto(CarFilterMessage message)
    {
        return new CarFilterDto(
                emptyToNull(message.getBrand()),
                emptyToNull(message.getModel()),
                emptyToNull(message.getGearBox()),
                emptyToNull(message.getColor()),
                message.getMinHorsePower() == 0 ? null : message.getMinHorsePower(),
                message.getMaxHorsePower() == 0 ? null : message.getMaxHorsePower()
        );
    }

    public CarFilterMessage toMessage(CarFilterDto dto)
    {
        return CarFilterMessage.newBuilder()
                .setBrand(dto.getBrand() == null ? "" : dto.getBrand())
                .setModel(dto.getModel() == null ? "" : dto.getModel())
                .setGearBox(dto.getGearBox() == null ? "" : dto.getGearBox())
                .setColor(dto.getColor() == null ? "" : dto.getColor())
                .setMinHorsePower(dto.getMinHorsePower() == null ? 0 : dto.getMinHorsePower())
                .setMaxHorsePower(dto.getMaxHorsePower() == null ? 0 : dto.getMaxHorsePower())
                .build();
    }

    private String emptyToNull(String value)
    {
        return value == null || value.isBlank() ? null : value;
    }
}