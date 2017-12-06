/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waze;

/**
 *
 * @author dlsza
 */
public class RoadDensity {
    private String username;
    private int LocationID;
    private String stName;
    private int trafficReports;
    
    
    public static final String TABLE = "road_density";     
    public static final String COL_USERNAME = "Username";
    public static final String COL_LOCATIONID = "LocationID";
    public static final String COL_STNAME = "StName";
    public static final String COL_TRAFFICREPORTS = "TrafficReports";

    @Override
    public String toString() {
	return "road_density [Username=" + username + ", LocationID=" + LocationID + ", StName=" + stName + ", TrafficReports="+ trafficReports +"]";
    }
    
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLocationID() {
        return LocationID;
    }

    public void setLocationID(int LocationID) {
        this.LocationID = LocationID;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public int getTrafficReports() {
        return trafficReports;
    }

    public void setTrafficReports(int trafficReports) {
        this.trafficReports = trafficReports;
    }
    
    
    
    
}
