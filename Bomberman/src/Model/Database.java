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

public class Database {

	public Database() {

	}

	/**
	 * Add the new user to the user database (username, password, realName, numOfPlay, TotalScore, levelsCompleted)
	 * @param User
	 * @return None
	 */
	public void writeUserCSVEntry(User newUser) throws IOException {
		FileWriter fileWriter = new FileWriter("user.csv", true);
		CSVWriter writer = new CSVWriter(fileWriter, ',');
		String[] entry = new String[]{newUser.getUsername(), newUser.getPassword(), newUser.getRealName(), Integer.toString(0), Integer.toString(0),
				Integer.toString(0)};
		writer.writeNext(entry);

		writer.close();
	}

	/**
	 * Read the user information
	 * @param Username
	 * @return User information
	 */
	public static User readUserCSVEntry(String username) throws IOException {
		FileReader fileReader = new FileReader("user.csv");
		CSVReader reader = new CSVReader(fileReader);

		String [] nextLine;

		while ((nextLine = reader.readNext()) != null) {
			if(username.equals(nextLine[0]))
				break;
		}

		reader.close();

		if(nextLine == null)
			return null;

		return new User(nextLine);
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
		FileReader fileReader = new FileReader("user.csv");
		CSVReader reader = new CSVReader(fileReader);


		//Read all rows at once
		List<String[]> userData = reader.readAll();
		String[] user = null;

		for(int i = 0; i < userData.size(); i++) {
			user = userData.get(i);

			if(user.equals(username)) {
				userData.remove(i);
				break;
			}
		}
		reader.close();

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

		if(levelCompleted != Integer.parseInt(user[5])) {
			user[5] = Integer.toString(levelCompleted);
		}

		userData.add(user);


		FileWriter fileWriter = new FileWriter("user.csv");
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
		FileReader fileReader = new FileReader("user.csv");
		CSVReader reader = new CSVReader(fileReader);

		String [] nextLine;

		while ((nextLine = reader.readNext()) != null) {
			if(username.equals(nextLine[0]))
				return true;
		}
		return false;
	}

	/**
	 * Writes the save game data name and the username associated
	 * @param User and save data name
	 * @return None
	 */

	public void writeGameDataCSVEntry(User user, String save) throws IOException {
		FileWriter fileWriter = new FileWriter("save.csv", true);
		CSVWriter writer = new CSVWriter(fileWriter, ',');
		String[] entry = new String[]{user.getUsername(), save};    
		writer.writeNext(entry);

		writer.close();

		writeSaveDataCSVEntry(save);
	}

	/**
	 * Writes the save game data variables
	 * @param Save name
	 * @return None
	 */

	public void writeSaveDataCSVEntry(String save) throws IOException {
		FileWriter fileWriter = new FileWriter(save+".csv", false);
		CSVWriter writer = new CSVWriter(fileWriter, ',');
		String[] entry = new String[]{};    ///also add all the variables that needs to be saved.
		writer.writeNext(entry);

		writer.close();
	}

	/**
	 * read all the saved game data name of the current user from the save.csv
	 * @param User
	 * @return Arraylist of all the saved game of the user
	 */

	public ArrayList<String> readGameDataCSVEntry(User user) throws IOException {
		FileReader fileReader = new FileReader("save.csv");
		CSVReader reader = new CSVReader(fileReader);

		String [] nextLine;
		ArrayList<String> list = new ArrayList<String>();

		while ((nextLine = reader.readNext()) != null) {
			if(user.getUsername().equals(nextLine[0]))
				list.add(nextLine[1]);
		}
		reader.close();
		return list;

	}

	/**
	 * read all the saved game data of the current user from the save.csv
	 * @param User
	 * @return Arraylist of all the saved game of the user
	 */

	public void loadGameDataCSVEntry(String save) throws IOException {
		FileReader fileReader = new FileReader("user.csv");
		CSVReader reader = new CSVReader(fileReader);

		String [] nextLine;

		nextLine = reader.readNext();
		reader.close();

		//add the correct load game sequence on the bottom
	}

	/**
	 * Returns all the user data
	 * @param None
	 * @return ArrayList<User>
	 */
	public ArrayList<User> returnAllUsers() throws IOException {
		FileReader fileReader = new FileReader("user.csv");
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
