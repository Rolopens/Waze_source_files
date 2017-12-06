package waze;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
//import java.sql.Date;

public class LocationsService {
    private UsersDB connection;
    
    public LocationsService(UsersDB UsersDB){
        this.connection = UsersDB;
    }
    
    public List<Location> getAll() {
	// create an empty list of SavedLocations
	List<Location> Locations = new ArrayList<Location>();
	
	//get connection from db
            Connection cnt = connection.getConnection();
	
	//create string query
	String query = "SELECT * FROM " + Location.TABLE;
	try {
		//create prepared statement
		PreparedStatement ps = cnt.prepareStatement(query);
		
		//get result and store in result set
		ResultSet rs = ps.executeQuery();
		
		//transform set to list
		while(rs.next()) {
			Locations.add(toLocation(rs));
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
	return Locations;
    }
    
    public static void main(String[] args) {
	LocationsService service = new LocationsService(new UsersDB());
	List <Location> Locations = service.getAll();
	
	for (Location Location: Locations) {
            System.out.println(Location);
	}
    }
    
    private Location toLocation(ResultSet rs) throws SQLException {
        Location Location = new Location();
		
        Location.setLocationID(rs.getInt(Location.COL_LOCATIONID));
        Location.setLocationName(rs.getString(Location.COL_LOCATIONNAME));
        Location.setStNo(rs.getString(Location.COL_STNO));
        Location.setStName(rs.getString(Location.COL_STNAME));
        Location.setCity(rs.getString(Location.COL_CITY));
        Location.setCountry(rs.getString(Location.COL_COUNTRY));
        Location.setImage(rs.getString(Location.COL_IMAGE));      
        Location.setIsGasStation(rs.getInt(Location.COL_ISGASSTATION));
        Location.setUnleadedPrice(rs.getFloat(Location.COL_UNLEADEDPRICE));
        Location.setDieselPrice(rs.getFloat(Location.COL_DIESELPRICE));
        
	return Location;
    }
}
