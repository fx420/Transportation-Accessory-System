public class Exhaust extends Inventory {

    private String ConfigurationSection;
    private String Material;

    public Exhaust(String ProductName, String ProductAttributes, String Attributes1, String Attributes2, int ProductQuantity, double ProductPrice) {
        super(ProductName, ProductAttributes, Attributes1, Attributes2, ProductQuantity, ProductPrice);
        this.ConfigurationSection = Attributes1;
        this.Material = Attributes2;
    }

    public String getConfigurationSection() {
        return ConfigurationSection;
    }

    public void setConfigurationSection(String ConfigurationSection) {
        this.ConfigurationSection = ConfigurationSection;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String Material) {
        this.Material = Material;
    }
}
