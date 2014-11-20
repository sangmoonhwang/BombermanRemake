package Model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class Database {
	
	public Database() {
		
	}
	
	/**
	 * Add the new user to the user database
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
		
		if(nextLine == null)
			return null;
		
	    return new User(nextLine);
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
	 * Writes the save game data name and the username associated to
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
		
	    return list;
	    	
	}
	
	/**
	 * read all the saved game data of the current user from the save.csv
	 * @param User
	 * @return Arraylist of all the saved game of the user
	 */
	
	public void loadGameDAtaCSVEntry(String save) throws IOException {
		FileReader fileReader = new FileReader("user.csv");
		CSVReader reader = new CSVReader(fileReader);
		  
		String [] nextLine;
	    
		nextLine = reader.readNext();
		
		//add the correct load game sequence on the bottom
	}
}
