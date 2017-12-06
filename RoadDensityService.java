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
        String query1 = "SELECT SUM(TrafficReports) as sumtraffic1 from road_density where stName in ('";
        String formatted1 = query1.concat(String.join("','", rd.getRouteSBG1().toArray(new CharSequence[rd.getRouteSBG1().size()]))) + "');";
        String query2 = "SELECT SUM(TrafficReports) as sumtraffic2 from road_density where stName in ('";
        String formatted2 = query2.concat(String.join("','", rd.getRouteSBG2().toArray(new CharSequence[rd.getRouteSBG2().size()]))) + "');";
        String query3 = "SELECT SUM(TrafficReports) as sumtraffic3 from road_density where stName in ('";
        String formatted3 = query3.concat(String.join("','", rd.getRouteSBG3().toArray(new CharSequence[rd.getRouteSBG3().size()]))) + "');";
        
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
            
            if(sum1<=sum2 && sum1<=sum3)
                return rd.getRouteSBG1();
            
            if(sum2<=sum3)
                return rd.getRouteSBG2();
            
                return rd.getRouteSBG3();
            
            
        }catch(SQLException e){
            System.out.println("[RoadDensity] FAILED! :(");
            e.printStackTrace();
        }
        
        return null;
    }
    
    public ArrayList<String> getBestGSP(RoadDensity rd){
        int sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0, sum5 = 0, sum6 = 0, sum7 = 0, sum8 = 0;
        Connection cnt = connection.getConnection();
        String query1 = "SELECT SUM(TrafficReports) as sumtraffic1 from road_density where stName in ('";
        String formatted1 = query1.concat(String.join("','", rd.getRouteGSP1().toArray(new CharSequence[rd.getRouteGSP1().size()]))) + "');";
        String query2 = "SELECT SUM(TrafficReports) as sumtraffic2 from road_density where stName in ('";
        String formatted2 = query2.concat(String.join("','", rd.getRouteGSP2().toArray(new CharSequence[rd.getRouteGSP2().size()]))) + "');";
        String query3 = "SELECT SUM(TrafficReports) as sumtraffic3 from road_density where stName in ('";
        String formatted3 = query3.concat(String.join("','", rd.getRouteGSP3().toArray(new CharSequence[rd.getRouteGSP3().size()]))) + "');";
        String query4 = "SELECT SUM(TrafficReports) as sumtraffic4 from road_density where stName in ('";
        String formatted4 = query4.concat(String.join("','", rd.getRouteGSP4().toArray(new CharSequence[rd.getRouteGSP4().size()]))) + "');";
        String query5 = "SELECT SUM(TrafficReports) as sumtraffic5 from road_density where stName in ('";
        String formatted5 = query5.concat(String.join("','", rd.getRouteGSP2().toArray(new CharSequence[rd.getRouteGSP5().size()]))) + "');";
        String query6 = "SELECT SUM(TrafficReports) as sumtraffic6 from road_density where stName in ('";
        String formatted6 = query6.concat(String.join("','", rd.getRouteGSP6().toArray(new CharSequence[rd.getRouteGSP6().size()]))) + "');";
        String query7 = "SELECT SUM(TrafficReports) as sumtraffic7 from road_density where stName in ('";
        String formatted7 = query7.concat(String.join("','", rd.getRouteGSP7().toArray(new CharSequence[rd.getRouteGSP7().size()]))) + "');";
        String query8 = "SELECT SUM(TrafficReports) as sumtraffic8 from road_density where stName in ('";
        String formatted8 = query8.concat(String.join("','", rd.getRouteGSP8().toArray(new CharSequence[rd.getRouteGSP8().size()]))) + "');";

        System.out.println(formatted1 + formatted2 + formatted3);
        
        try{
            PreparedStatement ps1 = cnt.prepareStatement(formatted1);
            PreparedStatement ps2 = cnt.prepareStatement(formatted2);
            PreparedStatement ps3 = cnt.prepareStatement(formatted3);
            PreparedStatement ps4 = cnt.prepareStatement(formatted4);
            PreparedStatement ps5 = cnt.prepareStatement(formatted5);
            PreparedStatement ps6 = cnt.prepareStatement(formatted6);
            PreparedStatement ps7 = cnt.prepareStatement(formatted7);
            PreparedStatement ps8 = cnt.prepareStatement(formatted8);
            
            ResultSet rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8;
            
            rs1 = ps1.executeQuery();
            rs2 = ps2.executeQuery();
            rs3 = ps3.executeQuery();
            rs4 = ps4.executeQuery();
            rs5 = ps5.executeQuery();
            rs6 = ps6.executeQuery();
            rs7 = ps7.executeQuery();
            rs8 = ps8.executeQuery();
            
            if(rs1.next()){
                sum1 = rs1.getInt("sumtraffic1");
            }
            if(rs2.next()){
                sum2 = rs2.getInt("sumtraffic2");
            }
            if(rs3.next()){
                sum3 = rs3.getInt("sumtraffic3");
            }
            if(rs4.next()){
                sum4 = rs4.getInt("sumtraffic4");
            }
            if(rs5.next()){
                sum5 = rs5.getInt("sumtraffic5");
            }
            if(rs6.next()){
                sum6 = rs6.getInt("sumtraffic6");
            }
            if(rs7.next()){
                sum7 = rs7.getInt("sumtraffic7");
            }
            if(rs8.next()){
                sum8 = rs8.getInt("sumtraffic8");
            }
            
            if(sum1<=sum2 && sum1<=sum3 && sum1<=sum4 && sum1<=sum5 && sum1<=sum6 && sum1<=sum7 && sum1<=sum8)
                return rd.getRouteGSP1();
            
            if(sum2<=sum3 && sum2<=sum4 && sum2<=sum5 && sum2<=sum6 && sum2<=sum7 && sum2<=sum8)
                return rd.getRouteGSP2();
            
            if(sum3<=sum4 && sum3<=sum5 && sum3<=sum6 && sum3<=sum7 && sum3<=sum8)
                return rd.getRouteGSP3();
            
            if(sum4<=sum5 && sum4<=sum6 && sum4<=sum7 && sum4<=sum8)
                return rd.getRouteGSP4();
            
            if(sum5<=sum6 && sum5<=sum7 && sum5<=sum8)
                return rd.getRouteGSP5();
            
            if(sum6<=sum7 && sum6<=sum8)
                return rd.getRouteGSP6();
            
            if(sum7<=sum8)
                return rd.getRouteGSP7();
            
                return rd.getRouteGSP8();
            
        }catch(SQLException e){
            System.out.println("[RoadDensity] FAILED! :(");
            e.printStackTrace();
        }
        
        return null;
    }
    
    public ArrayList<String> getBestSBP(RoadDensity rd){
        int sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0;
        Connection cnt = connection.getConnection();
        String query1 = "SELECT SUM(TrafficReports) as sumtraffic1 from road_density where stName in ('";
        String formatted1 = query1.concat(String.join("','", rd.getRouteSBP1().toArray(new CharSequence[rd.getRouteSBP1().size()]))) + "');";
        String query2 = "SELECT SUM(TrafficReports) as sumtraffic2 from road_density where stName in ('";
        String formatted2 = query2.concat(String.join("','", rd.getRouteSBP2().toArray(new CharSequence[rd.getRouteSBP2().size()]))) + "');";
        String query3 = "SELECT SUM(TrafficReports) as sumtraffic3 from road_density where stName in ('";
        String formatted3 = query3.concat(String.join("','", rd.getRouteSBP3().toArray(new CharSequence[rd.getRouteSBP3().size()]))) + "');";
        String query4 = "SELECT SUM(TrafficReports) as sumtraffic4 from road_density where stName in ('";
        String formatted4 = query4.concat(String.join("','", rd.getRouteSBP4().toArray(new CharSequence[rd.getRouteSBP4().size()]))) + "');";
        
        System.out.println(formatted1 + formatted2 + formatted3 + formatted4);
        
        try{
            PreparedStatement ps1 = cnt.prepareStatement(formatted1);
            PreparedStatement ps2 = cnt.prepareStatement(formatted2);
            PreparedStatement ps3 = cnt.prepareStatement(formatted3);
            PreparedStatement ps4 = cnt.prepareStatement(formatted4);
            
            ResultSet rs1, rs2, rs3, rs4;
            
            rs1 = ps1.executeQuery();
            rs2 = ps2.executeQuery();
            rs3 = ps3.executeQuery();
            rs4 = ps4.executeQuery();
            
            if(rs1.next()){
                sum1 = rs1.getInt("sumtraffic1");
            }
            if(rs2.next()){
                sum2 = rs2.getInt("sumtraffic2");
            }
            if(rs3.next()){
                sum3 = rs3.getInt("sumtraffic3");
            }
            if(rs4.next()){
                sum4 = rs4.getInt("sumtraffic4");
            }
            
            if(sum1<=sum2 && sum1<=sum3 && sum1<=sum4)
                return rd.getRouteSBP1();
            
            if(sum2<=sum3 && sum2<=sum4)
                return rd.getRouteSBP2();
            
            if(sum3<=sum4)
                return rd.getRouteSBP3();
            
            return rd.getRouteSBP4();
            
        }catch(SQLException e){
            System.out.println("[RoadDensity] FAILED! :(");
            e.printStackTrace();
        }
        
        return null;
    }
    
}
