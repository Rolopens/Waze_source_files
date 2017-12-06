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
import static java.util.Collections.list;
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
    
    public ArrayList<String> getBestSBG(RoadDensity rd){
        int sum1 = 0, sum2 = 0, sum3 = 0;
        Connection cnt = connection.getConnection();
        String query1 = "SELECT SUM(TrafficReports) as sumtraffic1 from road_density where stName in ({0});";
        String formatted1 = String.format(query1, String.join(",", rd.getRouteSBG1()));
        String query2 = "SELECT SUM(TrafficReports) as sumtraffic2 from road_density where stName in ({0});";
        String formatted2 = String.format(query2, String.join(",", rd.getRouteSBG2()));
        String query3 = "SELECT SUM(TrafficReports) as sumtraffic3 from road_density where stName in ({0});";
        String formatted3 = String.format(query3, String.join(",", rd.getRouteSBG1()));
        
        System.out.println(formatted1 + formatted2 + formatted3);
        
        try{
            PreparedStatement ps1 = cnt.prepareStatement(formatted1);
            PreparedStatement ps2 = cnt.prepareStatement(formatted2);
            PreparedStatement ps3 = cnt.prepareStatement(formatted3);
            
            ResultSet rs1, rs2, rs3;
            
            rs1 = ps1.executeQuery();
            rs2 = ps2.executeQuery();
            rs3 = ps3.executeQuery();
            
            if(rs1.next()){
                sum1 = rs1.getInt("sumtraffic1");
            }
            if(rs2.next()){
                sum2 = rs2.getInt("sumtraffic2");
            }
            if(rs3.next()){
                sum3 = rs3.getInt("sumtraffic3");
            }
            
            if(sum1<sum2 && sum1<sum3)
                return rd.getRouteSBG1();
            
            if(sum2<sum1 && sum2<sum3)
                return rd.getRouteSBG2();
            
            if(sum3<sum1 && sum3<sum2)
                return rd.getRouteSBG3();
            
            
        }catch(SQLException e){
            System.out.println("[RoadDensity] FAILED! :(");
            e.printStackTrace();
        }
        
        return null;
    }
    
    
    
}
