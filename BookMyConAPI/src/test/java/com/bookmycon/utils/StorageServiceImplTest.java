package com.bookmycon.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class StorageServiceImplTest {

    @InjectMocks
    StorageServiceImpl storageService;


    String basePath;

    @Mock
    File dirPath;

    @Before
    public void setupData() {
        basePath = "E:/uploads";
    }

    @Test
    public void testLoadAll() {
        String nonExistingDirectory = "non-existing-directory";
        File dirPath = new File(nonExistingDirectory);
        String[] actualFiles = dirPath.list();
        assertNull(actualFiles);
    }

}
