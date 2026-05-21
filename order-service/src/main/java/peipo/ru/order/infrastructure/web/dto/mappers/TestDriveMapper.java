package peipo.ru.order.infrastructure.web.dto.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import peipo.ru.common.dto.testdrives.TestDriveResponseDto;
import peipo.ru.order.domain.models.TestDrive;

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
