package waze;
//test
public class Friend {
    private String Username;
    private String FriendsUsername; // friends username
    
    public static final String TABLE = "friends";
    public static final String COL_USERNAME = "Username";
    public static final String COL_FRIENDSUSERNAME ="FriendsUsername";
    
    @Override
    public String toString() {
        return "friends [Username=" + Username + ", FriendsUsername=" + FriendsUsername + "]";
    }
    
    public String getUsername(){
        return Username;
    }
    
    public String getFriendsUsername(){
        return FriendsUsername;
    }
    
    public void setUsername(String username){
        Username = username;
    }
    
    public void setFriendsUsername(String friendsusername){
        FriendsUsername = friendsusername;
    }
}
