public class Inventory {

    protected String ProductName;
    protected String ProductAttributes;
    protected String Attributes1;
    protected String Attributes2;
    protected int ProductQuantity;
    protected double ProductPrice;
    
    public Inventory(){
        ProductName = null;
        ProductAttributes = null;
        Attributes1 = null;
        Attributes2 = null;
        ProductQuantity = 0;
        ProductPrice = 0.0;
    }
    
    public Inventory(String ProductName, String ProductAttributes, String Attributes1, String Attributes2, int ProductQuantity, double ProductPrice) {
        this.ProductName = ProductName;
        this.ProductAttributes = ProductAttributes;
        this.Attributes1 = Attributes1;
        this.Attributes2 = Attributes2;
        this.ProductQuantity = ProductQuantity;
        this.ProductPrice = ProductPrice;
    }
    
    public String getProductName() {
        return ProductName;
    }
    
    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }
 
    public void setAttributes1(String Attributes1) {
        this.Attributes1 = Attributes1;
    }

    public String getAttributes1() {
        return Attributes1;
    }

    public void setAttributes2(String Attributes2) {
        this.Attributes2 = Attributes2;
    }

    public String getAttributes2() {
        return Attributes2;
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

    public String getProductAttributes() {
        return ProductAttributes;
    }

    public void setProductAttributes(String ProductAttributes) {
        if ("Brakes".equals(ProductAttributes) || "Exhaust".equals(ProductAttributes) || "Suspension".equals(ProductAttributes)) {
            this.ProductAttributes = ProductAttributes;
        } else {
            throw new IllegalArgumentException("Invalid ItemAttributes value: " + ProductAttributes);
        }
    }

    @Override
    public String toString() {
        return String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s", getProductName(), getProductAttributes(), getAttributes1(), getAttributes2(), getProductQuantity(), getProductPrice());
    }

    public int getProductQuantityAsInt() {
        return getProductQuantity();
    }

    public double getProductPriceAsDouble() {
        return getProductPrice();
    }
}
