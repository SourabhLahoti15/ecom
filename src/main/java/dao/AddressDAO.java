package dao;

import java.util.List;

import model.Address;

public interface AddressDAO {
    // Create
    boolean addAddress(Address address);

    // Read
    Address getAddressById(int addressId);
    List<Address> getAddressesByUserId(int userId);

    // Update
    boolean updateAddress(Address address);

    // Delete
    boolean deleteAddress(int addressId);
}
