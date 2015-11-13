package tests.user;

import static org.junit.Assert.*;

import org.junit.Test;

import com.geonames.models.User;

public class UserValidationTest {

	@Test
	public void validationShouldFail() {
		User tester = new User("", "");
		
		assertFalse("Validation Should Fail", tester.validate());
	} 

}
