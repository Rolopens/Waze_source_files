package waze;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
//import java.sql.Date;


public class SLInnerJoinLService {
    private UsersDB connection;
    
    public SLInnerJoinLService(UsersDB UsersDB){
        this.connection = UsersDB;
    }
    
        public List<SavedLocationInnerJoinLocation> getAll(String username) {
	// create an empty list of SavedLocations
	List<SavedLocationInnerJoinLocation> locations = new ArrayList<SavedLocationInnerJoinLocation>();
	
	//get connection from db
            Connection cnt = connection.getConnection();
	
	//create string query
	String query = "select username, l.LocationID, LocationName, StName, city, country, type FROM location l inner join saved_locations sl on l.LocationID = sl.LocationID where username = ?";
	try {
		//create prepared statement
		PreparedStatement ps = cnt.prepareStatement(query);
                
                ps.setString(1, username);
		
		//get result and store in result set
		ResultSet rs = ps.executeQuery();
		
		//transform set to list
		while(rs.next()) {
			locations.add(toSLInnerJoinL(rs));
		}
		
		//close all resources
		ps.close();
		rs.close();
		cnt.close();
		
		System.out.println("[FriendLIST] SELECT SUCCESS!");
	} catch (SQLException e) {
		System.out.println("[FriendLIST] SELECT FAILED!");
		e.printStackTrace();
	}
	
	//return list
	return locations;
    }
        
    
    private SavedLocationInnerJoinLocation toSLInnerJoinL(ResultSet rs) throws SQLException {
        SavedLocationInnerJoinLocation location = new SavedLocationInnerJoinLocation();
	
        location.setUsername(rs.getString(SavedLocationInnerJoinLocation.COL_USERNAME));
        location.setLocationID(rs.getInt(SavedLocationInnerJoinLocation.COL_LOCATIONID));
        location.setLocationName(rs.getString(SavedLocationInnerJoinLocation.COL_LOCATIONNAME));
        location.setStName(rs.getString(SavedLocationInnerJoinLocation.COL_ST_NAME));
        location.setCity(rs.getString(SavedLocationInnerJoinLocation.COL_CITY));
        location.setCountry(rs.getString(SavedLocationInnerJoinLocation.Col_COUNTRY));
        
        /*
        Friend.setUsername(rs.getString(FriendList.COL_USERNAME));
	Friend.setLastName(rs.getString(FriendList.COL_LASTNAME));
        Friend.setFirstName(rs.getString(FriendList.COL_FIRSTNAME));
        Friend.setAvatar(rs.getString(FriendList.COL_AVATAR));
        Friend.setLastLogin(rs.getString(FriendList.COL_LASTLOGIN));
	*/	
	return location;
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


