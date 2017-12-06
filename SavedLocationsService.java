package waze;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
//import java.sql.Date;


public class SavedLocationsService {
    private UsersDB connection;
    
    public SavedLocationsService(UsersDB UsersDB) {
		this.connection = UsersDB;
    }
    
    public List<SavedLocation> getAll() {
	// create an empty list of SavedLocations
	List<SavedLocation> SavedLocations = new ArrayList<SavedLocation>();
	
	//get connection from db
            Connection cnt = connection.getConnection();
	
	//create string query
	String query = "SELECT * FROM " + SavedLocation.TABLE;
	try {
		//create prepared statement
		PreparedStatement ps = cnt.prepareStatement(query);
		
		//get result and store in result set
		ResultSet rs = ps.executeQuery();
		
		//transform set to list
		while(rs.next()) {
			SavedLocations.add(toSavedLocation(rs));
		}
		
		//close all resources
		ps.close();
		rs.close();
		cnt.close();
		
		System.out.println("[SavedLocationS] SELECT SUCCESS!");
	} catch (SQLException e) {
		System.out.println("[SavedLocationS] SELECT FAILED!");
		e.printStackTrace();
	}
	
	//return list
	return SavedLocations;
    }

    public static void main(String[] args) {
	SavedLocationsService service = new SavedLocationsService(new UsersDB());
	List <SavedLocation> SavedLocations = service.getAll();
	
	for (SavedLocation SavedLocation: SavedLocations) {
		System.out.println(SavedLocation);
	}
    }

    private SavedLocation toSavedLocation(ResultSet rs) throws SQLException {
        SavedLocation SavedLocation = new SavedLocation();
		
        SavedLocation.setUsername(rs.getString(SavedLocation.COL_USERNAME));
	SavedLocation.setLocationID(rs.getInt(SavedLocation.COL_LOCATIONID)); //not sure about getInt
	SavedLocation.setType(rs.getString(SavedLocation.COL_TYPE));
		
	return SavedLocation;
    }
    
    public void deleteSavedLocation(int LocationID) { //not sure if we use just locationID or if we should also use username
	//get connection
	Connection cnt = connection.getConnection();
	
	//create query
	String query = "DELETE FROM " + SavedLocation.TABLE +" WHERE LocationID = ?"; //is this how you do it? haha
	
	try {
            //create prepared statement
            PreparedStatement ps = cnt.prepareStatement(query);

            //prepare the values
            ps.setInt(1, LocationID);
            
            //execute the update
            ps.executeUpdate();

            //close resources
            ps.close();
            cnt.close();

            System.out.println("[SavedLocationS] DELETE SUCCESS!");
	} catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("[SavedLocationS] DELETE FAILED!");
            e.printStackTrace();
	}
		
		
    }
    
    public void addUser(SavedLocation SavedLocation) {
	//get connection
        Connection cnt = connection.getConnection();
				
	//create query
	String query = "INSERT INTO " + SavedLocation.TABLE +" VALUES(?, ?, ?)";
	
	try {
            //create prepared statement
            PreparedStatement ps = cnt.prepareStatement(query);
		
            //prepare the values
            ps.setString(1, SavedLocation.getUsername());
            ps.setInt(2, SavedLocation.getLocationID()); //Im sure setInt exists, found it online
            ps.setString(3, SavedLocation.getType());
          
            //execute the update
            ps.executeUpdate();
		
            //close resources
            ps.close();
            cnt.close();

            System.out.println("[SavedLocationS] INSERTION SUCCESS :3!");
	} catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("[SavedLocationS] INSERTION FAILED! :(");
            e.printStackTrace();
	}
		
    }
    
}