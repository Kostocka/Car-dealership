package peipo.ru.storage.infrastructure.grpc;

import org.springframework.stereotype.Component;
import peipo.ru.common.dto.CarFilterDto;
import peipo.ru.storage.grpc.CarFilterMessage;

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
                message.getMinHorsePower() == 0
                        ? null
                        : message.getMinHorsePower(),
                message.getMaxHorsePower() == 0
                        ? null
                        : message.getMaxHorsePower()
        );
    }

    private String emptyToNull(String value)
    {
        return value == null || value.isBlank()
                ? null
                : value;
    }
}