import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Manage_Staff {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        ArrayList<Staff> StaffList = new ArrayList<>();
            try {
                File file = new File("Staff.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
                Scanner input = new Scanner(file);

                while (input.hasNextLine()) {
                    String line = input.nextLine();
                    String[] parts = line.split(",");
                    String Name = parts[1].trim();
                    String Email = parts[2].trim();
                    String Department = parts[3].trim();
                    String Password;
                    if (parts.length >= 4) {
                        Password = parts[4];
                    } else {
                        System.out.println("Invalid format in line: " + line);
                        continue; 
                    }

                    Staff newStaff = new Staff(Name, Email, Department, Password);
                    StaffList.add(newStaff);
                }

                input.close();
            } catch (FileNotFoundException e) {
                System.out.println("Unable to load Staff from file: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error creating new file: " + e.getMessage());
            }
        
        while(choice !=5)
        {
            System.out.println("------------------");
            System.out.println("Manage Staff");
            System.out.println("------------------");
            System.out.println("1. Add Staff");
            System.out.println("2. Delete Staff");
            System.out.println("3. Modify Staff");
            System.out.println("4. Display All Staff");
            System.out.println("5. Exit");
            System.out.print("Enter your option: ");
            choice = scanner.nextInt();
            
            switch(choice)
            {
                
                case 1:
                    System.out.println("--------------------");
                    System.out.println("Register Staff:");
                    System.out.println("--------------------");
                    System.out.println(" ");

                    System.out.print("Staff Name: ");
                    scanner.nextLine();
                    String Name = scanner.nextLine();

                    System.out.print("Staff Email: ");
                    String Email = scanner.nextLine();
                    
                    System.out.println("Choose Department:");
                    System.out.println("1. Admin");
                    System.out.println("2. Staff");
                    System.out.print("Enter your choice (1 or 2): ");
                    int DepartmentChoice = scanner.nextInt();
                    String Department;
                    switch (DepartmentChoice) {
                        case 1:
                            Department = "Admin";
                            break;
                        case 2:
                            Department = "Staff";
                            break;
                        default:
                            System.out.println("Invalid department choice. Defaulting to 'Staff'.");
                            Department = "Staff";
                            break;
                    }
                    
                    System.out.print("Staff Password: ");
                    String Password = scanner.next();

                    Staff newStaff = new Staff(Name, Email, Department, Password);
                    StaffList.add(newStaff);

                    saveStaffToFile(StaffList);

                    System.out.println("\nStaff added successfully. Assigned ID: " + newStaff.getStaffId());
                    break;


                case 2: 
                    System.out.println("-----------------------------");
                    System.out.println("Delete existing Staff:");
                    System.out.println("-----------------------------");
                    System.out.println(" ");
                    System.out.print("Staff ID: ");
                    int deleteStaffId = scanner.nextInt();
                    boolean found = false;
                    for (int i = 0; i < StaffList.size(); i++) {
                        if (StaffList.get(i).getStaffId() == deleteStaffId) {
                            StaffList.remove(i);
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        System.out.println("\nStaff deleted successfully.");
                    } else {
                        System.out.println("\nStaff not found.");
                    }
                    saveStaffToFile(StaffList);
                    break;
            
                case 3:
                    System.out.println("--------------------------------");
                    System.out.println("Modify Staff information:");
                    System.out.println("--------------------------------");
                    System.out.println(" ");
                    System.out.print("Staff Name: ");
                    scanner.nextLine();
                    String modifyStaffName = scanner.nextLine();
                    found = false;
                    System.out.println("\nInput new information");
                    for (int i = 0; i < StaffList.size(); i++) {
                        if (StaffList.get(i).getName().equals(modifyStaffName)) {
                            System.out.print("Staff Email: ");
                            String newStaffEmail = scanner.next();
                            StaffList.get(i).setEmail(newStaffEmail);

                            System.out.println("Choose Staff Department:");
                            System.out.println("1. Admin");
                            System.out.println("2. Staff");
                            System.out.print("Enter your choice: ");
                            int departmentChoice = scanner.nextInt();
                            String newDepartment;

                            switch (departmentChoice) {
                                case 1:
                                    newDepartment = "Admin";
                                    break;
                                case 2:
                                    newDepartment = "Staff";
                                    break;
                                default:
                                    newDepartment = "Unknown";
                                    System.out.println("Invalid choice. Setting department to 'Unknown'.");
                                    break;
                            }
                            StaffList.get(i).setDepartment(newDepartment);

                            System.out.print("Staff Password: ");
                            String newStaffPassword = scanner.next();
                            StaffList.get(i).setPassword(newStaffPassword);
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        System.out.println("\nStaff modified successfully.");
                    } else {
                        System.out.println("\nStaff not found.");
                    }
                    saveStaffToFile(StaffList);
                    break;
                                
                case 4:
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("Display all Staff:");
                    System.out.println("------------------------------------------------------------------");
                    System.out.println(String.format("%-20s%-5s%-20s%-5s%-20s%-5s%-20s", "Staff ID", "|", "Staff Name", "|", "Staff Email", "|", "Staff Department", "|", "Staff Password"));
                    System.out.println("------------------------------------------------------------------");
                    for (Staff staff : StaffList) {
                        System.out.println(String.format("%-20s%-5s%-20s%-5s%-20s%-5s%-20s", staff.getStaffId(), "|", staff.getName(), "|", staff.getEmail(), "|", staff.getDepartment(), "|", staff.getPassword()));
                    }
                    break;

                case 5:
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
                    System.out.println("\nExit Menu.");
                    System.exit(0);
                    break;
                    
                default:
                    System.out.println("\nInvalid input\n");
            }

        }
        scanner.close();

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
}
