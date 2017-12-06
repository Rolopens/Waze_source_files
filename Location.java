package waze;

public class Location {
    private int LocationID;
    private String LocationName;
    private String StNo;
    private String StName;
    private String City;
    private String Country;
    private String Image;
    private int IsGasStation;
    private float UnleadedPrice;
    private float DieselPrice;
    
    public static final String TABLE = "location";
    public static final String COL_LOCATIONID = "LocationID";
    public static final String COL_LOCATIONNAME = "LocationName";
    public static final String COL_STNO = "StNo";
    public static final String COL_STNAME = "StName";
    public static final String COL_CITY = "City";
    public static final String COL_COUNTRY = "Country";
    public static final String COL_IMAGE = "Image";
    public static final String COL_ISGASSTATION = "IsGasStation";
    public static final String COL_UNLEADEDPRICE = "UnleadedPrice";
    public static final String COL_DIESELPRICE = "DieselPrice";
    
    @Override
    public String toString() {
        return "location [LocationID=" + LocationID + ", StNo=" + StNo + ", StName=" + StName + ", City=" + City
        + ", Country= "+ Country + ", Image= "+ Image + ", IsGasStation= "+ IsGasStation + ", UnleadedPrice= " + UnleadedPrice
        + ", DieselPrice= " + DieselPrice +"]";
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

    public String getStNo() {
        return StNo;
    }

    public void setStNo(String StNo) {
        this.StNo = StNo;
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

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public int getIsGasStation() {
        return IsGasStation;
    }

    public void setIsGasStation(int IsGasStation) {
        this.IsGasStation = IsGasStation;
    }

    public float getUnleadedPrice() {
        return UnleadedPrice;
    }

    public void setUnleadedPrice(float UnleadedPrice) {
        this.UnleadedPrice = UnleadedPrice;
    }

    public float getDieselPrice() {
        return DieselPrice;
    }

    public void setDieselPrice(float DieselPrice) {
        this.DieselPrice = DieselPrice;
    }
    
    
    
}
