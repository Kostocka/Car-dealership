package peipo.ru.cardealership.infrastructure.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peipo.ru.cardealership.application.usecases.parts.*;
import peipo.ru.cardealership.domain.models.parts.*;
import peipo.ru.cardealership.domain.vo.Money;
import peipo.ru.cardealership.domain.vo.id.CarModelId;
import peipo.ru.cardealership.domain.vo.id.PartId;
import peipo.ru.cardealership.infrastructure.web.dto.mappers.parts.*;
import peipo.ru.cardealership.infrastructure.web.dto.parts.*;
import peipo.ru.cardealership.infrastructure.web.dto.MoneyDto;
import peipo.ru.cardealership.infrastructure.web.dto.parts.requests.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/bodies")
    public List<BodyDto> getBodies()
    {
        return getBodyUseCase.execute().stream()
                .map(bodyDtoMapper::toDto)
                .toList();
    }

    @GetMapping("/engines")
    public List<EngineDto> getEngines()
    {
        return getEngineUseCase.execute().stream()
                .map(engineDtoMapper::toDto)
                .toList();
    }

    @GetMapping("/gearboxes")
    public List<GearBoxDto> getGearBoxes()
    {
        return getGearBoxUseCase.execute().stream()
                .map(gearBoxDtoMapper::toDto)
                .toList();
    }

    @GetMapping("/wheels")
    public List<WheelsDto> getWheels()
    {
        return getWheelsUseCase.execute().stream()
                .map(wheelsDtoMapper::toDto)
                .toList();
    }

    @GetMapping("/interiors")
    public List<InteriorDto> getInteriors()
    {
        return getInteriorUseCase.execute().stream()
                .map(interiorDtoMapper::toDto)
                .toList();
    }

    @PostMapping("/{partId}/price")
    public void setPartPrice(@PathVariable UUID partId, @RequestBody MoneyDto priceDto)
    {
        setPartPriceUseCase.execute(new PartId(partId), priceDto.toMoney());
    }

    @GetMapping("/{partId}/price")
    public MoneyDto getPrice(@PathVariable UUID partId)
    {
        Money price = getPartPriceUseCase.execute(new PartId(partId));
        return new MoneyDto(price.value());
    }

    @PostMapping("/{partId}/stock")
    public void addStock(@PathVariable UUID partId, @RequestBody AddStockRequest request)
    {
        addPartStockUseCase.execute(new PartId(partId), request.getQuantity());
    }

    @PostMapping("/bodies")
    public BodyDto createBody(@RequestBody CreateBodyRequest request)
    {
        Body body = bodyDtoMapper.fromRequest(request);
        return bodyDtoMapper.toDto(createBodyUseCase.execute(body));
    }

    @PostMapping("/engines")
    public EngineDto createEngine(@RequestBody CreateEngineRequest request)
    {
        Engine engine = engineDtoMapper.fromRequest(request);
        return engineDtoMapper.toDto(createEngineUseCase.execute(engine));
    }

    @PostMapping("/gearboxes")
    public GearBoxDto createGearBox(@RequestBody CreateGearBoxRequest request)
    {
        GearBox gearBox = gearBoxDtoMapper.fromRequest(request);
        return gearBoxDtoMapper.toDto(createGearBoxUseCase.execute(gearBox));
    }

    @PostMapping("/interiors")
    public InteriorDto createInterior(@RequestBody CreateInteriorRequest request)
    {
        Interior interior = interiorDtoMapper.fromRequest(request);
        return interiorDtoMapper.toDto(createInteriorUseCase.execute(interior));
    }

    @PostMapping("/wheels")
    public WheelsDto createWheels(@RequestBody CreateWheelsRequest request)
    {
        Wheels wheels = wheelsDtoMapper.fromRequest(request);
        return wheelsDtoMapper.toDto(createWheelsUseCase.execute(wheels));
    }

    @PostMapping("/part-to-part")
    public void addPartsCompabiliti(@RequestBody AddPartCompatibilityRequest request)
    {
        addPartCompatibilityUseCase.execute(new PartId(request.getFirstPart()), new PartId(request.getSecondPart()));
    }

    @PostMapping("/part-to-model")
    public void addPartToModelCompabiliti(@RequestBody AddModelPartCompatibilityRequest request)
    {
        addModelPartCompatibilityUseCase.execute(new PartId(request.getPartId()), new CarModelId(request.getModelId()));
    }
}
