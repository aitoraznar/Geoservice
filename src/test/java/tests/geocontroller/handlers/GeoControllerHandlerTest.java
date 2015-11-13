package tests.geocontroller.handlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geonames.controllers.handlers.GeoControllerHandler;
import com.geonames.services.UserService;

@SuppressWarnings("unchecked")
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserService.class)
public class GeoControllerHandlerTest {
	
	@Test
	public void userInvalid() {
		String name = null;
		String zip = null;
		
		String response = GeoControllerHandler.handleUserLocate(name, zip);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			HashMap<String, Object> res = mapper.readValue(response, HashMap.class);
			assertEquals("The response code should be 422", 422, res.get("code"));
		} catch (Exception e) {
			fail("Unexpected Exception");
		}
	}

	@Test
	public void userValid() {
		PowerMockito.mockStatic(GeoControllerHandler.class);
		// Mocking UserService
		when(GeoControllerHandler.getUserService()).thenReturn(mock(UserService.class));
		
		String name = "Carlos";
		String zip = "48005";
		
		String response = GeoControllerHandler.handleUserLocate(name, zip);
		
		PowerMockito.verifyStatic(Mockito.times(1));
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			HashMap<String, Object> res = mapper.readValue(response, HashMap.class);
			assertEquals("The response code should be 200", 200, res.get("code"));
			
		} catch (Exception e) {
			fail("Unexpected Exception");
		}
	}

}
