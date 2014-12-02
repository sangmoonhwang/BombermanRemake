

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import Model.Database;
import Model.User;

public class DatabaseTest {

	@Test
	public void testWriteUserCSVEntry() throws IOException {
		User u = new User("WriteTest01","Abcdef1!","WRITINGTEST");
		Database d = new Database();
		d.writeUserCSVEntry(u);
		assertTrue(d.isUserExist("WriteTest01"));
	}

	@SuppressWarnings("static-access")
	@Test
	public void testReadUserCSVEntry() throws IOException {
		Database d = new Database();
		assertEquals("Amak1020",d.readUserCSVEntry("Amak1020").getUsername());
		assertNull(d.readUserCSVEntry("thisuserdoesnotexist"));
	}


	@SuppressWarnings("static-access")
	@Test
	public void testIsUserExist() throws IOException {
		Database d = new Database();
		assertFalse(d.isUserExist("definitelyNOT"));
		assertTrue(d.isUserExist("Amak1020"));
	}

}
