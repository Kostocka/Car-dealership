package org.kostocka.cardealership.domain.services;

import lombok.AllArgsConstructor;
import org.kostocka.cardealership.domain.exception.EntityNotFoundException;
import org.kostocka.cardealership.domain.models.parts.*;
import org.kostocka.cardealership.domain.repository.PartRepository;
import org.kostocka.cardealership.domain.vo.id.PartId;

@AllArgsConstructor
public class PartCatalogService
{
    private final PartRepository<Body> bodyRepository;
    private final PartRepository<Engine> engineRepository;
    private final PartRepository<GearBox> gearBoxRepository;
    private final PartRepository<Interior> interiorRepository;
    private final PartRepository<Wheels>  wheelsRepository;

    public Body getBody(PartId id)
    {
        return bodyRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Body not found"));
    }

    public Engine getEngine(PartId id)
    {
        return engineRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Engine not found"));
    }

    public GearBox getGearBox(PartId id)
    {
        return gearBoxRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("GearBox not found"));
    }

    public Interior getInterior(PartId id)
    {
        return interiorRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Interior not found"));
    }

    public Wheels getWheels(PartId id)
    {
        return wheelsRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Wheels not found"));
    }
}
