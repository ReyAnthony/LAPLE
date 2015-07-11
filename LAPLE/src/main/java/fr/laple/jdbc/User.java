package fr.laple.jdbc;

public class User {
	static String name;
	static String email;
	static Boolean online;
	static int id;
	
	
	public static void profile(String myName, String myEmail, Boolean myOnline, int myId){
		name=myName;
		email=myEmail;
		online=myOnline;
		id=myId;
	}
	public static String myString() {
		return "Profile [name=" + name + ", email=" + email + "]";
	}
	
}
