import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Order {
    protected int OrderId;
    protected String Name;
    protected String ProductName;
    protected String ProductAttributes;
    protected int ProductQuantity;
    protected double ProductPrice;
    protected LocalDate OrderDate;

    private static int nextOrderId = 1;

    public int generateOrderId() {
        return nextOrderId++;
    }

    public Order(int OrderId, String Name, String ProductName, String ProductAttributes, int ProductQuantity, double ProductPrice, String OrderDate) {
        this.OrderId = OrderId;
        this.Name = Name;
        this.ProductName = ProductName;
        this.ProductAttributes = ProductAttributes;
        this.ProductQuantity = ProductQuantity;
        this.ProductPrice = ProductPrice;
        this.OrderDate = parseOrderDate(OrderDate);
    }

    private LocalDate parseOrderDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateStr, formatter);
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getProductAttributes() {
        return ProductAttributes;
    }

    public void setProductAttributes(String ProductAttributes) {
        this.ProductAttributes = ProductAttributes;
    }

    public int getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(int ProductQuantity) {
        this.ProductQuantity = ProductQuantity;
    }

    public double getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(double ProductPrice) {
        this.ProductPrice = ProductPrice;
    }

    public LocalDate getOrderDate() {
        return OrderDate;
    }

    public void setDateSold(LocalDate dateSold) {
        this.OrderDate = dateSold;
    }

    public int getOrderId() {
        return OrderId;
    }

    @Override
    public String toString() {
        return "Order ID: " + OrderId + ", Customer: " + Name + ", Product: " + ProductName + ", Product Attributes: " + ProductAttributes + ", Quantity: " + ProductQuantity + ", Price: " + ProductPrice + ", Order Date: " + OrderDate;
    }
}
