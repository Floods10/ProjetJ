package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import utils.Account;



public class DAOAccount implements IDAO<Account,Integer>{

	@Override
	public Account findById(Integer id) {
		Account account = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("Select * from compte where id=?");
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) 
			{
				String login=rs.getString("login");
				String password=rs.getString("password");
				int win=rs.getInt("win");
				int lost=rs.getInt("lost");
				int monsterKills=rs.getInt("monster_kills");
				
				account = new Account(id, login, password, win, lost, monsterKills);
			}
			rs.close();
			ps.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public List<Account> findAll() {
		List<Account> accounts= new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("Select * from compte");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) 
			{
				int id=rs.getInt("id");
				String login=rs.getString("login");
				String password=rs.getString("password");
				int win=rs.getInt("win");
				int lost=rs.getInt("lost");
				int monsterKills=rs.getInt("monster_kills");
				
				accounts.add(new Account(id, login, password, win, lost, monsterKills));
			}
			rs.close();
			ps.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public void insert(Account account) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO compte (login,password,win,lost,monster_kills) VALUES (?,?,?,?,?)");
			
			ps.setString(1, account.getLogin());
			ps.setString(2, account.getPassword());
			ps.setInt(3, account.getWin());
			ps.setInt(4, account.getLost());
			ps.setInt(5, account.getMonsterKill());
		
			ps.executeUpdate();
	
			ps.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Account account) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("Update compte set login=?,password=?,win=?,lost=?,monster_kills=? where id=?");
			
			ps.setString(1, account.getLogin());
			ps.setString(2, account.getPassword());
			ps.setInt(3, account.getWin());
			ps.setInt(4, account.getLost());
			ps.setInt(5, account.getMonsterKill());
			ps.setInt(6, account.getId());
		
			ps.executeUpdate();
	
			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Account account) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("DELETE FROM compte where id=?");
			ps.setInt(1,account.getId());
		
			ps.executeUpdate();
	
			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Account authenticate(String log, String pass)
	{
		Account account = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM compte WHERE login=? and password=?");
			ps.setString(1, log);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id=rs.getInt("id");
				int win=rs.getInt("win");
				int lost=rs.getInt("lost");
				int monsterKills=rs.getInt("monster_kills");
				
				account = new Account(id, log, pass, win, lost, monsterKills);
			}
			rs.close();
			ps.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}
	

}
