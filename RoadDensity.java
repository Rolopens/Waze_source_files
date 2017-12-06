/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waze;

import java.util.ArrayList;

/**
 *
 * @author dlsza
 */
public class RoadDensity {
    private String stName;
    private int trafficReports;
    private static final ArrayList<String>routeSBG1 = new ArrayList<String>(){{
    add("Don Manolo");
    add("Don Jesus");
    add("Bacolod");
    add("Angeles I");
    add("Angeles II");
    }};
    private static final ArrayList<String>routeSBG2 = new ArrayList<String>(){{
    add("Don Manolo");
    add("Don Jesus");
    add("Bacolod");
    add("Cebu");
    add("Angeles II");
    }};
    private static final ArrayList<String>routeSBG3 = new ArrayList<String>(){{
    add("Don Manolo");
    add("Don Jesus");
    add("Bacolod");
    add("Dona Annie");
    add("Angeles II");
    }};
    private static final ArrayList<String>routeGSP1 = new ArrayList<String>(){{
    add("Angeles II");
    add("Angeles I");
    add("Bacolod");
    add("Don Jesus");
    add("Dona Mary");
    add("Ormoc");
    add("Dona Ines II");
    add("Tacloban");
    add("Tagbilaran");
    }};
    private static final ArrayList<String>routeGSP2 = new ArrayList<String>(){{
    add("Angeles II");
    add("Angeles I");
    add("Bacolod");
    add("Don Jesus");
    add("Dona Ines I");
    add("Dona Ines II");
    add("Tacloban");
    add("Tagbilaran");
    }};
    private static final ArrayList<String>routeGSP3 = new ArrayList<String>(){{
    add("Angeles II");
    add("Angeles I");
    add("Bacolod");
    add("Don Jesus");
    add("Dona Mary");
    add("Ormoc");
    add("Dona Ines II");
    add("Trece Martires");
    add("Tagbilaran");
    }};
    private static final ArrayList<String>routeGSP4 = new ArrayList<String>(){{
    add("Angeles II");
    add("Angeles I");
    add("Bacolod");
    add("Don Jesus");
    add("Dona Ines I");
    add("Dona Ines II");
    add("Trece Martires");
    add("Tagbilaran");
    }};
    private static final ArrayList<String>routeGSP5 = new ArrayList<String>(){{
    add("Angeles II");
    add("Dona Annie");
    add("Bacolod");
    add("Don Jesus");
    add("Dona Ines I");
    add("Dona Ines II");
    add("Trece Martires");
    add("Tagbilaran");
    }};
    private static final ArrayList<String>routeGSP6 = new ArrayList<String>(){{
    add("Angeles II");
    add("Dona Annie");
    add("Don Jesus");
    add("Dona Ines I");
    add("Dona Ines II");
    add("Tacloban");
    add("Tagbilaran");
    }};
    private static final ArrayList<String>routeGSP7 = new ArrayList<String>(){{
    add("Angeles II");
    add("Dona Annie");
    add("Don Jesus");
    add("Dona Mary");
    add("Ormoc");
    add("Dona Ines II");
    add("Trece Martires");
    add("Tagbilaran");
    }};
    private static final ArrayList<String>routeGSP8 = new ArrayList<String>(){{
    add("Angeles II");
    add("Dona Annie");
    add("Don Jesus");
    add("Dona Mary");
    add("Ormoc");
    add("Dona Ines II");
    add("Tacloban");
    add("Tagbilaran");
    }};
    private static final ArrayList<String>routeSBP1 = new ArrayList<String>(){{
    add("Don Manolo");
    add("Don Jesus");
    add("Don Mary");
    add("Don Ormoc");
    add("Dona Ines II");
    add("Tacloban");
    add("Tagbiliran");
    }};
    private static final ArrayList<String>routeSBP2 = new ArrayList<String>(){{
    add("Don Manolo");
    add("Don Jesus");
    add("Don Mary");
    add("Don Ormoc");
    add("Dona Ines II");
    add("Trece Martires");
    add("Tagbiliran");
    }};
    private static final ArrayList<String>routeSBP3 = new ArrayList<String>(){{
    add("Don Manolo");
    add("Don Jesus");
    add("Don Ines I");
    add("Dona Ines II");
    add("Tacloban");
    add("Tagbiliran");
    }};
    private static final ArrayList<String>routeSBP4 = new ArrayList<String>(){{
    add("Don Manolo");
    add("Don Jesus");
    add("Don Ines I");
    add("Dona Ines II");
    add("Trece Martires");
    add("Tagbiliran");
    }};
    
    public static final String TABLE = "road_density";     
    public static final String COL_STNAME = "StName";
    public static final String COL_HIGHTRAFFICREPORTS = "TrafficReports";

    public static ArrayList<String> getRouteSBG1() {
        return routeSBG1;
    }

    public static ArrayList<String> getRouteSBG2() {
        return routeSBG2;
    }

    public static ArrayList<String> getRouteSBG3() {
        return routeSBG3;
    }

    public static ArrayList<String> getRouteGSP1() {
        return routeGSP1;
    }

    public static ArrayList<String> getRouteGSP2() {
        return routeGSP2;
    }

    public static ArrayList<String> getRouteGSP3() {
        return routeGSP3;
    }

    public static ArrayList<String> getRouteGSP4() {
        return routeGSP4;
    }

    public static ArrayList<String> getRouteGSP5() {
        return routeGSP5;
    }

    public static ArrayList<String> getRouteGSP6() {
        return routeGSP6;
    }

    public static ArrayList<String> getRouteGSP7() {
        return routeGSP7;
    }

    public static ArrayList<String> getRouteGSP8() {
        return routeGSP8;
    }

    public static ArrayList<String> getRouteSBP1() {
        return routeSBP1;
    }

    public static ArrayList<String> getRouteSBP2() {
        return routeSBP2;
    }

    public static ArrayList<String> getRouteSBP3() {
        return routeSBP3;
    }

    public static ArrayList<String> getRouteSBP4() {
        return routeSBP4;
    }

    @Override
    public String toString() {
	return "road_density [StName=" + stName + ", TrafficReports="+ trafficReports +"]";
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
