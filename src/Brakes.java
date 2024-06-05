public class Brakes extends Inventory {

    private int CaliperPistons;
    private String PadMaterials;

    public Brakes(String ProductName, String ProductAttributes, String CaliperPistons, String PadMaterials, int ProductQuantity, double ProductPrice) {
        super(ProductName, ProductAttributes, CaliperPistons, PadMaterials, ProductQuantity, ProductPrice);
        this.CaliperPistons = Integer.parseInt(CaliperPistons);
        this.PadMaterials = PadMaterials;
    }

    public int getCaliperPistons() {
        return CaliperPistons;
    }

    public void setCaliperPistons(int CaliperPistons) {
        this.CaliperPistons = CaliperPistons;
    }

    public String getPadMaterials() {
        return PadMaterials;
    }

    public void setPadMaterials(String PadMaterials) {
        this.PadMaterials = PadMaterials;
    }
}
