package entity;

/**
 * Domain model representing a Product in the catalog.
 * <p>
 * Fields map directly to the database columns in the {@code product} table.
 * This class is a simple POJO used for transferring data between the
 * persistence layer (DAOs) and the web layer (servlets/JSPs).
 */

public class Product {

    private int productId;
    private String productName;
    private String productDescription;
    private String productColor;
    private String productSize;
    private double productPrice;

    /**
     * No-args constructor required by some frameworks and for convenience
     * when building an instance via setters.
     */
    public Product() {}

    /**
     * Full constructor including the generated {@code productId}.
     */
    public Product(int productId, String productName, String productDescription,
                   String productColor, String productSize, double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productColor = productColor;
        this.productSize = productSize;
        this.productPrice = productPrice;
    }

    /**
     * Constructor used before the product has been persisted (no id yet).
     */
    public Product(String productName, String productDescription,
                   String productColor, String productSize, double productPrice) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productColor = productColor;
        this.productSize = productSize;
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductColor() {
        return productColor;
    }
    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductSize() {
        return productSize;
    }
    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public double getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
