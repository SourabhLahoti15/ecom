package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.ProductDAOImpl;
import model.Product;

@WebServlet("/addProduct")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1MB
    maxFileSize = 1024 * 1024 * 5,   // 5MB
    maxRequestSize = 1024 * 1024 * 10 // 10MB
)
public class AddProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": false, \"message\": \"You are not logged in. Please log in to add a product.\", \"type\": \"error\"}");
            return; 
        }

        try {
            Part filePart = request.getPart("productImage");
            String fileName = getFileName(filePart);
            
            String uploadPath = getServletContext().getRealPath("") + File.separator + "images";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath);

            String name = request.getParameter("product_name");
            String description = request.getParameter("product_description");
            double price = Double.parseDouble(request.getParameter("product_price"));
            int stock = Integer.parseInt(request.getParameter("product_stock"));
            int uid = (int) session.getAttribute("user_id");
            
            String dbImagePath = "images/" + fileName;
            Product product = new Product(dbImagePath, name, description, price, stock, uid);
            ProductDAOImpl productDAO = new ProductDAOImpl();
            
            boolean isAdded = productDAO.addProduct(product);
            if(isAdded) {
                response.setContentType("application/json");
                response.getWriter().write("{\"success\": true, \"message\": \"Product added successfully.\", \"type\": \"success\"}");
            } else {
                response.setContentType("application/json");
                response.getWriter().write("{\"success\": false, \"message\": \"Failed to add product.\", \"type\": \"error\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": false, \"message\": \"An error occurred while adding product.\", \"type\": \"error\"}");
        }
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }
}