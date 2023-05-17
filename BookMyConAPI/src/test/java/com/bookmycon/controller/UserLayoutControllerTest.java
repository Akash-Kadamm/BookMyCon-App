package com.bookmycon.controller;

import com.bookmycon.dto.DataDto;
import com.bookmycon.model.Areas;
import com.bookmycon.model.UserLayout;
import com.bookmycon.repository.AreasRepository;
import com.bookmycon.service.AreasService;
import com.bookmycon.service.UserLayoutService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserLayoutControllerTest {

    @InjectMocks
    private UserLayoutController userLayoutController;

    @Mock
    private UserLayoutService userLayoutService;

    @Mock
    private AreasRepository areasRepository;

    @Mock
    UserLayout userLayout;
    @Mock
    private AreasService areasService;

    DataDto dataDto;
    int[] array;
    Areas areas;

    @Before
    public void setUpData(){
        array=new int[2];
        array[0]=10;
        array[1]=20;
         dataDto=new DataDto("dataDto","rectangle",array,"dfhsiad","aifd","dfd");
         areas=new Areas(1, dataDto.getName(), dataDto.getShape(), dataDto.getCoords(),"transperant",dataDto.getFillColor(),userLayout);

    }

    @Test
    public void testGetAllUserLayout() {
        ResponseEntity<List<UserLayout>> actual = userLayoutController.getAllUserLayout();
        List<UserLayout> list = new ArrayList<>();
        assertEquals(list, actual.getBody());
    }

    @Test
    public void testDeleteUserById() throws Exception {
        String userName = "testuser";
        ResponseEntity<String> expectedResponse = new ResponseEntity<String>("record deleted", HttpStatus.OK);
        ResponseEntity<String> actualResponse = userLayoutController.deleteUserById(userName);
        verify(userLayoutService).deleteUserLayout(userName);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void editUserLayoutTest() {
        String id = "UserLayout";
        ResponseEntity<?> result = userLayoutController.editUserLayout(id, userLayout);
        verify(userLayoutService, times(1)).addUserLayout(userLayout);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void addUserLayoutTest() {
        ResponseEntity<?> result = userLayoutController.addUserLayout(userLayout);
        verify(userLayoutService, times(1)).addUserLayout(userLayout);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    public void testGetAllAreas() {
        when(areasService.findAll()).thenReturn(List.of(new Areas()));
        ResponseEntity<?> result = userLayoutController.getAllAreas();
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    @Test
    public void testAddAreas_Post() {
         when(userLayoutService.findById(anyString())).thenReturn(Optional.of(userLayout));
           verify(areasService,times(0)).addAreas(areas);
           ResponseEntity<?> result=userLayoutController.addareas(dataDto);
           assertEquals(HttpStatus.CREATED,result.getStatusCode());

    }

    @Test
    public void testAddAreas_Put() {
          verify(areasService,times(0)).addAreas(any(Areas.class));
          ResponseEntity<?> result=userLayoutController.addareas(areas);
          assertEquals(HttpStatus.CREATED,result.getStatusCode());
    }

    @Test
    public void testGetUserLayoutById() {
     when(userLayoutService.findById(anyString())).thenReturn(Optional.of(userLayout));
     ResponseEntity<?> result=userLayoutController.getUserLayoutById("faf");
     assertEquals(HttpStatus.OK,result.getStatusCode());
    }
}
