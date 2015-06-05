package fr.laple.model.user;

/**
 * This class is a singleton and contains all the user related data
 *
 * @author anthonyrey
 */
public class User {

    private String name;
    private String surname;
    private char[] password;
    private String email;
    private boolean online;

    private static User userInstance;

    private User(){
    }

    /**
     * @return An unique instance of User
     */
    public static synchronized User getInstance()
    {
        if(userInstance == null)
            userInstance = new User();

        return userInstance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
