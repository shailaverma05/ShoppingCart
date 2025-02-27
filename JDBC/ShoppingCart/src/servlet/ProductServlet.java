package servlet;

import dao.ProductDAO;
import model.Item;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Item> products = ProductDAO.getAllProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
    }
}
