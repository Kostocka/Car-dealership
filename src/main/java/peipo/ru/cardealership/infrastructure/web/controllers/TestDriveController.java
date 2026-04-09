package peipo.ru.cardealership.infrastructure.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peipo.ru.cardealership.application.usecases.testdrives.AddCarToTestDrive;
import peipo.ru.cardealership.application.usecases.testdrives.CreateTestDriveRequest;
import peipo.ru.cardealership.application.usecases.testdrives.GetTestDrives;
import peipo.ru.cardealership.application.usecases.testdrives.RemoveCarFromTestDrive;
import peipo.ru.cardealership.domain.models.TestDrive;
import peipo.ru.cardealership.domain.vo.id.CarId;
import peipo.ru.cardealership.domain.vo.id.ClientId;
import peipo.ru.cardealership.infrastructure.web.dto.mappers.TestDriveMapper;
import peipo.ru.cardealership.infrastructure.web.dto.testdrives.AddCarToTestDriveRequest;
import peipo.ru.cardealership.infrastructure.web.dto.testdrives.CreateTestDriveRequestDto;
import peipo.ru.cardealership.infrastructure.web.dto.testdrives.RemoveCarFromTestDriveRequest;
import peipo.ru.cardealership.infrastructure.web.dto.testdrives.TestDriveResponseDto;

import java.util.List;

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

    @GetMapping
    public List<TestDriveResponseDto> getAllTestDrives()
    {
        return getTestDrives.execute().stream()
                .map(testDriveMapper::toDto)
                .toList();
    }

    @PostMapping("/requests")
    public TestDriveResponseDto createTestDriveRequest(@RequestBody CreateTestDriveRequestDto request)
    {
        TestDrive testDrive = createTestDriveRequest.execute(
                new ClientId(request.getClientId()),
                new CarId(request.getCarId())
        );
        return testDriveMapper.toDto(testDrive);
    }

    @PostMapping("/cars")
    public void addCarToTestDrive(@RequestBody AddCarToTestDriveRequest request)
    {
        addCarToTestDrive.execute(new CarId(request.getCarId()));
    }

    @DeleteMapping("/cars")
    public void removeCarFromTestDrive(@RequestBody RemoveCarFromTestDriveRequest request)
    {
        removeCarFromTestDrive.execute(new CarId(request.getCarId()));
    }
}
