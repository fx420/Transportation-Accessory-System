import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class Menu_Staff {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        ArrayList<Staff> StaffList = new ArrayList<>();
    
        try {
            File file = new File("Staff.txt");
            Scanner input = new Scanner(file);
    
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String Name = parts[1].trim();  
                    String Email = parts[2].trim(); 
                    String Department = parts[3].trim(); 
                    String Password = parts[4].trim(); 
    
                    Staff newStaff = new Staff(Name, Email, Department, Password);
                    StaffList.add(newStaff);
                } else {
                    System.out.println("Invalid format in line: " + line);
                }
            }
    
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to load Staff from file: " + e.getMessage());
        }
    
        while (true) {
            try {
                System.out.println("----------------------");
                System.out.println("Welcome to Staff Inventory Management System");
                System.out.println("----------------------");
                System.out.println("1.Register Staff");
                System.out.println("2.Login Staff");
                System.out.println("3.Exit");
        
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();
        
                    switch (choice) {
                        case 1:
                            registerStaff(scanner, StaffList);
                            break;
        
                        case 2:
                            loginStaff(scanner, StaffList);
                            break;
        
                        case 3:
                            saveStaffToFile(StaffList);
                            System.out.println("\nExit.");
                            break;
        
                        default:
                            System.out.println("Invalid Choice");
                            break;
                    }
        
                    if (choice == 3) {
                        break;
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                }
            } catch (NoSuchElementException e) {
                System.out.println("Error: " + e.getMessage());
                break;
            }
        }
        scanner.close();
    }

    private static void registerStaff(Scanner scanner, ArrayList<Staff> staffList) {
        System.out.println("--------------------");
        System.out.println("Register Staff:");
        System.out.println("--------------------");
        System.out.println(" ");
    
        System.out.print("Staff Name: ");
        String Name = scanner.nextLine();
        System.out.print("Staff Email: ");
        String Email = scanner.next();
        System.out.println("Department: ");
        System.out.println("1. Admin");
        System.out.println("2. Staff");
        System.out.print("Choose Department (1 or 2): ");
        int DepartmentChoice = scanner.nextInt();
        String Department;
        switch (DepartmentChoice) {
            case 1:
                Department = "admin";
                break;
            case 2:
                Department = "staff";
                break;
            default:
                System.out.println("Invalid department choice. Defaulting to 'staff'.");
                Department = "staff";
                break;
        }
        System.out.print("Staff Password: ");
        String Password = scanner.next();
    
        Staff newStaff = new Staff(Name, Email, Department, Password);
        staffList.add(newStaff);
        System.out.println("\nStaff added successfully. Assigned ID: " + newStaff.getStaffId());
    }

    private static void loginStaff(Scanner scanner, ArrayList<Staff> StaffList) {
        boolean loggedIn = false;
    
        while (!loggedIn) {
            System.out.println("--------------------");
            System.out.println("Staff Login:");
            System.out.println("--------------------");
    
            System.out.print("Staff Name: ");
            String NameInput = scanner.nextLine();
            System.out.print("Staff Password: ");
            String PasswordInput = scanner.next();
    
            for (Staff staff : StaffList) {
                if (staff.getName().equals(NameInput) && staff.getPassword().equals(PasswordInput)) {
                    loggedIn = true;
                    System.out.println("Login successful. Welcome, " + staff.getName() + "!");
                    LoginStaff(staff);
                    break;
                }
            }
    
            if (!loggedIn) {
                System.out.println("Login failed. Invalid email or password.");
                System.out.println("Press 1 to try again or any other key to return to the menu:");
                int tryAgainChoice = scanner.nextInt();
                if (tryAgainChoice != 1) {
                    break;
                }
            }
        }
    }

    private static void saveStaffToFile(ArrayList<Staff> StaffList) {
        try {
            FileWriter writer = new FileWriter("Staff.txt");
            for (Staff staff : StaffList) {
                writer.write(staff.getStaffId() + "," + staff.getName() + "," + staff.getEmail() + "," + staff.getDepartment() + "," + staff.getPassword() + "\n");
            }
            writer.close();
            System.out.println("\nStaff data saved to file.");
        } catch (IOException e) {
            System.out.println("Unable to save Staff data to file: " + e.getMessage());
        }
    }

    static void LoginStaff(Staff i) {
        Scanner in = new Scanner(System.in);
		try {
			System.out.println("\nWelcome " + i.getName() + "\nChoose option");
			System.out.println("1.Manage Staff");
			System.out.println("2.Manage Customer");
			System.out.println("3.Manage Inventory");
			System.out.println("4.Update Order");
			System.out.println("5.View Report");
			System.out.println("6.Exit application");
			System.out.print("\nEnter your choice: ");
			int choose = in.nextInt();
		
			switch (choose) {
				case 1:
					Manage_Staff.main(null);
					break;

				case 2:
					Manage_Customer.main(null);
					break;

				case 3:
					Manage_Inventory.main(null);
					break;

				case 4:
					Manage_Order.main(null);
					break;

				case 5:
					Manage_Report.main(null);
					break;

                case 6:
                    System.out.println("Exiting Application......");
                    System.exit(0);
                    break;
                    
				default:
					System.out.println("Invalid choice");
			}
		}finally {
			in.close();
		}
    }
}
