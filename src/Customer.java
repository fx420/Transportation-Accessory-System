public class Customer extends User{

    protected String CustomerOrder;
    protected int CustomerID;
    protected String CustomerDOB;

    public Customer(String Name, String Email, String CustomerDOB, String Password) {
        super(Name, Email, Password);
        this.CustomerDOB = CustomerDOB;
        this.CustomerID = generateCustomerID();
    }

    public String getCustomerDOB(){
        return CustomerDOB;
    }

    public void setCustomerDOB(String CustomerDOB) {
        this.CustomerDOB = CustomerDOB;
    }

    public String getCustomerOrder() {
        return CustomerOrder;
    }

    public void setCustomerOrder(String CustomerOrder) {
        this.CustomerOrder = CustomerOrder;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    private static int nextCustomerID = 1;

    private int generateCustomerID() {
        return nextCustomerID++;
    }
}
