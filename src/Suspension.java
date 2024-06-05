public class Suspension extends Inventory {

    private boolean Adjustability;
    private double SpringRate;

    public Suspension(String ProductName, String ProductAttributes, boolean Adjustability, double SpringRate, int ProductQuantity, double ProductPrice) {
        super(ProductName, ProductAttributes, Boolean.toString(Adjustability), Double.toString(SpringRate), ProductQuantity, ProductPrice);
        this.Adjustability = Adjustability;
        this.SpringRate = SpringRate;
    }

    public boolean isAdjustable() {
        return Adjustability;
    }

    public void setAdjustability(boolean Adjustability) {
        this.Adjustability = Adjustability;
    }

    public double getSpringRate() {
        return SpringRate;
    }

    public void setSpringRate(double SpringRate) {
        this.SpringRate = SpringRate;
    }
}
