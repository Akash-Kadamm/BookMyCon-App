package com.bookmycon.service;

import com.bookmycon.model.Areas;
import com.bookmycon.model.Product;
import com.bookmycon.repository.AreasRepository;
import com.bookmycon.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AreaServiceTest {

    @Mock
    AreasRepository areasRepository;

    @InjectMocks
    AreasService areasService;

    List<Areas> areasList = new ArrayList<>();
    Areas areas;

    @Test
    public void testFindAllAreas()
    {
        List<Areas> actualArea=areasService.findAll();
        assertEquals(areasList,actualArea);
    }

    @Test
    public void testFindById() {
        // Arrange
        int id = 99;
        Areas area = new Areas(id, "Area 1");

        when(areasRepository.findById(id)).thenReturn(Optional.of(area));

        // Act
        Optional<Areas> result = areasService.findById(id);

        // Assert
        assertEquals(area, result.get());
    }


    @Test
    public void testGetAreasById() {
        Areas areas = new Areas();
        areas.setAreaId(1);
        areas.setName("Test Area");

        when(areasRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(areas));

        Optional<Areas> foundAreas = areasService.getAreasById(1);

        //assertTrue(foundAreas.isPresent());
        assertEquals(areas.getName(), foundAreas.get().getName());
    }

    @Test
    public void testDeleteAreas() {
        int idToDelete = 1;

        areasService.deleteAreas(idToDelete);

        verify(areasRepository).deleteById(idToDelete);
    }

    @Test
    public void testEditAreas() {
        int idToEdit = 1;
        Areas areas = new Areas();
        areas.setAreaId(1);
        areas.setName("Updated Test Area");

        areasService.editAreas(idToEdit, areas);

        ArgumentCaptor<Areas> argumentCaptor = ArgumentCaptor.forClass(Areas.class);
        verify(areasRepository).save(argumentCaptor.capture());
        Areas capturedAreas = argumentCaptor.getValue();
        assertEquals(idToEdit, capturedAreas.getAreaId());
        assertEquals(areas.getName(), capturedAreas.getName());
    }

}
