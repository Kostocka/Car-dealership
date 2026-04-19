package peipo.ru.cardealership.infrastructure.web.controllers;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import peipo.ru.cardealership.application.usecases.testdrives.AddCarToTestDrive;
import peipo.ru.cardealership.application.usecases.testdrives.CreateTestDriveRequest;
import peipo.ru.cardealership.application.usecases.testdrives.GetTestDrives;
import peipo.ru.cardealership.application.usecases.testdrives.RemoveCarFromTestDrive;
import peipo.ru.cardealership.domain.models.TestDrive;
import peipo.ru.cardealership.domain.vo.id.CarId;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.infrastructure.security.RolesAllowed;
import peipo.ru.cardealership.infrastructure.web.dto.mappers.TestDriveMapper;
import peipo.ru.cardealership.infrastructure.web.dto.testdrives.AddCarToTestDriveRequest;
import peipo.ru.cardealership.infrastructure.web.dto.testdrives.CreateTestDriveRequestDto;
import peipo.ru.cardealership.infrastructure.web.dto.testdrives.RemoveCarFromTestDriveRequest;
import peipo.ru.cardealership.infrastructure.web.dto.testdrives.TestDriveResponseDto;

@RestController
@RequestMapping("/test-drives")
@RequiredArgsConstructor
public class TestDriveController
{
    private final AddCarToTestDrive addCarToTestDrive;
    private final CreateTestDriveRequest createTestDriveRequest;
    private final GetTestDrives getTestDrives;
    private final RemoveCarFromTestDrive removeCarFromTestDrive;

    private final TestDriveMapper testDriveMapper;

    @RolesAllowed({"MANAGER", "ADMIN"})
    @GetMapping
    public List<TestDriveResponseDto> getAllTestDrives()
    {
        return getTestDrives.execute().stream()
                .map(testDriveMapper::toDto)
                .toList();
    }

    @RolesAllowed({"USER", "ADMIN"})
    @PostMapping("/requests")
    public TestDriveResponseDto createTestDriveRequest(@RequestBody CreateTestDriveRequestDto request,
                                                       @AuthenticationPrincipal Jwt jwt)
    {
        TestDrive testDrive = createTestDriveRequest.execute(
                new ClientId(UUID.fromString(jwt.getSubject())),
                new CarId(request.getCarId())
        );
        return testDriveMapper.toDto(testDrive);
    }

    @RolesAllowed({"MANAGER", "ADMIN"})
    @PostMapping("/cars")
    public void addCarToTestDrive(@RequestBody AddCarToTestDriveRequest request)
    {
        addCarToTestDrive.execute(new CarId(request.getCarId()));
    }

    @RolesAllowed({"MANAGER", "ADMIN"})
    @DeleteMapping("/cars")
    public void removeCarFromTestDrive(@RequestBody RemoveCarFromTestDriveRequest request)
    {
        removeCarFromTestDrive.execute(new CarId(request.getCarId()));
    }
}
