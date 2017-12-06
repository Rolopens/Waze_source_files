package waze;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
//import java.sql.Date;

import com.sun.org.apache.bcel.internal.generic.Type;
import java.sql.Statement;



public class UsersService {
	private UsersDB connection;
	
	public UsersService(UsersDB UsersDB) {
		this.connection = UsersDB;
	}

	public List<User> getAll() {
		// create an empty list of Users
		List<User> Users = new ArrayList<User>();
		
		//get connection from db
		Connection cnt = connection.getConnection();
		
		//create string query
		String query = "SELECT * FROM " + User.TABLE;
		try {
			//create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//get result and store in result set
			ResultSet rs = ps.executeQuery();
			
			//transform set to list
			while(rs.next()) {
				Users.add(toUser(rs));
			}
			
			//close all resources
			ps.close();
			rs.close();
			cnt.close();
			
			System.out.println("[UserS] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[UserS] SELECT FAILED!");
			e.printStackTrace();
		}
		
		//return list
		return Users;
	}

	public List<User> getFriends(User x) {
		// create an empty list of Users
		List<User> Friends = new ArrayList<User>();
		
		//get connection from db
		Connection cnt = connection.getConnection();
		
		//create string query
		String query = "SELECT " + User.COL_USERNAME
                        + ", " + User.COL_PASSWORD
                        + ", " + User.COL_LASTNAME
                        + ", " + User.COL_FIRSTNAME
                        + ", " + User.COL_AVATAR
                        + ", " + User.COL_PHONENO
                        + ", " + User.COL_EMAIL
                        + ", " + User.COL_LASTLOGIN
                        + " FROM (" + User.TABLE + " U INNER JOIN " + Friend.TABLE
                        + " ON U." + User.COL_USERNAME + " = " + Friend.COL_FRIENDSUSERNAME
                        + ") WHERE U." + User.COL_USERNAME + " = ?";
		try {
			//create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
                        System.out.println(query);
                        
			ps.setString(1, x.getUsername());
			
			//get result and store in result set
			ResultSet rs = ps.executeQuery();
			
			//transform set to list
			while(rs.next()) {
				Friends.add(toUser(rs));
			}
			
			//close all resources
			ps.close();
			rs.close();
			cnt.close();
			
			System.out.println("[UserS] SELECT SUCCESS!");
		} catch (SQLException e) {
			System.out.println("[UserS] SELECT FAILED!");
			e.printStackTrace();
		}
		
		//return list
		return Friends;
	}
	
	public static void main(String[] args) {
		UsersService service = new UsersService(new UsersDB());
		List <User> Users = service.getAll();
		
		for (User User: Users) {
			System.out.println(User);
		}
	}

	private User toUser(ResultSet rs) throws SQLException {
		User User = new User();
		
		User.setUsername(rs.getString(User.COL_USERNAME));
		User.setPassword(rs.getString(User.COL_PASSWORD));
		User.setLastName(rs.getString(User.COL_LASTNAME));
		User.setFirstName(rs.getString(User.COL_FIRSTNAME));
		User.setAvatar(rs.getString(User.COL_AVATAR));
		User.setPhoneNo(rs.getString(User.COL_PHONENO));
		User.setEmail(rs.getString(User.COL_EMAIL));
		User.setLastLogin(rs.getDate(User.COL_LASTLOGIN));
		//User.setGroup(rs.getString(User.COL_GROUP));
		//User.setMobile(rs.getString(User.COL_MOBILE));
		//User.setName(rs.getString(User.COL_NAME));
		
		return User;
	}

	public void deleteUser(String username) {
		//get connection
		Connection cnt = connection.getConnection();
		
		//create query
		String query = "DELETE FROM " + User.TABLE +" WHERE Username = ?";
		
		try {
			//create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//prepare the values
			ps.setString(1, username);
			
			//execute the update
			ps.executeUpdate();
			
			//close resources
			ps.close();
			cnt.close();
	
			System.out.println("[UserS] DELETE SUCCESS!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[UserS] DELETE FAILED!");
			e.printStackTrace();
		}
		
		
	}

	public void addUser(User User) {
		//get connection
				Connection cnt = connection.getConnection();
				
				//create query
				String query = "INSERT INTO " + User.TABLE + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
				
				try {
					//create prepared statement
					PreparedStatement ps = cnt.prepareStatement(query);
					
					//prepare the values
					ps.setString(1, User.getUsername());
					ps.setString(2, User.getPassword());
					ps.setString(3, User.getLastName());
					ps.setString(4, User.getFirstName());
					ps.setString(5, "default.png");
					ps.setString(6, User.getPhoneNo());
					ps.setString(7, User.getEmail());
					ps.setDate(8, null);
					
					//execute the update
					ps.executeUpdate();
					
					//close resources
					ps.close();
					cnt.close();
			
					System.out.println("[UserS] INSERTION SUCCESS :3!");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("[UserS] INSERTION FAILED! :(");
					e.printStackTrace();
				}
		
	}

	public void updateUser(User User) {
		//get connection
		Connection cnt = connection.getConnection();
		
		//create query
		String query = "UPDATE " + User.TABLE +
				" SET "
				+ User.COL_EMAIL + " = ?,"
				+ User.COL_PASSWORD + " = ?,"
				+ User.COL_PHONENO + " = ?,"
				+ User.COL_LASTNAME + " = ?,"
				+ User.COL_FIRSTNAME + " = ?" + 
				" WHERE " + User.COL_USERNAME + " = ?";
		
		try {
			//create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
                        
			//prepare the values
			ps.setString(1, User.getEmail());
			ps.setString(2, User.getPassword());
			ps.setString(3, User.getPhoneNo());
			ps.setString(4, User.getLastName());
			ps.setString(5, User.getFirstName());
			ps.setString(6, User.getUsername());
			
			//execute the update
			ps.executeUpdate();
			
			//close resources
			ps.close();
			cnt.close();
	
			System.out.println("[UserS] UPDATION SUCCESS :3!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[UserS] UPDATION FAILED! :(");
			e.printStackTrace();
		}
	}

	public List<User> getAllByGroup(String group) {
		
		return null;
	}

}
