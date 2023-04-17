package service;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.micro.housekeepingservice.dto.HousekeepingDto;
import com.micro.housekeepingservice.entity.Housekeeping;
import com.micro.housekeepingservice.impl.HousekeepingServiceImpl;
import com.micro.housekeepingservice.repository.HousekeepingRepository;
import com.micro.housekeepingservice.service.HousekeepingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class HousekeepingServiceTest {
    @Mock
    private HousekeepingRepository housekeepingRepository;

    @InjectMocks
    private HousekeepingServiceImpl housekeepingService;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testGetAllHousekeepingRequests() {
        HousekeepingRepository mockRepository = mock(HousekeepingRepository.class);

        List<Housekeeping> housekeepingList = new ArrayList<>();
        housekeepingList.add(new Housekeeping(1L, "Clean bathroom", "John"));
        housekeepingList.add(new Housekeeping(2L, "Change bedsheets", "Jane"));

        when(mockRepository.findAll()).thenReturn(housekeepingList);

        HousekeepingServiceImpl service = new HousekeepingServiceImpl(mockRepository);

        List<HousekeepingDto> result = service.getAllHousekeepingRequests();

        assertEquals(housekeepingList.size(), result.size());

        for (int i = 0; i < housekeepingList.size(); i++) {
            Housekeeping housekeeping = housekeepingList.get(i);
            HousekeepingDto dto = result.get(i);
            assertEquals(housekeeping.getId(), dto.getId());
            assertEquals(housekeeping.getHousekeepingServices(),dto.getHousekeepingServices());
            assertEquals(housekeeping.getAuditoriumName(), dto.getAuditoriumName());
            assertEquals(housekeeping.getId(), dto.getId());
        }
    }

    @Test
    public void testSaveHousekeepingRequest() {
        HousekeepingDto housekeepingDto = new HousekeepingDto();
        housekeepingDto.setId(1L);
        housekeepingDto.setAuditoriumName("Auditorium 1");
        housekeepingDto.setHousekeepingServices("Cleaning");

        HousekeepingRepository housekeepingRepository = mock(HousekeepingRepository.class);

        Housekeeping expectedHousekeeping = new Housekeeping();
        expectedHousekeeping.setId(1L);
        expectedHousekeeping.setAuditoriumName("Auditorium 1");
        expectedHousekeeping.setHousekeepingServices("Cleaning");
        when(housekeepingRepository.save(any(Housekeeping.class))).thenReturn(expectedHousekeeping);

        HousekeepingServiceImpl housekeepingService = new HousekeepingServiceImpl(housekeepingRepository);
        HousekeepingDto savedHousekeepingDto = housekeepingService.saveHousekeepingRequest(housekeepingDto);

        ArgumentCaptor<Housekeeping> housekeepingArgumentCaptor = ArgumentCaptor.forClass(Housekeeping.class);
        verify(housekeepingRepository).save(housekeepingArgumentCaptor.capture());
        Housekeeping capturedHousekeeping = housekeepingArgumentCaptor.getValue();
        assertEquals(expectedHousekeeping.getId(), capturedHousekeeping.getId());
        assertEquals(expectedHousekeeping.getAuditoriumName(), capturedHousekeeping.getAuditoriumName());
        assertEquals(expectedHousekeeping.getHousekeepingServices(), capturedHousekeeping.getHousekeepingServices());

        assertEquals(expectedHousekeeping.getId(), savedHousekeepingDto.getId());
        assertEquals(expectedHousekeeping.getAuditoriumName(), savedHousekeepingDto.getAuditoriumName());
        assertEquals(expectedHousekeeping.getHousekeepingServices(), savedHousekeepingDto.getHousekeepingServices());
    }

    @Test
    public void testDeleteHouskeepingRequest() {
        Long housekeepingId = 1L;
        housekeepingService.deleteHouskeepingRequest(housekeepingId);
        verify(housekeepingRepository).deleteById(eq(housekeepingId));
    }





}


