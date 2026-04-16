package peipo.ru.cardealership.infrastructure.web.dto.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.cardealership.domain.models.TestDrive;
import peipo.ru.cardealership.infrastructure.web.dto.testdrives.TestDriveResponseDto;

@Component
@RequiredArgsConstructor
public class TestDriveMapper
{
    public TestDriveResponseDto toDto(TestDrive testDrive)
    {
        TestDriveResponseDto testDriveResponseDto = new TestDriveResponseDto();
        testDriveResponseDto.setTestDriveId(testDrive.getTestDriveId().id());
        testDriveResponseDto.setClientId(testDrive.getClientId().id());
        testDriveResponseDto.setStartTime(testDrive.getStartTime());
        testDriveResponseDto.setCarId(testDrive.getCarId().id());
        return testDriveResponseDto;
    }
}
