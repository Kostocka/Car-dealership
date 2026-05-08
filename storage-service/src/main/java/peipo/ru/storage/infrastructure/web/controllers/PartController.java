package peipo.ru.storage.infrastructure.web.controllers;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peipo.ru.common.dto.MoneyDto;
import peipo.ru.common.dto.parts.*;
import peipo.ru.common.dto.parts.requests.*;
import peipo.ru.common.security.RolesAllowed;
import peipo.ru.common.vo.Money;
import peipo.ru.common.vo.id.PartId;
import peipo.ru.storage.application.usecases.parts.*;
import peipo.ru.storage.domain.models.parts.*;
import peipo.ru.storage.domain.vo.CarModelId;
import peipo.ru.storage.infrastructure.web.dto.mappers.parts.*;

@RestController
@RequestMapping("/parts")
@RequiredArgsConstructor
public class PartController
{
    private final GetBodyUseCase getBodyUseCase;
    private final GetEngineUseCase getEngineUseCase;
    private final GetGearBoxUseCase getGearBoxUseCase;
    private final GetWheelsUseCase getWheelsUseCase;
    private final GetInteriorUseCase getInteriorUseCase;

    private final CreateBodyUseCase createBodyUseCase;
    private final CreateEngineUseCase createEngineUseCase;
    private final CreateGearBoxUseCase createGearBoxUseCase;
    private final CreateWheelsUseCase createWheelsUseCase;
    private final CreateInteriorUseCase createInteriorUseCase;

    private final BodyDtoMapper bodyDtoMapper;
    private final EngineDtoMapper engineDtoMapper;
    private final GearBoxDtoMapper gearBoxDtoMapper;
    private final WheelsDtoMapper wheelsDtoMapper;
    private final InteriorDtoMapper interiorDtoMapper;

    private final GetPartPriceUseCase getPartPriceUseCase;
    private final SetPartPriceUseCase setPartPriceUseCase;
    private final AddPartStockUseCase addPartStockUseCase;

    private final AddModelPartCompatibilityUseCase addModelPartCompatibilityUseCase;
    private final AddPartCompatibilityUseCase addPartCompatibilityUseCase;

    @RolesAllowed({"USER", "WAREHOUSE_ADMIN", "MANAGER", "ADMIN"})
    @GetMapping("/bodies")
    public List<BodyDto> getBodies()
    {
        return getBodyUseCase.execute().stream()
                .map(bodyDtoMapper::toDto)
                .toList();
    }

    @RolesAllowed({"USER", "WAREHOUSE_ADMIN", "MANAGER", "ADMIN"})
    @GetMapping("/engines")
    public List<EngineDto> getEngines()
    {
        return getEngineUseCase.execute().stream()
                .map(engineDtoMapper::toDto)
                .toList();
    }

    @RolesAllowed({"USER", "WAREHOUSE_ADMIN", "MANAGER", "ADMIN"})
    @GetMapping("/gearboxes")
    public List<GearBoxDto> getGearBoxes()
    {
        return getGearBoxUseCase.execute().stream()
                .map(gearBoxDtoMapper::toDto)
                .toList();
    }

    @RolesAllowed({"USER", "WAREHOUSE_ADMIN", "MANAGER", "ADMIN"})
    @GetMapping("/wheels")
    public List<WheelsDto> getWheels()
    {
        return getWheelsUseCase.execute().stream()
                .map(wheelsDtoMapper::toDto)
                .toList();
    }

    @RolesAllowed({"USER", "WAREHOUSE_ADMIN", "MANAGER", "ADMIN"})
    @GetMapping("/interiors")
    public List<InteriorDto> getInteriors()
    {
        return getInteriorUseCase.execute().stream()
                .map(interiorDtoMapper::toDto)
                .toList();
    }

    @RolesAllowed({"WAREHOUSE_ADMIN", "ADMIN"})
    @PostMapping("/{partId}/price")
    public void setPartPrice(@PathVariable UUID partId, @RequestBody MoneyDto priceDto)
    {
        setPartPriceUseCase.execute(new PartId(partId), priceDto.toMoney());
    }

    @RolesAllowed({"USER", "WAREHOUSE_ADMIN", "MANAGER", "ADMIN"})
    @GetMapping("/{partId}/price")
    public MoneyDto getPrice(@PathVariable UUID partId)
    {
        Money price = getPartPriceUseCase.execute(new PartId(partId));
        return new MoneyDto(price.value());
    }

    @RolesAllowed({"WAREHOUSE_ADMIN", "ADMIN"})
    @PostMapping("/{partId}/stock")
    public void addStock(@PathVariable UUID partId, @RequestBody AddStockRequest request)
    {
        addPartStockUseCase.execute(new PartId(partId), request.getQuantity());
    }

    @RolesAllowed({"WAREHOUSE_ADMIN", "ADMIN"})
    @PostMapping("/bodies")
    public BodyDto createBody(@RequestBody CreateBodyRequest request)
    {
        Body body = bodyDtoMapper.fromRequest(request);
        return bodyDtoMapper.toDto(createBodyUseCase.execute(body));
    }

    @RolesAllowed({"WAREHOUSE_ADMIN", "ADMIN"})
    @PostMapping("/engines")
    public EngineDto createEngine(@RequestBody CreateEngineRequest request)
    {
        Engine engine = engineDtoMapper.fromRequest(request);
        return engineDtoMapper.toDto(createEngineUseCase.execute(engine));
    }

    @RolesAllowed({"WAREHOUSE_ADMIN", "ADMIN"})
    @PostMapping("/gearboxes")
    public GearBoxDto createGearBox(@RequestBody CreateGearBoxRequest request)
    {
        GearBox gearBox = gearBoxDtoMapper.fromRequest(request);
        return gearBoxDtoMapper.toDto(createGearBoxUseCase.execute(gearBox));
    }

    @RolesAllowed({"WAREHOUSE_ADMIN", "ADMIN"})
    @PostMapping("/interiors")
    public InteriorDto createInterior(@RequestBody CreateInteriorRequest request)
    {
        Interior interior = interiorDtoMapper.fromRequest(request);
        return interiorDtoMapper.toDto(createInteriorUseCase.execute(interior));
    }

    @RolesAllowed({"WAREHOUSE_ADMIN", "ADMIN"})
    @PostMapping("/wheels")
    public WheelsDto createWheels(@RequestBody CreateWheelsRequest request)
    {
        Wheels wheels = wheelsDtoMapper.fromRequest(request);
        return wheelsDtoMapper.toDto(createWheelsUseCase.execute(wheels));
    }

    @RolesAllowed({"WAREHOUSE_ADMIN", "ADMIN"})
    @PostMapping("/part-to-part")
    public void addPartsCompatibility(@RequestBody AddPartCompatibilityRequest request)
    {
        addPartCompatibilityUseCase.execute(
                new PartId(request.getFirstPart()),
                new PartId(request.getSecondPart())
        );
    }

    @RolesAllowed({"WAREHOUSE_ADMIN", "ADMIN"})
    @PostMapping("/part-to-model")
    public void addPartToModelCompatibility(@RequestBody AddModelPartCompatibilityRequest request)
    {
        addModelPartCompatibilityUseCase.execute(
                new PartId(request.getPartId()),
                new CarModelId(request.getModelId())
        );
    }
}
