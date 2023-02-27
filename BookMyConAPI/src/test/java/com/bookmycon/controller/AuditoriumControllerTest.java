package com.bookmycon.controller;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import com.bookmycon.model.Auditoriums;
import com.bookmycon.service.AuditoriumService;
import com.bookmycon.utils.ResponseMessage;

@RunWith(MockitoJUnitRunner.class)
public class AuditoriumControllerTest {
	
	@Mock
	AuditoriumService audiService;
	
	@InjectMocks
	AuditoriumController audiController;
	
	Auditoriums auditoriums;
	
	Map<String, Object> response=new HashMap<>();
	
	@Before
	public void setUp() {
		setData();
	}
	
	public void setData() {
		auditoriums=new Auditoriums("Audi1", "CT1", 120, "small", "AC,Network");
	}
	

	@Test
    public void testUpdateAuditorium() {
		audiController.updateAuditorium(1, auditoriums);
		verify(audiService,times(1)).updateAuditorium(1, auditoriums);
	}

	@Test
    public 	void testGetAuditoriumByID() {
		response.put("message", ResponseMessage.GETTING_AUDITORIUM.getMessage());
		when(audiService.getAuditoriumById(anyInt())).thenReturn(response);
		ResponseEntity<Object> actual=audiController.getAuditoriumByID(1);
		assertEquals(response, actual.getBody());
	}

}
