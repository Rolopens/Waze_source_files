package waze;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
//import java.sql.Date;


public class FriendListService {
    private UsersDB connection;
    
    public FriendListService(UsersDB UsersDB){
        this.connection = UsersDB;
    }
    
        public List<FriendList> getAll(String username) {
	// create an empty list of SavedLocations
	List<FriendList> friends = new ArrayList<FriendList>();
	
	//get connection from db
            Connection cnt = connection.getConnection();
	
	//create string query
	String query = "select f.Username, LastName, FirstName, Avatar, LastLogin FROM useraccounts u inner join friends f on f.FriendsUsername = U.Username WHERE f.Username = '?'";
	try {
		//create prepared statement
		PreparedStatement ps = cnt.prepareStatement(query);
                
                ps.setString(1, username);
		
		//get result and store in result set
		ResultSet rs = ps.executeQuery();
		
		//transform set to list
		while(rs.next()) {
			friends.add(toFriend(rs));
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
	return friends;
    }
        
    public static void main(String[] args) {
	
	
    }
    
    private FriendList toFriend(ResultSet rs) throws SQLException {
        FriendList Friend = new FriendList();
		
        Friend.setUsername(rs.getString(FriendList.COL_USERNAME));
	Friend.setLastName(rs.getString(FriendList.COL_LASTNAME));
        Friend.setFirstName(rs.getString(FriendList.COL_FIRSTNAME));
        Friend.setAvatar(rs.getString(FriendList.COL_AVATAR));
        Friend.setLastLogin(rs.getString(FriendList.COL_LASTLOGIN));
		
	return Friend;
    }
    /*
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
    */
    /*
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
*/
}


