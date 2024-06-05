public class Report {
    protected String ProductName;
    protected String ProductQuantity;
    protected String ProductPrice;
    protected String TotalSales;

    public Report() {
        this.ProductName = null;
        this.ProductQuantity = null;
        this.ProductPrice = null;
        this.TotalSales = null;
    }

    public Report(String ProductName, String ProductQuantity, String ProductPrice, String TotalSales) {
        this.ProductName = ProductName;
        this.ProductQuantity = ProductQuantity;
        this.ProductPrice = ProductPrice;
        this.TotalSales = TotalSales;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductQuantity(String ProductQuantity) {
        this.ProductQuantity = ProductQuantity;
    }

    public String getProductQuantityy() {
        return ProductQuantity;
    }

    public void setProductPrice(String ProductPrice) {
        this.ProductPrice = ProductPrice;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setTotalSales(String TotalSales) {
        this.TotalSales = TotalSales;
    }

    public String getTotalSales() {
        return TotalSales;
    }
}
