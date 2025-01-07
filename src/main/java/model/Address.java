package model;

public class Address {
    private int addressId;
    private int userId;
    private String countryRegion;
    private String fullName;
    private String pincode;
    private String mobile;
    private String flatDetails;
    private String areaDetails;
    private String landmark;
    private String townCity;
    private String state;
    private String addressType;
    private String additionalInstructions;

    public Address(int addressId, int userId, String countryRegion, String fullName, String pincode,
                    String mobile, String flatDetails, String areaDetails, String landmark, 
                    String townCity, String state, String addressType, String additionalInstructions) {
        this.addressId = addressId;
        this.userId = userId;
        this.countryRegion = countryRegion;
        this.fullName = fullName;
        this.pincode = pincode;
        this.mobile = mobile;
        this.flatDetails = flatDetails;
        this.areaDetails = areaDetails;
        this.landmark = landmark;
        this.townCity = townCity;
        this.state = state;
        this.addressType = addressType;
        this.additionalInstructions = additionalInstructions;
    }
    public Address(int userId, String countryRegion, String fullName, String pincode,
                    String mobile, String flatDetails, String areaDetails, String landmark, 
                    String townCity, String state, String addressType, String additionalInstructions) {
        this.userId = userId;
        this.countryRegion = countryRegion;
        this.fullName = fullName;
        this.pincode = pincode;
        this.mobile = mobile;
        this.flatDetails = flatDetails;
        this.areaDetails = areaDetails;
        this.landmark = landmark;
        this.townCity = townCity;
        this.state = state;
        this.addressType = addressType;
        this.additionalInstructions = additionalInstructions;
    }

    // Default Constructor
    public Address() {}

    // Getters and Setters
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFlatDetails() {
        return flatDetails;
    }

    public void setFlatDetails(String flatDetails) {
        this.flatDetails = flatDetails;
    }

    public String getAreaDetails() {
        return areaDetails;
    }

    public void setAreaDetails(String areaDetails) {
        this.areaDetails = areaDetails;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getTownCity() {
        return townCity;
    }

    public void setTownCity(String townCity) {
        this.townCity = townCity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAdditionalInstructions() {
        return additionalInstructions;
    }

    public void setAdditionalInstructions(String additionalInstructions) {
        this.additionalInstructions = additionalInstructions;
    }
}
