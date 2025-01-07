import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.ProductDAOImpl;
import model.Product;

public class ProductDAOImplTest {

    private ProductDAOImpl productDAO;

    @BeforeEach
    void setUp() {
        productDAO = new ProductDAOImpl();
    }

    @Test
    void testAddProduct() {
        Product product = new Product(0, "path/to/image", "Product A", "Description A", 100.0, 50, 1);  // Assuming product_id is auto-generated
        assertTrue(productDAO.addProduct(product));
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product(0, "path/to/image", "Product B", "Description B", 150.0, 30, 1);  // Assuming product_id is auto-generated
        productDAO.addProduct(product);
        product.setProductPrice(160.0);
        product.setProductStock(40);
        assertTrue(productDAO.updateProduct(product));
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product(0, "path/to/image", "Product C", "Description C", 200.0, 20, 1);  // Assuming product_id is auto-generated
        productDAO.addProduct(product);
        assertTrue(productDAO.deleteProduct(product.getProductId()));
    }

    @Test
    void testGetAllProducts() {
        Product product = new Product(0, "path/to/image", "Product D", "Description D", 300.0, 10, 1);  // Assuming product_id is auto-generated
        productDAO.addProduct(product);
        List<Product> products = productDAO.getAllProducts();
        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    @Test
    void testGetProductById() {
        Product product = new Product(0, "path/to/image", "Product E", "Description E", 250.0, 15, 1);  // Assuming product_id is auto-generated
        productDAO.addProduct(product);
        Product fetchedProduct = productDAO.getProductById(product.getProductId());
        assertNotNull(fetchedProduct);
        assertEquals(product.getProductId(), fetchedProduct.getProductId());
    }

    @Test
    void testGetProductByUserId() {
        Product product = new Product(0, "path/to/image", "Product F", "Description F", 400.0, 25, 1);  // Assuming product_id is auto-generated
        productDAO.addProduct(product);
        List<Product> products = productDAO.getProductByUserId(1);
        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    @Test
    void testSearchProductsByName() {
        Product product = new Product(0, "path/to/image", "Product G", "Description G", 500.0, 5, 1);  // Assuming product_id is auto-generated
        productDAO.addProduct(product);
        List<Product> products = productDAO.searchProductsByName("Product G");
        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals("Product G", products.get(0).getProductName());
    }
}
