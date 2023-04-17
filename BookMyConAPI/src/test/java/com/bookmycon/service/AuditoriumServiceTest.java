package com.bookmycon.service;


import com.bookmycon.model.Auditoriums;
import com.bookmycon.repository.AuditoriumRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuditoriumServiceTest {
        @Mock
        private AuditoriumRepository auditoriumRepo;

        @InjectMocks
        private AuditoriumService auditoriumService;

        @Before
        public void setUp() {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        public void testAddAuditorium() {
            Auditoriums auditorium = new Auditoriums("CT","CT1",40,"Meeting","AC");;
            when(auditoriumRepo.save(auditorium)).thenReturn(auditorium);
            Auditoriums result = auditoriumService.addAuditorium(auditorium);
            verify(auditoriumRepo).save(auditorium);
            assertEquals(auditorium, result);
        }


    @Test
    public void testUpdateAuditorium() {
        Auditoriums auditorium = new Auditoriums();
        auditorium.setAuditoriumName("Test Auditorium");
        auditorium.setAuditoriumLocation("Test Location");
        auditorium.setAuditoriumCapacity(100);

        when(auditoriumRepo.findById(1)).thenReturn(Optional.of(auditorium));
        when(auditoriumRepo.save(auditorium)).thenReturn(auditorium);
        auditoriumService.updateAuditorium(1, auditorium);
        verify(auditoriumRepo).findById(1);
        verify(auditoriumRepo).save(auditorium);
        assertEquals("Test Auditorium", auditorium.getAuditoriumName());
        assertEquals("Test Location", auditorium.getAuditoriumLocation());
        assertEquals(100, auditorium.getAuditoriumCapacity());
    }
    @Test
    public void testDeleteById() {
        int id = 1;

        doNothing().when(auditoriumRepo).deleteById(id);

        auditoriumService.deleteById(id);

        verify(auditoriumRepo, times(1)).deleteById(id);
    }
    @Test
    public void testShowAll() {
        List<Auditoriums> auditoriumList = new ArrayList<>();
        Auditoriums auditorium1 = new Auditoriums();
        auditorium1.setAuditoriumId(1);
        auditorium1.setAuditoriumName("Auditorium 1");
        auditorium1.setAuditoriumLocation("Location 1");
        auditorium1.setAuditoriumCapacity(100);
        auditorium1.setAuditoriumType("meeting");
        auditorium1.setAuditoriumAminity("AC");
        auditoriumList.add(auditorium1);

        Auditoriums auditorium2 = new Auditoriums();
        auditorium2.setAuditoriumName("Auditorium 2");
        auditorium2.setAuditoriumLocation("Location 2");
        auditorium2.setAuditoriumCapacity(100);
        auditorium2.setAuditoriumType("meeting");
        auditorium2.setAuditoriumAminity("AC");
        auditoriumList.add(auditorium2);

        when(auditoriumRepo.findAll()).thenReturn(auditoriumList);

        List<Auditoriums> result = auditoriumService.showAll();

        assertEquals(auditoriumList, result);
        verify(auditoriumRepo, times(1)).findAll();
    }
    @Test
    public void testFindByAuditoriumByName() {
        String name = "Auditorium 1";

        List<Auditoriums> auditoriumList = new ArrayList<>();
        Auditoriums auditorium1 = new Auditoriums();
        auditorium1.setAuditoriumId(1);
        auditorium1.setAuditoriumName(name);
        auditorium1.setAuditoriumLocation("Location 1");
        auditorium1.setAuditoriumCapacity(100);
        auditorium1.setAuditoriumType("meeting");
        auditorium1.setAuditoriumAminity("AC");
        auditoriumList.add(auditorium1);

        when(auditoriumRepo.findByAuditoriumName(name)).thenReturn(auditoriumList);

        List<Auditoriums> result = auditoriumService.findByAuditoriumByName(name);

        assertEquals(auditoriumList, result);
        verify(auditoriumRepo, times(1)).findByAuditoriumName(name);
    }
    @Test
    public void testGetAuditoriumById() {
        int id = 1;
        Auditoriums auditorium = new Auditoriums();
        auditorium.setAuditoriumId(id);
        auditorium.setAuditoriumName("Auditorium 1");
        auditorium.setAuditoriumLocation("Location 1");
        auditorium.setAuditoriumCapacity(100);
        auditorium.setAuditoriumType("meeting");
        auditorium.setAuditoriumAminity("AC");

        when(auditoriumRepo.findById(id)).thenReturn(Optional.of(auditorium));

        Map<String, Object> result = auditoriumService.getAuditoriumById(id);

        assertEquals(auditorium, result.get("Auditorium"));
        verify(auditoriumRepo, times(1)).findById(id);
    }

}



