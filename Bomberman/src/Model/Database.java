package Model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * Database class
 *
 */
public class Database {

	public Database() {

	}

	/**
	 * Add the new user to the user database (username, password, realName, numOfPlay, TotalScore, levelsCompleted)
	 * @param User
	 * @return None
	 */
	public void writeUserCSVEntry(User newUser) throws IOException {
		FileWriter fileWriter = new FileWriter("res/data/user.csv", true);
		CSVWriter writer = new CSVWriter(fileWriter, ',');
		if(!newUser.equals(null)) {
			String[] entry = new String[]{newUser.getUsername(), newUser.getPassword(), newUser.getRealName(), Integer.toString(0), Integer.toString(0),
					Integer.toString(0)};
			writer.writeNext(entry);

			writer.close();
		}

	}

	/**
	 * Read the specified user information
	 * @param String username
	 * @return User information
	 */
	public static User readUserCSVEntry(String username) throws IOException {
		FileReader fileReader = new FileReader("res/data/user.csv");
		CSVReader reader = new CSVReader(fileReader);

		String [] nextLine;

		while ((nextLine = reader.readNext()) != null) {
			if(username.equals(nextLine[0])) {
				reader.close();
				return new User(nextLine);
			}
		}

		reader.close();

		return null;
	}

	/**
	 * Modify account
	 * @param username
	 * @param newPassword
	 * @param newName
	 * @param newTotalScore
	 * @param numOfPlay
	 * @param levelCompleted
	 * @return User information
	 */
	public static void modifyUserCSVEntry(String username, String newPassword, String newRealName, int numOfPlay, int newTotalScore, int levelCompleted) throws IOException {
		FileReader fileReader = new FileReader("res/data/user.csv");
		CSVReader reader = new CSVReader(fileReader);


		//Read all rows at once
		List<String[]> userData = reader.readAll();
		String[] user = null;
		boolean noUser = true;
		for(int i = 0; i < userData.size(); i++) {
			user = userData.get(i);

			if(user[0].equals(username)) {
				userData.remove(i);
				noUser = false;
				break;
			}
		}
		reader.close();
		if(noUser == true)
			return;
		//writes the modified data
		if(newPassword != null) {
			user[1] = newPassword; 
		}

		if(newRealName != null) {
			user[2] = newRealName;
		}

		if(numOfPlay != 0) {
			user[3] = Integer.toString(numOfPlay);
		}

		if(newTotalScore != 0) {
			user[4] = Integer.toString(newTotalScore);
		}

		if(levelCompleted > Integer.parseInt(user[5])) {
			user[5] = Integer.toString(levelCompleted);
		}

		userData.add(user);


		FileWriter fileWriter = new FileWriter("res/data/user.csv");
		CSVWriter writer = new CSVWriter(fileWriter, ',');
		writer.writeAll(userData);

		writer.close();
	}

	/**
	 * Checks for the same user name
	 * @param username
	 * @return True if account with the same username exists otherwise false
	 */

	public boolean isUserExist(String username) throws IOException {
		FileReader fileReader = new FileReader("res/data/user.csv");
		CSVReader reader = new CSVReader(fileReader);

		String [] nextLine;

		while ((nextLine = reader.readNext()) != null) {
			if(username.equals(nextLine[0]))
				return true;
		}
		return false;
	}

	/**
	 * Returns all the user data
	 * @param None
	 * @return ArrayList<User>
	 */
	public ArrayList<User> returnAllUsers() throws IOException {
		FileReader fileReader = new FileReader("res/data/user.csv");
		CSVReader reader = new CSVReader(fileReader);


		ArrayList<User> allUsers = new ArrayList<User>();
		String [] nextLine;

		while ((nextLine = reader.readNext()) != null) {
			User u = new User(nextLine);
			allUsers.add(u);
		}
		reader.close();
		
		return allUsers;

	}
}
