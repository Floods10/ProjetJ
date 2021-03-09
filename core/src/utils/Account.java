package utils;

public class Account {

	private Integer id;
	private String login;
	private String password;
	private int win;
	private int lost;
	private int monsterKills;
	
	public Account() {}

	public Account(Integer id, String login, String password, int win, int lost, int monsterKills) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.win = win;
		this.lost = lost;
		this.monsterKills = monsterKills;
	}
	public Account(String login, String password) {
		this(null, login, password, 0, 0, 0);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLost() {
		return lost;
	}

	public void setLost(int lost) {
		this.lost = lost;
	}

	public int getMonsterKill() {
		return monsterKills;
	}

	public void setMonsterKill(int monsterKills) {
		this.monsterKills = monsterKills;
	}

	@Override
	public String toString() {
		return "Account (id=" + id + ", login=" + login + ", win=" + win + ", lost=" + lost
				+ ", monsterKills=" + monsterKills + ")";
	}
	
	
	

}
