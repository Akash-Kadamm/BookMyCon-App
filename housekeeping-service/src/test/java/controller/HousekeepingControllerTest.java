package controller;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.micro.housekeepingservice.controller.HousekeepingController;
import com.micro.housekeepingservice.dto.HousekeepingDto;
import com.micro.housekeepingservice.impl.HousekeepingServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

public class HousekeepingControllerTest {
    @InjectMocks
    private HousekeepingController housekeepingController;

    @Mock
    private HousekeepingServiceImpl housekeepingService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveHousekeepingRequest() {
        HousekeepingDto housekeepingDto = new HousekeepingDto();
        housekeepingDto.setHousekeepingServices("Clean the kitchen");
        housekeepingDto.setAuditoriumName("101");
        housekeepingDto.setId(1L);

        HousekeepingDto savedHousekeepingDto = new HousekeepingDto();
        savedHousekeepingDto.setId(1L);
        savedHousekeepingDto.setHousekeepingServices("Clean the kitchen");
        savedHousekeepingDto.setAuditoriumName("101");


        when(housekeepingService.saveHousekeepingRequest(housekeepingDto)).thenReturn(savedHousekeepingDto);

        ResponseEntity<HousekeepingDto> responseEntity = housekeepingController.saveHousekeepingRequest(housekeepingDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(savedHousekeepingDto, responseEntity.getBody());
    }
    @Test
    public void testGetAllHousekeepingRequests() {

        List<HousekeepingDto> housekeepingList = new ArrayList<>();
        HousekeepingDto housekeeping1 = new HousekeepingDto();
        housekeeping1.setId(1L);
        housekeeping1.setAuditoriumName("101");
        housekeeping1.setHousekeepingServices("cleaning floor");
        housekeepingList.add(housekeeping1);

        HousekeepingDto housekeeping2 = new HousekeepingDto();
        housekeeping2.setId(2L);
        housekeeping2.setAuditoriumName("102");
        housekeeping2.setHousekeepingServices("washroom cleaning");
        housekeepingList.add(housekeeping2);

        when(housekeepingService.getAllHousekeepingRequests()).thenReturn(housekeepingList);

        ResponseEntity<List<HousekeepingDto>> response = housekeepingController.getAllHousekeepingRequests();

        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<HousekeepingDto> responseBody = response.getBody();
        assertEquals(housekeepingList.size(), responseBody.size());
        assertEquals(housekeepingList.get(0).getId(), responseBody.get(0).getId());
        assertEquals(housekeepingList.get(1).getAuditoriumName(), responseBody.get(1).getAuditoriumName());
    }

    @Test
    public void testDeleteHousekeepingRequest() {
        Long housekeepingId = 1L;

        doNothing().when(housekeepingService).deleteHouskeepingRequest(housekeepingId);

        ResponseEntity<String> response = housekeepingController.deleteHousekeepingRequest(housekeepingId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Housekeeping Request Deleted Successfully!!!", response.getBody());

        verify(housekeepingService).deleteHouskeepingRequest(housekeepingId);
    }
}
