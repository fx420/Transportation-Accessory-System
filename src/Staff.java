public class Staff extends User{

    protected String Department;
    protected int StaffId;

    public Staff(String Name, String Email, String Department, String Password) {
        super(Name, Email, Password);
        this.Department = Department;
        this.StaffId = nextStaffId++;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public int getStaffId() {
        return StaffId;
    }

    private static int nextStaffId = 1;

}
