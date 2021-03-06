package Model;

import java.io.Serializable;

/**
 * User model
 *
 */
public class User implements Serializable{

	private String username;
	private String password;
	private String realName;
	private int numOfPlay;
	private int totalScore;
	private int levelCompleted;
	private String[] savedGame;

	//creating new user
	/**
	 * constructor
	 * @param username new username
	 * @param password new password
	 * @param realName new realname
	 */
	public User(String username, String password, String realName) {
		this.setUsername(username);
		this.setPassword(password);
		this.setRealName(realName);
		setNumOfPlay(0);
		setTotalScore(0);
		setLevelCompleted(0);
	}

	//existing user
	/**
	 * constructor
	 * @param data existing user
	 */
	public User(String[] data) {
		this.setUsername(data[0]);
		this.setPassword(data[1]);
		this.setRealName(data[2]);
		setNumOfPlay(Integer.parseInt(data[3]));
		setTotalScore(Integer.parseInt(data[4]));
		setLevelCompleted(Integer.parseInt(data[5]));
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getNumOfPlay() {
		return numOfPlay;
	}

	public void setNumOfPlay(int numOfPlay) {
		this.numOfPlay = numOfPlay;
	}

	public int getTotalScore() {
		return totalScore;
	}

	/**
	 * adds up the score by i
	 * @param i score to increase by
	 */
	public void updateScore(int i) {
		totalScore += i;
	}

	public void setTotalScore(int t) {
		totalScore = t;
	}

	public int getLevelCompleted() {
		return levelCompleted;
	}

	public void setLevelCompleted(int levelCompleted) {
		this.levelCompleted = levelCompleted;
	}

	public String[] getSavedGame() {
		return savedGame;
	}

	public void setSavedGame(String[] savedGame) {
		this.savedGame = savedGame;
	}

}
