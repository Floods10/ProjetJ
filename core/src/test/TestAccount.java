package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import dao.DAOAccount;
import utils.Account;

public class TestAccount {
	
	static Account compteConnected;

	public static String saisieString(String message) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(message);
		return sc.nextLine();
	}

	public static int saisieInt(String message) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(message);
		return sc.nextInt();
	}

	
	static DAOAccount daoAccount= new DAOAccount();

	public static void main(String[] args) {
		menuPrincipal();
	}


	private static void menuPrincipal() {
		compteConnected = null;
		System.out.println("\nMenu gestion comptes, merci de faire un choix :");
		System.out.println("1 - S'identifier");
		System.out.println("2 - Lister les comptes");
		System.out.println("3 - Ajouter un compte");
		System.out.println("4 - Modifier un compte");
		System.out.println("5 - Delete un compte");
		System.out.println("6 - Quitter");
		int choix = saisieInt("");
		
		switch(choix) 
		{
		case 1 : authentificate();break;
		case 2 : showAccount();break;
		case 3 : addAccount();break;
		case 4 : updateAccount();break;
		case 5 : deleteAccount();break;
		case 6 : System.exit(0);break;
		default : System.out.println("Choix impossible !\n");
		}
		menuPrincipal();		
	}
		
	private static void authentificate() {
		String login=saisieString("Saisir login");
		String password=saisieString("Saisir password");
		
		compteConnected = daoAccount.authenticate(login, password);
		
		if (compteConnected!=null) {
			System.out.println("Bonjour "+compteConnected.getLogin()+", vous êtes connecté!\n>>"+compteConnected);
		} else {
			System.out.println("La connexion a échoué.");
		}
	}

	public static void showAccount() {
		for(Account account : daoAccount.findAll()){
			System.out.println(account);
		}
	}
	
	public static void addAccount() {
		String login=saisieString("Saisir login");
		String password=saisieString("Saisir password");
		
		daoAccount.insert(new Account(login, password));
	}
	
	private static Account selectAcc() 
	{
		System.out.println("Choisir un compte : ");
		List<Account> accounts = daoAccount.findAll();
		int nParties;
		
		for(int i=0;i<accounts.size();i++) {
			Account a = accounts.get(i);
			nParties = a.getWin()+a.getLost();
			System.out.println(i+" - "+a.getLogin()+"("+nParties+" parties)");
		}
		int choix = saisieInt("");
		Account account = accounts.get(choix);
		return account;
	}
	
	public static void updateAccount() {
		Account account = selectAcc();
		System.out.println(account);
		String login = saisieString("Nouveau login: ");
		String password = saisieString("Nouveau password: ");
		int win = saisieInt("Nouveau win: ");
		int lost = saisieInt("Nouveau lost: ");
		int monsterKills = saisieInt("Nouveau monsterKills: ");

		account.setLogin(login);
		account.setPassword(password);
		account.setWin(win);
		account.setLost(lost);
		account.setMonsterKill(monsterKills);
		
		daoAccount.update(account);
	}
	
	public static void deleteAccount() {
		daoAccount.delete(selectAcc());
	}
		
		

}
