package waze;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
//import java.sql.Date;


public class FriendsService {
    private UsersDB connection;
    
    public FriendsService(UsersDB UsersDB){
        this.connection = UsersDB;
    }
    
        public List<Friend> getAll() {
	// create an empty list of SavedLocations
	List<Friend> Friends = new ArrayList<Friend>();
	
	//get connection from db
            Connection cnt = connection.getConnection();
	
	//create string query
	String query = "SELECT * FROM " + Friend.TABLE;
	try {
		//create prepared statement
		PreparedStatement ps = cnt.prepareStatement(query);
		
		//get result and store in result set
		ResultSet rs = ps.executeQuery();
		
		//transform set to list
		while(rs.next()) {
			Friends.add(toFriend(rs));
		}
		
		//close all resources
		ps.close();
		rs.close();
		cnt.close();
		
		System.out.println("[FriendS] SELECT SUCCESS!");
	} catch (SQLException e) {
		System.out.println("[FriendS] SELECT FAILED!");
		e.printStackTrace();
	}
	
	//return list
	return Friends;
    }
        
    public static void main(String[] args) {
	FriendsService service = new FriendsService(new UsersDB());
	List <Friend> Friends = service.getAll();
	
	for (Friend Friend: Friends) {
		System.out.println(Friend);
	}
    }
    
    private Friend toFriend(ResultSet rs) throws SQLException {
        Friend Friend = new Friend();
		
        Friend.setUsername(rs.getString(Friend.COL_USERNAME));
	Friend.setFriendsUsername(rs.getString(Friend.COL_FRIENDSUSERNAME));
		
	return Friend;
    }
    
    public void addFriend(Friend Friend) {
	//get connection
        Connection cnt = connection.getConnection();
				
	//create query
	String query = "INSERT INTO " + Friend.TABLE +" VALUES(?, ?)";
	
	try {
            //create prepared statement
            PreparedStatement ps = cnt.prepareStatement(query);
		
            //prepare the values
            ps.setString(1, Friend.getUsername());
            ps.setString(2, Friend.getFriendsUsername());
          
            //execute the update
            ps.executeUpdate();
		
            //close resources
            ps.close();
            cnt.close();

            System.out.println("[FriendS] INSERTION SUCCESS :3!");
	} catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("[FriendS] INSERTION FAILED! :(");
            e.printStackTrace();
	}
		
    }
    
    public void deleteFriend(String FriendsUsername) { //not sure if we use just locationID or if we should also use username
	//get connection
	Connection cnt = connection.getConnection();
	
	//create query
	String query = "DELETE FROM " + Friend.TABLE +" WHERE FriendsUsername = ?"; //is this how you do it? haha
	
	try {
            //create prepared statement
            PreparedStatement ps = cnt.prepareStatement(query);

            //prepare the values
            ps.setString(1, FriendsUsername);
            
            //execute the update
            ps.executeUpdate();

            //close resources
            ps.close();
            cnt.close();

            System.out.println("[FriendS] DELETE SUCCESS!");
	} catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("[FriendS] DELETE FAILED!");
            e.printStackTrace();
	}
		
		
    }
}
