package com.bookmycon.controller;
import com.bookmycon.utils.StorageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockHttpServletResponse;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

import static org.mockito.Mockito.*;

public class FileControllerImplTest {
    @InjectMocks
    FileControllerImpl fileControllerImpl;
    @Mock
    StorageService storageService;
    HttpServletResponse response;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        response = new MockHttpServletResponse();
    }

//    @Test
//    public void testDownload() throws IOException {
//        String fileName = "example.jpg";
//       Resource resource = mock(Resource.class);
//       InputStream inputStream = mock(InputStream.class);
//        ServletOutputStream outputStream = mock(ServletOutputStream.class);
//
//        when(storageService.load(fileName)).thenReturn(resource);
//        when(resource.getInputStream()).thenReturn(inputStream);
//        when(response.getOutputStream()).thenReturn(outputStream);
//
//        fileControllerImpl.download(fileName, response);
//
//        verify(resource, times(1)).getInputStream();
//
//
//
//
//    }
}
