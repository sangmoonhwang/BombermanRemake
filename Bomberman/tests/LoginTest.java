

import static org.junit.Assert.*;

import org.junit.Test;

import Controller.Login;
import View.DrawLogin;

public class LoginTest {


	@SuppressWarnings("static-access")
	@Test
	public void testLoginUser() {
		Login ln = new Login();
		ln.loginUser("thiswontwork!", "definitelywontwork");
		assertFalse(ln.isSuccess());
		ln.loginUser("Amak1020", "Abcdef1!");
		assertTrue(ln.isSuccess());
	}

}
