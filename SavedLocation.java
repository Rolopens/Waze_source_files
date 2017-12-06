package waze;

public class SavedLocation {
    private String Username;
    private int LocationID;
    private String Type;
    
    public static final String TABLE = "saved_locations";
    public static final String COL_USERNAME = "Username";
    public static final String COL_LOCATIONID = "LocationID";
    public static final String COL_TYPE = "type";
    
    @Override
	public String toString() {
		return "saved_locations [Username=" + Username + ", LocationID=" + LocationID + ", type=" + Type + "]";
	}
        
        public String getUsername(){
            return Username;
        }
        
        public int getLocationID(){
            return LocationID;
        }
        
        public String getType(){
            return Type;
        }
        
        public void setUsername(String username){
            Username = username;
        }
        
        public void setLocationID(int locationID){
            LocationID = locationID;
        }
        
        public void setType(String type){
            Type = type;
        }
    
}
