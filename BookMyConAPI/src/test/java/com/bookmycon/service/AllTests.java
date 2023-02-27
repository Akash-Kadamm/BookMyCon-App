package com.bookmycon.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.bookmycon.controller.AuditoriumControllerTest;
import com.bookmycon.controller.LoginControllerTest;
import com.bookmycon.controller.UserControllerTest;

@RunWith(Suite.class)
@SuiteClasses({ AuditoriumServiceTest.class, LoginServiceTest.class, UserServiceTest.class,
	UserControllerTest.class,AuditoriumControllerTest.class,
	LoginControllerTest.class})
public class AllTests {

}
