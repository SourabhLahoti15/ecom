package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAOImpl;

@WebServlet("/deleteProduct")
public class DeleteProductServelt extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String strpid = request.getParameter("productId");

        if (strpid != null && !strpid.isEmpty()) {
            int pid = Integer.parseInt(strpid);
            ProductDAOImpl productDAO = new ProductDAOImpl();
            boolean isDeleted = productDAO.deleteProduct(pid);

            if (isDeleted) {
                response.setContentType("application/json");
                response.getWriter().write("{\"success\": true, \"message\": \"Product deleted successfully.\"}");
            
            } else {
                response.setContentType("application/json");
                response.getWriter().write("{\"success\": false, \"message\": \"Failed to delete product.\"}");
            }
        } else {
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": false, \"message\": \"Invalid product.\"}");
        }
    }
}
