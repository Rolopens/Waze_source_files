/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waze;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author dlsza
 */
public class RoadDensityService {
    private UsersDB connection;
    
    public RoadDensityService(UsersDB UsersDB) {
		this.connection = UsersDB;
    }
    
    public List<RoadDensity> getAll() {
	// create an empty list of SavedLocations
	List<RoadDensity> RoadDensities = new ArrayList<RoadDensity>();
	
	//get connection from db
            Connection cnt = connection.getConnection();
	
	//create string query
	String query = "SELECT * FROM " + RoadDensity.TABLE;
	try {
		//create prepared statement
		PreparedStatement ps = cnt.prepareStatement(query);
		
		//get result and store in result set
		ResultSet rs = ps.executeQuery();
		
		//transform set to list
		while(rs.next()) {
			RoadDensities.add(toRoadDensity(rs));
		}
		
		//close all resources
		ps.close();
		rs.close();
		cnt.close();
		
		System.out.println("[RoadDensityServiceS] SELECT SUCCESS!");
	} catch (SQLException e) {
		System.out.println("[RoadDensityServiceS] SELECT FAILED!");
		e.printStackTrace();
	}
	
	//return list
	return RoadDensities;
    }
    
    public static void main(String[] args) {
	SavedLocationsService service = new SavedLocationsService(new UsersDB());
	List <SavedLocation> SavedLocations = service.getAll();
	
	for (SavedLocation SavedLocation: SavedLocations) {
		System.out.println(SavedLocation);
	}
    }

    private RoadDensity toRoadDensity(ResultSet rs) throws SQLException {
        RoadDensity RoadDensity = new RoadDensity();

	RoadDensity.setStName(rs.getString(RoadDensity.COL_STNAME));
	RoadDensity.setTrafficReports(rs.getInt(RoadDensity.COL_HIGHTRAFFICREPORTS));
        
	return RoadDensity;
    }
    
    public void addTraficReport(RoadDensity rd, String stName) {
        Connection cnt = connection.getConnection();
        String query = "UPDATE waze." + rd.TABLE + " SET " + rd.COL_HIGHTRAFFICREPORTS + "=" + rd.COL_HIGHTRAFFICREPORTS + " + 1" + 
                " WHERE StName ='" + stName+"'";
        System.out.println(query);
        try{
            PreparedStatement ps = cnt.prepareStatement(query);
            //ps.setString(1, rd.getStName());
            //ps.setInt(2, rd.getTrafficReports());
            ps.executeUpdate();
            
            ps.close();
            cnt.close();
	
            System.out.println("[RoadDensity] UPDATION SUCCESS :3!");
        } catch(SQLException e){
            System.out.println("[RoadDensity] UPDATION FAILED! :(");
            e.printStackTrace();
        }
    }
    
}
