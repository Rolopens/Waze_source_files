package waze;

import java.sql.Date;

public class User {
	private String Username;
	private String Password;
	private String LastName;
	private String FirstName;
	private String Avatar;
	private String PhoneNo;
	private String Email;
	private String LastLogin;
	
	
	public static final String TABLE = "useraccounts";
	public static final String COL_USERNAME = "Username";
	public static final String COL_PASSWORD = "Password";
	public static final String COL_LASTNAME = "LastName";
	public static final String COL_FIRSTNAME = "FirstName";
	public static final String COL_AVATAR = "Avatar";
	public static final String COL_PHONENO = "PhoneNo";
	public static final String COL_EMAIL = "Email";
	public static final String COL_LASTLOGIN = "LastLogin";
	

	@Override
	public String toString() {
		return "useraccounts [Username=" + Username + ", LastName=" + LastName + ", FirstName=" + FirstName + ", Avatar=" + Avatar
				+ ", PhoneNo= "+ PhoneNo + ", Email= "+ Email + ", LastLogin= "+ LastLogin + "]";
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getAvatar() {
		return Avatar;
	}

	public void setAvatar(String avatar) {
		Avatar = avatar;
	}

	public String getPhoneNo() {
		return PhoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getLastLogin() {
		return LastLogin;
	}

	public void setLastLogin(String lastLogin) {
		LastLogin = lastLogin;
	}
}
