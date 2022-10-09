package com.ioffice.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ioffice.controller.AuditoriumControllerTest;
import com.ioffice.controller.LoginControllerTest;
import com.ioffice.controller.UserControllerTest;

@RunWith(Suite.class)
@SuiteClasses({ AuditoriumServiceTest.class, LoginServiceTest.class,
	UserServiceTest.class,UserControllerTest.class,
	AuditoriumControllerTest.class,LoginControllerTest.class})
public class AllTests {

}
