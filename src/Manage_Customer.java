import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Manage_Customer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice =0;

        ArrayList<Customer> CustomerList = new ArrayList<Customer>();
            try {
                File file = new File("Customers.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
                Scanner input = new Scanner(file);
    
                while (input.hasNextLine()) {
                    String line = input.nextLine();
                    System.out.println("Line: " + line);
                    String[] parts = line.split(",");
                    if (parts.length >= 5) {
                        String Name = parts[1].trim();
                        String Email = parts[2].trim();
                        String CustomerDOB = parts[3].trim();
                        String Password = parts[4].trim();
                        
                        Customer customer = new Customer(Name, Email, CustomerDOB, Password);
                        CustomerList.add(customer);
                    } else {
                        System.out.println("Invalid input format: " + line);
                        continue;
                    }
                }
    
                input.close();
            } catch (FileNotFoundException e) {
                System.out.println("Unable to load customers from file: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error creating new file: " + e.getMessage());
            }
       
    
        ArrayList<Order> OrderList = new ArrayList<Order>();
            try {
                File file = new File("Order.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
                Scanner input = new Scanner(file);
    
                while (input.hasNextLine()) {
                    String line = input.nextLine();
                    String[] parts = line.split(",");
                    int OrderId = Integer.parseInt(parts[0].trim());
                    String Name = parts[1];
                    String ProductName = parts[2];
                    String ProductAttributes = parts[3];
                    int ProductQuantity = Integer.parseInt(parts[4].trim());
                    double ProductPrice = Double.parseDouble(parts[5]);
                    String OrderDate = parts[6];
                
                    Order newOrder = new Order(OrderId, Name, ProductName, ProductAttributes, ProductQuantity, ProductPrice, OrderDate);
                    OrderList.add(newOrder);
                }
    
                input.close();
            } catch (FileNotFoundException e) {
                System.out.println("Unable to load Order from file: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Error creating new file: " + e.getMessage());
            }
    
        while(choice !=6)
        {
            System.out.println("------------------");
            System.out.println("Manage Customer");
            System.out.println("------------------");
            System.out.println("1. Add Customer");
            System.out.println("2. Delete Customer");
            System.out.println("3. Modify Customer");
            System.out.println("4. Search Customer & Purchase History");
            System.out.println("5. Display All Customer");
            System.out.println("6. Exit");
            System.out.print("Enter your option: ");
            choice = scanner.nextInt();
            
            switch(choice)
            {
                case 1: 
                    System.out.println("--------------------");
                    System.out.println("Add a new customer:");
                    System.out.println("--------------------");
                    System.out.println(" ");
                    System.out.print("Name: ");
                    scanner.nextLine();
                    String Name = scanner.nextLine();
                    System.out.print("Customer Email: ");
                    String Email = scanner.next();
                    System.out.print("Customer Date of Birth(DOB)(yyyy-MM-dd): ");
                    String CustomerDOB = scanner.next();
                    System.out.print("Customer Password: ");
                    String Password = scanner.next();
                    Customer customer = new Customer(Name, Email, CustomerDOB, Password);
                    CustomerList.add(customer);
                    saveCustomerToFile(CustomerList);
                    System.out.println("\nCustomer added successfully.");
                    break;
                
                    
                case 2: 
                    System.out.println("-----------------------------");
                    System.out.println("Delete existing customer:");
                    System.out.println("-----------------------------");
                    System.out.println(" ");
                    System.out.print("Name: ");
                    String DeleteName = scanner.next();
                    boolean found = false;
                    for (int i = 0; i < CustomerList.size(); i++) {
                        if (CustomerList.get(i).getName().equals(DeleteName)) {
                            CustomerList.remove(i);
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        System.out.println("\nCustomer deleted successfully.");
                    } else {
                        System.out.println("\nCustomer not found.");
                    }
                    break;
                
                    
                case 3:
                    System.out.println("--------------------------------");
                    System.out.println("Modify customer information:");
                    System.out.println("--------------------------------");
                    System.out.println(" ");
                    System.out.print("Enter Customer name: ");
                    scanner.nextLine();
                    String ModifyName = scanner.nextLine();
                    found = false;
                    System.out.println("\nInput new information:");
                    for (Customer Customer : CustomerList) {
                        if (Customer.getName().equals(ModifyName)) {

                            System.out.print("Customer Email: ");
                            String newEmail = scanner.next();
                            Customer.setEmail(newEmail);

                            System.out.print("Customer Date of Birth (DOB)(yyyy-MM-dd): ");
                            String newCustomerDOB = scanner.next();
                            Customer.setCustomerDOB(newCustomerDOB);

                            System.out.print("Customer Password: ");
                            String newPassword = scanner.next();
                            Customer.setPassword(newPassword);
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        System.out.println("\nCustomer modified successfully.");
                    } else {
                        System.out.println("\nCustomer not found.");
                    }
                    break;
    
                case 4:
                    System.out.println("------------------------------------");
                    System.out.println("Search Customer & Purchase History:");
                    System.out.println("------------------------------------");
                    System.out.println(" ");
                    System.out.print("Customer Name: ");
                    scanner.nextLine();
                    String searchName = scanner.nextLine();
                    found = false;
                    for (int i = 0; i < CustomerList.size(); i++) {
                        if (CustomerList.get(i).getName().equals(searchName)) {
                            System.out.println("\nPurchase History:\n");;
                            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println(String.format("%-20s%-3s%-20s%-3s%-20s%-3s%-20s%-3s%-20s",
                                    "Product Name", "|  ", "Product ProductAttributes", "|  ", "Product Quality", "|  ", "Product Price", "|  ", "Date Purchased"));
                            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
                            Boolean noPurchase = true;
                            for (Order order : OrderList) {
                                if (order.getName().equals(searchName)) {
                                    noPurchase = false;
                                    System.out.println(String.format("%-20s%-3s%-25s%-3s%-20s%-3s%-20s%-3s%-20s",
                                        order.getProductName(), "|  ", order.getProductAttributes(), "|  ", order.getProductQuantity(), "|  ", order.getProductPrice(), "|  ", order.getOrderDate(), "|"));
                                }
                            }
                            if (noPurchase) {
                                System.out.println("No Purchase History\n");
                            }
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("\nCustomer not found.");
                    }
                    break;
    
                case 5:
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("Display all Customer:");
                    System.out.println("------------------------------------------------------------------");
                    System.out.println(String.format("%-20s%-5s%-20s%-5s%-20s%-5s%-20s%-5s%-20s", "Customer ID", "|", "Customer Name", "|", "Customer Email" , "|", "Customer Date Of Birth" , "|", "Customer Password"));
                    System.out.println("------------------------------------------------------------------");
                    for (Customer Customer : CustomerList) {
                        System.out.println(String.format("%-20s%-5s%-20s%-5s%-20s%-5s%-20s%-5s%-20s", Customer.getCustomerID(), "|", Customer.getName(), "|", Customer.getEmail(), "|", Customer.getCustomerDOB(), "|", Customer.getPassword()));
                    }
                    break;
    
                case 6: 
                    try {
                        FileWriter writer = new FileWriter("Customers.txt");
                        for (Customer c : CustomerList) {
                            writer.write(c.getCustomerID() + "," + c.getName() + "," + c.getEmail() + "," + c.getCustomerDOB() + ","+c.getPassword()+"\n");
                        }
                        writer.close();
                        System.out.println("\nCustomer data saved to file.");
                    } catch (IOException e) {
                        System.out.println("Unable to save customer data to file: " + e.getMessage());
                    }
                    System.out.println("\nExit Menu.");
                    System.exit(0);
                    
                default:
                    System.out.println("\nInvalid input\n");
    
            }
            }scanner.close();
    }

    private static void saveCustomerToFile(ArrayList<Customer> CustomerList) {
    try {
        FileWriter writer = new FileWriter("Customers.txt");
        for (Customer customer : CustomerList) {
            writer.write(customer.getCustomerID() + "," + customer.getName() + "," + customer.getEmail() + "," + customer.getCustomerDOB() + "," + customer.getPassword() + "\n");
        }
        writer.close();
        System.out.println("\nCustomer data saved to file.");
    } catch (IOException e) {
        System.out.println("Unable to save Customer data to file: " + e.getMessage());
    }
}
    
}
    
