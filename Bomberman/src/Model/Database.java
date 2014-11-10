package Model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class Database {
	
	public Database() {
		
	}
	
	//order of user file is in username-password-realName-numOfPlay-totalScore-levelCompleted-savedGame
	public void writeUserCSVEntry(User newUser) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(newUser.getUsername()+".csv"), ',');
		String[] entry = new String[]{newUser.getUsername(), newUser.getPassword(), newUser.getRealName(), Integer.toString(0), Integer.toString(0),
									Integer.toString(0)};
		writer.writeNext(entry);
		
		writer.close();
	}
	
	public static User readUserCSVEntry(String username) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(username+ ".csv"));
		  
		String [] nextLine;
	    
	    nextLine = reader.readNext();
	    		
	    return new User(nextLine);
	    
	}
	
	//thinking of creating each user data in separate file and game data and Leaderboard data
	
/*	
	//should think about all the valuables that needs to be saved
	public void writeGameDataCSVEntry() throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(username+"GameData.csv"), ',');
		String[] entry = new String[]{savedGame};
		
		writer.writeNext(entry);
		
		writer.close();
	}
	
	public String[] readGameDataCSVEntry() throws IOException {
		CSVReader reader = new CSVReader(new FileReader("yourfile.csv"));
		  
		String [] nextLine;
	    User acc;
	    
	    while ((nextLine = reader.readNext()) != null) {
	        // nextLine[] is an array of values from the line
	    	
	}

*/
	    
}
