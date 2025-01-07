import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.AddressDAOImpl;
import model.Address;

public class AddressDAOImplTest {

    private AddressDAOImpl addressDAO;

    @BeforeEach
    void setUp() {
        addressDAO = new AddressDAOImpl();
    }

    @Test
    void testAddAddress() {
        Address address = new Address(
                0,
                1,
                "India",
                "Raj",
                "123456",
                "9876543210",
                "Flat 101",
                "Area 51",
                "Landmark X",
                "City Y",
                "State Z",
                "Home",
                "Leave at door");

        boolean result = addressDAO.addAddress(address);
        assertTrue(result, "Address should be added successfully");
    }

    @Test
    void testGetAddressById() {
        Address address = new Address(
                0,
                1, 
                "India", "Raj", "123456", "9876543210",
                "Flat 101", "Area 51", "Landmark X",
                "City Y", "State Z", "Home", "Leave at door");
        addressDAO.addAddress(address);

        Address retrievedAddress = addressDAO.getAddressById(1);
        assertNotNull(retrievedAddress, "Address should not be null");
        assertEquals("Raj", retrievedAddress.getFullName(), "Names should match");
    }

    @Test
    void testGetAddressesByUserId() {
        Address address1 = new Address(
                0, 1, "India", "Raj", "123456", "9876543210",
                "Flat 101", "Area 51", "Landmark X",
                "City Y", "State Z", "Home", "Leave at door");
        Address address2 = new Address(
                0, 1, "India", "Ram", "654321", "1234567890",
                "Flat 202", "Area 52", "Landmark P",
                "City Y", "State Q", "Work", "Ring the bell");
        addressDAO.addAddress(address1);
        addressDAO.addAddress(address2);

        List<Address> addresses = addressDAO.getAddressesByUserId(1);
        assertEquals(2, addresses.size(), "Should return 2 addresses for user_id 1");
    }

    @Test
    void testUpdateAddress() {
        Address address = new Address(
                0, 1, "India", "Raj", "123456", "9876543210",
                "Flat 101", "Area 51", "Landmark X",
                "City Y", "State Z", "Home", "Leave at door");
        addressDAO.addAddress(address);

        Address updatedAddress = new Address(
                1, 1, "India", "Raj Updated", "654321", "1234567890",
                "Flat 102", "Area 52", "Landmark Y",
                "City Z", "State W", "Work", "Updated instructions");
        boolean result = addressDAO.updateAddress(updatedAddress);
        assertTrue(result, "Address should be updated successfully");

        Address retrievedAddress = addressDAO.getAddressById(1);
        assertEquals("Raj Updated", retrievedAddress.getFullName(), "Name should be updated");
    }

    @Test
    void testDeleteAddress() {
        Address address = new Address(
                0, 1, "India", "Raj", "123456", "9876543210",
                "Flat 101", "Area 51", "Landmark X",
                "City Y", "State Z", "Home", "Leave at door");
        addressDAO.addAddress(address);

        boolean result = addressDAO.deleteAddress(1);
        assertTrue(result, "Address should be deleted successfully");

        Address retrievedAddress = addressDAO.getAddressById(1);
        assertNull(retrievedAddress, "Address should no longer exist");
    }
}
