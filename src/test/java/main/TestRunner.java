package main;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import tests.geocontroller.handlers.AllGeoControllerHandlerTests;
import tests.user.AllUserTests;

public class TestRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(AllUserTests.class, AllGeoControllerHandlerTests.class);
		System.out.println("Tests launched: " + result.getRunCount());
		System.out.println("========================================");
	    for (Failure failure : result.getFailures()) {
	    	System.out.println("Test failed: " + failure.toString());
	    }
	    System.out.println("Tests ended in " + result.getRunTime() + "ms");
	}
}
