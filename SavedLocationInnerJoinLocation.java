package waze;
//test
public class SavedLocationInnerJoinLocation {
    private String Username;
    private int LocationID;
    private String LocationName;
    private String StName;
    private String City;
    private String Country;// friends username
    
    //public static final String TABLE = "friends";
    public static final String COL_USERNAME = "Username";
    public static final String COL_LOCATIONID ="LocationID";
    public static final String COL_LOCATIONNAME ="LocationName";
    public static final String COL_ST_NAME ="StName";
    public static final String COL_CITY ="City";
    public static final String Col_COUNTRY = "Country";
    
    
    /*@Override
    public String toString() {
        return "friends [Username=" + Username + ", LastName=" + LastName + ", FirstName="+FirstName+", Avatar="+Avatar+", LastLogin="+LastLogin + "]";
    }*/

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public int getLocationID() {
        return LocationID;
    }

    public void setLocationID(int LocationID) {
        this.LocationID = LocationID;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String LocationName) {
        this.LocationName = LocationName;
    }

    public String getStName() {
        return StName;
    }

    public void setStName(String StName) {
        this.StName = StName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    
    
    
}

