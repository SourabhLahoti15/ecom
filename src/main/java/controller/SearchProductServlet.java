package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAOImpl;
import model.Product;

@WebServlet("/searchProduct")
public class SearchProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ProductDAOImpl productDAO = new ProductDAOImpl();
        String searchedProduct = request.getParameter("searchedProduct");
        List<Product> searchResults = productDAO.searchProductsByName(searchedProduct);
        request.setAttribute("searchResults", searchResults);

        request.getRequestDispatcher("searchResults.jsp").forward(request, response);
    }
}