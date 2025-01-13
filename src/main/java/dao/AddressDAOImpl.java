package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Address;

public class AddressDAOImpl implements AddressDAO {
    
    private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    @Override
    public boolean addAddress(Address address) {
        String query = "INSERT INTO Address(user_id, country_region, full_name, pincode, mobile, flat_details, area_details, landmark, town_city, state, address_type, additional_instructions) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, address.getUserId());
            ps.setString(2, address.getCountryRegion());
            ps.setString(3, address.getFullName());
            ps.setString(4, address.getPincode());
            ps.setString(5, address.getMobile());
            ps.setString(6, address.getFlatDetails());
            ps.setString(7, address.getAreaDetails());
            ps.setString(8, address.getLandmark());
            ps.setString(9, address.getTownCity());
            ps.setString(10, address.getState());
            ps.setString(11, address.getAddressType());
            ps.setString(12, address.getAdditionalInstructions());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Address getAddressById(int addressId) {
        String query = "SELECT * FROM Address WHERE address_id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, addressId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return new Address(
                        rs.getInt("address_id"),
                        rs.getInt("user_id"),
                        rs.getString("country_region"),
                        rs.getString("full_name"),
                        rs.getString("pincode"),
                        rs.getString("mobile"),
                        rs.getString("flat_details"),
                        rs.getString("area_details"),
                        rs.getString("landmark"),
                        rs.getString("town_city"),
                        rs.getString("state"),
                        rs.getString("address_type"),
                        rs.getString("additional_instructions")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Address> getAddressesByUserId(int userId) {
        List<Address> addresses = new ArrayList<>();
        String query = "SELECT * FROM Address WHERE user_id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                addresses.add(new Address(
                        rs.getInt("address_id"),
                        rs.getInt("user_id"),
                        rs.getString("country_region"),
                        rs.getString("full_name"),
                        rs.getString("pincode"),
                        rs.getString("mobile"),
                        rs.getString("flat_details"),
                        rs.getString("area_details"),
                        rs.getString("landmark"),
                        rs.getString("town_city"),
                        rs.getString("state"),
                        rs.getString("address_type"),
                        rs.getString("additional_instructions")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }

    @Override
    public boolean updateAddress(Address address) {
        String query = "UPDATE Address SET country_region = ?, full_name = ?, pincode = ?, mobile = ?, flat_details = ?, area_details = ?, "
                + "landmark = ?, town_city = ?, state = ?, address_type = ?, additional_instructions = ? WHERE address_id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, address.getCountryRegion());
            ps.setString(2, address.getFullName());
            ps.setString(3, address.getPincode());
            ps.setString(4, address.getMobile());
            ps.setString(5, address.getFlatDetails());
            ps.setString(6, address.getAreaDetails());
            ps.setString(7, address.getLandmark());
            ps.setString(8, address.getTownCity());
            ps.setString(9, address.getState());
            ps.setString(10, address.getAddressType());
            ps.setString(11, address.getAdditionalInstructions());
            ps.setInt(12, address.getAddressId());
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAddress(int addressId) throws SQLException {
        String query = "DELETE FROM Address WHERE address_id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, addressId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw e;
        }
    }
}
