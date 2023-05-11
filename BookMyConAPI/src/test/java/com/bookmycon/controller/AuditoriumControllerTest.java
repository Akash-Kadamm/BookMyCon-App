package com.bookmycon.controller;
import com.bookmycon.dto.UserRequestDTO;
import com.bookmycon.model.Auditoriums;
import com.bookmycon.model.Booking;
import com.bookmycon.model.User;
import com.bookmycon.repository.AuditoriumRepository;
import com.bookmycon.repository.BookingRepository;
import com.bookmycon.service.AuditoriumService;
import com.bookmycon.service.BookingService;
import com.bookmycon.utils.PdfGenerator;
import com.bookmycon.utils.PdfOfAuditorium;
import com.bookmycon.utils.ResponseMessage;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuditoriumControllerTest {

	@Mock
	private AuditoriumRepository auditoriumRepo;
	@Mock
	private BookingRepository bookingRepository;
	@Mock
	private BookingService bookingService;
	@Mock
	private AuditoriumService auditoriumService;

	@InjectMocks
	private AuditoriumController auditoriumController;

	PdfOfAuditorium pdfOfAuditorium;
	HttpServletResponse servletResponse;


	@Test
	public void testAddAuditorium() {
		Auditoriums auditorium = new Auditoriums();
		auditorium.setAuditoriumId(1);
		auditorium.setAuditoriumName("Auditorium 1");
		auditorium.setAuditoriumLocation("Location 1");
		auditorium.setAuditoriumCapacity(100);
		auditorium.setAuditoriumType("meeting");
		auditorium.setAuditoriumAminity("AC");

		doReturn(null).when(auditoriumService).addAuditorium(auditorium);

		ResponseEntity<Object> response = auditoriumController.addAuditorium(auditorium);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(ResponseMessage.AUDITORIUM_ADDED.getMessage(), response.getBody());

		verify(auditoriumService, times(1)).addAuditorium(auditorium);
	}

	@Test
	public void testGetAllAuditoriums() {
		List<Auditoriums> auditoriumsList = new ArrayList<>();
		auditoriumsList.add(new Auditoriums("6282", "CT", 40, "meeting", "AC"));
		auditoriumsList.add(new Auditoriums("6283", "CT", 40, "meeting", "AC"));

		when(auditoriumService.showAll()).thenReturn(auditoriumsList);

		ResponseEntity<List<Auditoriums>> responseEntity = auditoriumController.getAllAuditoriums();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		List<Auditoriums> responseList = responseEntity.getBody();
		assertNotNull(responseList);
		assertEquals(2, responseList.size());
		assertEquals("6282", responseList.get(0).getAuditoriumName());
		assertEquals("CT", responseList.get(0).getAuditoriumLocation());
		assertEquals(40, responseList.get(0).getAuditoriumCapacity());
		assertEquals("meeting", responseList.get(0).getAuditoriumType());
		assertEquals("AC", responseList.get(0).getAuditoriumAminity());
		assertEquals("6283", responseList.get(1).getAuditoriumName());
		assertEquals("CT", responseList.get(1).getAuditoriumLocation());
		assertEquals(40, responseList.get(1).getAuditoriumCapacity());
		assertEquals("meeting", responseList.get(1).getAuditoriumType());
		assertEquals("AC", responseList.get(1).getAuditoriumAminity());

		verify(auditoriumService, times(1)).showAll();
	}
	@Test
	public void testGetAuditoriunByName() {
		String auditoriumName = "Test Auditorium";
		Auditoriums auditorium = new Auditoriums();
		auditorium.setAuditoriumName(auditoriumName);
		List<Auditoriums> auditoriumList = new ArrayList<>();
		auditoriumList.add(auditorium);

		Mockito.when(auditoriumService.findByAuditoriumByName(auditoriumName)).thenReturn(auditoriumList);

		ResponseEntity<Auditoriums> response = auditoriumController.getAuditoriunByName(auditoriumName);

		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertEquals(auditoriumName, response.getBody().getAuditoriumName());
	}

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		pdfOfAuditorium = new PdfOfAuditorium();
		servletResponse = new MockHttpServletResponse();
	}

	@Test
	public void testUpdateAuditorium() {
		int id = 1;
		Auditoriums auditorium = new Auditoriums();
		auditorium.setAuditoriumName("Test Auditorium");
		auditorium.setAuditoriumLocation("Test Location");
		auditorium.setAuditoriumCapacity(100);
		doNothing().when(auditoriumService).updateAuditorium(id, auditorium);

		ResponseEntity<Object> response = auditoriumController.updateAuditorium(id, auditorium);

		verify(auditoriumService, times(1)).updateAuditorium(id, auditorium);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	@Test
	public void testDeleteAuditorium() {
		int id = 1;

		// create a mock auditorium object to be returned by the repository
		Auditoriums mockAuditorium = new Auditoriums();
		mockAuditorium.setAuditoriumId(id);
		mockAuditorium.setAuditoriumName("Test Auditorium");
		mockAuditorium.setAuditoriumLocation("Test Location");
		mockAuditorium.setAuditoriumCapacity(100);

		// create a mock list of bookings to be returned by the booking repository
		List<Booking> mockBookingList = new ArrayList<>();
		Booking mockBooking1 = new Booking();
		mockBooking1.setBookingId(1);
		mockBooking1.setAduitoriamId(mockAuditorium);
		mockBookingList.add(mockBooking1);
		Booking mockBooking2 = new Booking();
		mockBooking2.setBookingId(2);
		mockBooking2.setAduitoriamId(mockAuditorium);
		mockBookingList.add(mockBooking2);

		// mock the repository and service methods
		when(auditoriumRepo.getById(id)).thenReturn(mockAuditorium);
		when(bookingRepository.findByAduitoriamId(mockAuditorium)).thenReturn(mockBookingList);

		// call the controller method
		ResponseEntity<String> response = auditoriumController.deleteAuditorium(id);

		// verify that the service and repository methods were called
		verify(bookingService, times(2)).addBooking(any());
		verify(auditoriumService, times(1)).deleteById(id);

		// verify that the response is as expected
		assert(response.getBody().equals("record deleted"));
		assert(response.getStatusCode().equals(HttpStatus.OK));
	}

	@Test
	public void testGetAuditoriumById() {
		int id = 1;
		Map<String, Object> expectedResponse = new HashMap<>();
		expectedResponse.put("id", id);
		expectedResponse.put("name", "Audi 1");
		expectedResponse.put("capacity", 100);
		expectedResponse.put("location", "Building A");
		expectedResponse.put("type","meet");
		expectedResponse.put("amenity","AC");
		expectedResponse.put("message", ResponseMessage.GETTING_AUDITORIUM.getMessage());

		// Mock the service method to return the expected response
		when(auditoriumService.getAuditoriumById(id)).thenReturn(expectedResponse);

		// Make the GET request
		ResponseEntity<Object> responseEntity = auditoriumController.getAuditoriumByID(id);

		// Verify the response status code and body
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(expectedResponse, responseEntity.getBody());
	}

	@Test
	public void testGeneratePdfOfAuditoriumFileContentType()  throws IOException {
		auditoriumController.generatePdfFileOfAudi(servletResponse);
		assertEquals("application/pdf", servletResponse.getContentType());
	}



	@Test
	public void testGeneratePdfFileFindAllAuditoriums() throws IOException {
		auditoriumController.generatePdfFileOfAudi(servletResponse);
		verify(auditoriumService).showAll();
	}

}

