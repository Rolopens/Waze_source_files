package waze;
//test
public class FriendList {
    private String Username;
    private String LastName;
    private String FirstName;
    private String Avatar;
    private String LastLogin;// friends username
    
    //public static final String TABLE = "friends";
    public static final String COL_USERNAME = "Username";
    public static final String COL_LASTNAME ="LastName";
    public static final String COL_FIRSTNAME ="FirstName";
    public static final String COL_AVATAR ="Avatar";
    public static final String COL_LASTLOGIN ="LastLogin";
    
    
    @Override
    public String toString() {
        return "friends [Username=" + Username + ", LastName=" + LastName + ", FirstName="+FirstName+", Avatar="+Avatar+", LastLogin="+LastLogin + "]";
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String Avatar) {
        this.Avatar = Avatar;
    }

    public String getLastLogin() {
        return LastLogin;
    }

    public void setLastLogin(String LastLogin) {
        this.LastLogin = LastLogin;
    }
    
    
}

