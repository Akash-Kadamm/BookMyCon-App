package com.bookmycon.service;
import com.bookmycon.model.UserLayout;
import com.bookmycon.repository.UserLayoutRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserLayoutServiceTest {

    @Mock
    UserLayoutRepository userLayoutRepository;

    @InjectMocks
    UserLayoutService userLayoutService;

    List<UserLayout> userLayoutList = new ArrayList<>();

    @Test
    public void testAllUserLayout() {
        List<UserLayout> actual = userLayoutService.findAll();
        assertEquals(userLayoutList,actual);
    }

    @Test
    public void addUserLayout_validUserLayout_userLayoutSaved() {

        UserLayout userLayout = new UserLayout();
        userLayout.getNameId();
        userLayoutService.addUserLayout(userLayout);
        verify(userLayoutRepository).save(userLayout);
    }

    @Test
    public void deleteUserLayout_validId_deleteCalledOnRepository() {
        String id = "12345";
        userLayoutService.deleteUserLayout(id);

        verify(userLayoutRepository).deleteById(id);
    }

    @Test
    public void editUserLayout_validIdAndUserLayout_saveCalledOnRepository() {
        String id = "UserLayout";
        UserLayout userLayout = new UserLayout();
        userLayout.getNameId();
        userLayoutService.editUserLayout(id, userLayout);
        verify(userLayoutRepository).save(userLayout);
    }

    @Test
    public void getUserLayoutById_validId_userLayoutReturned() {
        String id = "12345";
        UserLayout userLayout = new UserLayout();
        userLayout.getNameId();
        when(userLayoutRepository.findById(id)).thenReturn(Optional.of(userLayout));

        Optional<UserLayout> result = userLayoutService.getUserLayoutById(id);
        assertEquals(userLayout, result.get());
    }

    @Test
    public void getUserLayoutById_invalidId_emptyOptionalReturned() {
        String id = "12345";
        when(userLayoutRepository.findById(id)).thenReturn(Optional.empty());

        Optional<UserLayout> result = userLayoutService.getUserLayoutById(id);

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void findById_validId_userLayoutReturned() {
        String id = "12345";
        UserLayout userLayout = new UserLayout();
        userLayout.getNameId();
        when(userLayoutRepository.findById(id)).thenReturn(Optional.of(userLayout));
        Optional<UserLayout> result = userLayoutRepository.findById(id);


        assertEquals(userLayout, result.get());
    }

    @Test
    public void findById_invalidId_emptyOptionalReturned() {
        String id = "12345";
        when(userLayoutRepository.findById(id)).thenReturn(Optional.empty());

        Optional<UserLayout> result = userLayoutRepository.findById(id);
        assertEquals(Optional.empty(), result);
    }
}