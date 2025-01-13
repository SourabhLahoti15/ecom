package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AddressDAOImpl;
import model.Address;

@WebServlet({ "/addAddress", "/updateAddress", "/getAddress", "/deleteAddress" })
public class AddressServelt extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        
        HttpSession session = request.getSession();

        AddressDAOImpl addressDAO = new AddressDAOImpl();

        int uid = (int) session.getAttribute("user_id");
        String action = request.getServletPath();
        
        if ("/addAddress".equals(action) || "/updateAddress".equals(action)) {
            String countryRegion = request.getParameter("countryRegion");
            String fullName = request.getParameter("fullName");
            String pincode = request.getParameter("pincode");
            String mobile = request.getParameter("mobile");
            String flatDetails = request.getParameter("flatDetails");
            String areaDetails = request.getParameter("areaDetails");
            String landmark = request.getParameter("landmark");
            String townCity = request.getParameter("townCity");
            String state = request.getParameter("state");
            String addressType = request.getParameter("addressType");
            String additionalInstructions = request.getParameter("additionalInstructions");
            
            if ("/addAddress".equals(action)){
                boolean isAdded;
                Address address = new Address(uid,countryRegion,fullName,pincode,mobile,flatDetails,areaDetails,landmark,townCity,state,addressType,additionalInstructions);
                isAdded = addressDAO.addAddress(address);
                if (isAdded) {
                    List<Address> addresses = addressDAO.getAddressesByUserId(uid);
                    session.setAttribute("addresses", addresses);
                    response.getWriter().write("{\"success\": true, \"message\": \"Address added successfully!\", \"type\": \"success\"}");
                }
                else{
                    response.getWriter().write("{\"success\": false, \"message\": \"Failed to add address!\", \"type\": \"error\"}");
                }
            } else if ("/updateAddress".equals(action)) {
                boolean isUpdated;
                String addressIdParam = request.getParameter("addressId");
                int addressId = Integer.parseInt(addressIdParam);

                Address address = new Address(addressId,uid,countryRegion,fullName,pincode,mobile,flatDetails,areaDetails,landmark,townCity,state,addressType,additionalInstructions);
                isUpdated = addressDAO.updateAddress(address);    
                if (isUpdated) {
                    List<Address> addresses = addressDAO.getAddressesByUserId(uid);
                    session.setAttribute("addresses", addresses);

                    response.getWriter().write("{\"success\": true, \"message\": \"Address updated successfully!\", \"type\": \"success\"}");
                } else {
                    response.getWriter().write("{\"success\": false, \"message\": \"Failed to update address!\", \"type\": \"error\"}");
                }
            }       
        } else if ("/deleteAddress".equals(action)) {
            try {
                String addressIdParam = request.getParameter("addressId");
                int addressId = Integer.parseInt(addressIdParam);
                boolean isDeleted = addressDAO.deleteAddress(addressId);
                if (isDeleted) {
                    List<Address> addresses = addressDAO.getAddressesByUserId(uid);
                    session.setAttribute("addresses", addresses);
                    response.getWriter().write("{\"success\": true, \"message\": \"Address deleted successfully!\", \"type\": \"success\"}");
                } else {
                    response.getWriter().write("{\"success\": false, \"message\": \"Failed to delete address!\", \"type\": \"error\"}");
                }                
            } catch (SQLException e) {
                if (e.getSQLState().equals("23000")) {
                    response.getWriter().write("{\"success\": false, \"message\": \"Cannot delete this address as there are orders to deliver on this address.\", \"type\": \"error\"}");
                } else {
                    response.getWriter().write("{\"success\": false, \"message\": \"An exception occurred while deleting the address.\", \"type\": \"error\"}");
                }
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        if ("/getAddress".equals(action)) {
            int addressId = Integer.parseInt(request.getParameter("addressId"));
            AddressDAOImpl addressDAO = new AddressDAOImpl();
            Address address = addressDAO.getAddressById(addressId);
            request.setAttribute("address", address);

            RequestDispatcher rd = request.getRequestDispatcher("updateAddress.jsp");
            rd.forward(request, response);
        }
    }
}
