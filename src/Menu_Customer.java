import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Menu_Customer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        ArrayList<Customer> CustomerList = new ArrayList<>();
        ArrayList<Inventory> ProductList = new ArrayList<>();

        loadCustomersFromFile(CustomerList);
        loadProductsFromFile(ProductList);

        while (choice != 3) {
            displayMainMenu();

            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        registerCustomer(scanner, CustomerList);
                        break;
                    case 2:
                        loginCustomer(scanner, CustomerList, ProductList);
                        break;
                    case 3:
                        saveCustomerToFile(CustomerList);
                        saveProductsToFile(ProductList);
                        System.out.println("\nExiting...");
                        break;
                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            } catch (NoSuchElementException e) {
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("----------------------");
        System.out.println("Welcome to Customer Inventory Management System");
        System.out.println("----------------------");
        System.out.println("1. Register Customer");
        System.out.println("2. Login Customer");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void registerCustomer(Scanner scanner, ArrayList<Customer> customerList) {
        System.out.println("--------------------");
        System.out.println("Register Customer:");
        System.out.println("--------------------");
        System.out.println(" ");

        System.out.print("Customer Name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Customer Email: ");
        String email = scanner.next();
        System.out.print("Customer Date of Birth(DOB): ");
        String dob = scanner.next();
        System.out.print("Customer Password: ");
        String password = scanner.next();

        Customer newCustomer = new Customer(name, email, dob, password);
        customerList.add(newCustomer);
        System.out.println("\nCustomer added successfully. Assigned ID: " + newCustomer.getCustomerID());
    }

    private static void loginCustomer(Scanner scanner, ArrayList<Customer> CustomerList, ArrayList<Inventory> ProductList) {
        System.out.println("--------------------");
        System.out.println("Customer Login:");
        System.out.println("--------------------");
    
        try {
            System.out.print("Customer Name: ");
            scanner.nextLine();
            String NameInput = scanner.nextLine().trim();
            System.out.print("Customer Password: ");
            String PasswordInput = scanner.next().trim();
    
            Customer loggedInCustomer = null;
    
            for (Customer customer : CustomerList) {
                if (customer.getName().equals(NameInput) && customer.getPassword().equals(PasswordInput)) {
                    loggedInCustomer = customer;
                    System.out.println("Login successful. Welcome, " + customer.getName() + "!");
                    break;
                }
            }
    
            if (loggedInCustomer != null) {
                LoginCustomer(loggedInCustomer, ProductList);
            } else {
                System.out.println("Login failed. Invalid name or password.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Invalid input. Please enter valid data.");
        }
    }
    
    static void LoginCustomer(Customer customer, ArrayList<Inventory> ProductList) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            try {
                System.out.println("\nWelcome " + customer.getName() + "\nChoose option"); 
                System.out.println("1. Manage Profile");
                System.out.println("2. Product Inventory");
                System.out.println("3. View Order");
                System.out.println("4. Exit application");
                System.out.print("\nEnter your choice: ");
                int choose = scanner.nextInt();
    
                switch (choose) {
                    case 1:
                        manageProfile(scanner, customer);
                        break;
    
                    case 2:
                        showProductSystemMenu(scanner, ProductList, customer);
                        break;
    
                    case 3:
                        String LoggedInCustomerName = customer.getName();
                        ArrayList<Order> CustomerOrder = new ArrayList<>();
    
                        try {
                            File file = new File("Order.txt");
                            Scanner input = new Scanner(file);
    
                            while (input.hasNextLine()) {
                                String line = input.nextLine();
                                String[] parts = line.split(",");
                                if (parts.length >= 7) {
                                    String Name = parts[1].trim();
                                    if (Name.equals(LoggedInCustomerName)) {
                                        int OrderId = Integer.parseInt(parts[0].trim());
                                        String ProductName = parts[2].trim();
                                        String ProductAttributes = parts[3].trim();
                                        int ProductQuantity = Integer.parseInt(parts[4].trim());
                                        double ProductPrice = Double.parseDouble(parts[5].trim());
                                        String OrderDate = parts[6].trim();
    
                                        Order order = new Order(OrderId, Name, ProductName, ProductAttributes, ProductQuantity, ProductPrice, OrderDate);
                                        CustomerOrder.add(order);
                                    }
                                } else {
                                    System.out.println("Invalid input line in Order file: " + line);
                                }
                            }
                            input.close();
                        } catch (FileNotFoundException e) {
                            System.out.println("Unable to load Order from file: " + e.getMessage());
                        }
    
                        if (!CustomerOrder.isEmpty()) {
                            System.out.println("------------------------------------------------------------------");
                            System.out.println("Order for " + LoggedInCustomerName + ":");
                            System.out.println("------------------------------------------------------------------");
                            for (Order order : CustomerOrder) {
                                System.out.println(order);
                            }
                        } else {
                            System.out.println("No Order found for " + LoggedInCustomerName);
                        }
                        break;
    
                    case 4:
                        System.out.println("Exiting Application...");
                        System.exit(0);
                        break;
    
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (NoSuchElementException e) {
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        scanner.close();
    }

    private static void manageProfile(Scanner scanner, Customer customer) {
        System.out.println("--------------------");
        System.out.println("Modify Profile:");
        System.out.println("--------------------");

        System.out.println("Modifying Profile for: " + customer.getName());
        System.out.print("New Email: ");
        String newCustomerEmail = scanner.next();

        System.out.print("New Date of Birth(DOB)(yyyy-MM-dd): ");
        String newCustomerDOB = scanner.next();

        System.out.print("New Password: ");
        String newCustomerPassword = scanner.next();

        customer.setEmail(newCustomerEmail);
        customer.setCustomerDOB(newCustomerDOB);
        customer.setPassword(newCustomerPassword);

        System.out.println("\nProfile modified successfully.");
    }

    private static void showProductSystemMenu(Scanner scanner, ArrayList<Inventory> ProductList, Customer customer) {
        int choice = 0;
        while (choice != 4) {
            System.out.println("----------------------");
            System.out.println("Product Inventory");
            System.out.println("----------------------");
            System.out.println("1. Display Product");
            System.out.println("2. Buy Product");
            System.out.println("3. Exit");
            System.out.print("Enter your option: ");
    
            choice = scanner.nextInt();
    
            switch (choice) {
                case 1:
                    displayAllProducts(ProductList, scanner);
                    break;
    
                case 2:
                    System.out.println("--------------------");
                    System.out.println("Buy Product:");
                    System.out.println("--------------------");
                    BuyProduct(scanner, ProductList, customer);
                    break;
    
                case 3:
                    System.out.println("\nExiting Product Inventory...");
                    return; 
    
                default:
                    System.out.println("\nInvalid choice.");
                    break;
            }
        }
    }
    
    static void BuyProduct(Scanner scanner, ArrayList<Inventory> ProductList, Customer customer) {
        System.out.print("Enter Product Name: ");
        scanner.nextLine();
        String ProductName = scanner.nextLine();
    
        Inventory selectedProduct = null;
        for (Inventory product : ProductList) {
            if (product.getProductName().equals(ProductName)) {
                selectedProduct = product;
                break;
            }
        }
    
        if (selectedProduct == null) {
            System.out.println("Product not found.");
            return;
        }
    
        System.out.print("Enter Quantity to buy: ");
        int QuantityToBuy = scanner.nextInt();
    
        int availableQuantity = selectedProduct.getProductQuantity();
        if (availableQuantity < QuantityToBuy) {
            System.out.println("Not enough quantity available.");
            return;
        }
    
        int remainingQuantity = availableQuantity - QuantityToBuy;
        selectedProduct.setProductQuantity(remainingQuantity);
    
        int OrderId = generateOrderId();
    
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String OrderDate = now.format(formatter);
    
        saveOrderToFile(OrderId, customer.getName(), ProductName, selectedProduct.getProductAttributes(), QuantityToBuy, selectedProduct.getProductPrice(), OrderDate);
    
        System.out.println("Purchase successful.");
    }
    
    static int generateOrderId() {
        int maxOrderId = 0;
        try {
            File file = new File("Order.txt");
            Scanner input = new Scanner(file);
    
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 1) {
                    int orderId = Integer.parseInt(parts[0].trim());
                    maxOrderId = Math.max(maxOrderId, orderId);
                } else {
                    System.out.println("Invalid input line in Order file: " + line);
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to load Order from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing order ID: " + e.getMessage());
        }
    
        return maxOrderId + 1;
    }

    private static void displayAllProducts(ArrayList<Inventory> ProductList, Scanner scanner) {
        System.out.println("------------------------------------------------------------------");
        System.out.println("Display Products:");
        System.out.println("------------------------------------------------------------------");
        System.out.println("Product Attributes");
        System.out.println("Select Product Attributes:");
        System.out.println("1. Brakes");
        System.out.println("2. Exhaust");
        System.out.println("3. Suspension");
        System.out.print("Enter your choice: ");
        int attributeChoice = scanner.nextInt();
        scanner.nextLine();
        String searchProductAttributes = null;
        switch (attributeChoice) {
            case 1:
                searchProductAttributes = "Brakes";
                break;
            case 2:
                searchProductAttributes = "Exhaust";
                break;
            case 3:
                searchProductAttributes = "Suspension";
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
        if (searchProductAttributes != null) {
            displayProductsByAttributes(ProductList, searchProductAttributes);
        }
    }
    
    private static void displayProductsByAttributes(ArrayList<Inventory> productList, String searchProductAttributes) {
        System.out.println("------------------------------------------------------------------");
        System.out.println("Products with Attributes: " + searchProductAttributes);
        System.out.println("------------------------------------------------------------------");
        System.out.println(String.format("%-20s%-3s%-20s%-3s%-20s%-3s%-20s%-3s%-20s%-3s%-20s", "Product Name", "|  ", "Product Attributes Name", "|  ","Attributes1 Name", "|  ","Attributes2 Name", "|  ","Product Quantity", "|  ", "Product Price"));
        System.out.println("------------------------------------------------------------------");
        boolean foundInAttributes = false;
        for (Inventory product : productList) {
            if (product.getProductAttributes().equalsIgnoreCase(searchProductAttributes)) {
                System.out.println(String.format("%-20s%-3s%-20s%-3s%-20s%-3s%-20s%-3s%-20s%-3s%-20s", product.getProductName(), "|  ", product.getProductAttributes(), "|  ", product.getAttributes1(), "|  ", product.getAttributes2(), "|  ", product.getProductQuantity(), "|  ", product.getProductPrice()));
                foundInAttributes = true;
            }
        }
        if (!foundInAttributes) {
            System.out.println("No products found with the selected attributes.");
        }
    }
    

    private static void loadCustomersFromFile(ArrayList<Customer> CustomerList) {
        try {
            File file = new File("Customers.txt");
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String Name = parts[1];
                    String Email = parts[2];
                    String CustomerDOB = parts[3];
                    String Password = parts[4];

                    Customer newCustomer = new Customer(Name, Email, CustomerDOB, Password);
                    CustomerList.add(newCustomer);
                } else {
                    System.out.println("Invalid input line: " + line);
                }
            }

            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to load Customer from file: " + e.getMessage());
        }
    }

    private static void loadProductsFromFile(ArrayList<Inventory> ProductList) {
        try {
            File file = new File("Inventory.txt");
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    String ProductName = parts[0].trim();
                    String ProductAttributes = parts[1].trim();
                    String Attributes1 = parts[2].trim();
                    String Attributes2 = parts[3].trim();
                    int ProductQuantity = Integer.parseInt(parts[4].trim());
                    double ProductPrice = Double.parseDouble(parts[5].trim());

                    Inventory newProduct = new Inventory(ProductName, ProductAttributes, Attributes1, Attributes2, ProductQuantity, ProductPrice);
                    ProductList.add(newProduct);
                } else {
                    System.out.println("Invalid format in line: " + line);
                }
            }

            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to load Item from file: " + e.getMessage());
        }
    }

    private static void saveProductsToFile(ArrayList<Inventory> ProductList) {
        try (FileWriter writer = new FileWriter("Inventory.txt")) {
            for (Inventory product : ProductList) {
                writer.write(product.getProductName() + "," + product.getProductAttributes() + "," + product.getAttributes1() + "," + product.getAttributes2() + "," + product.getProductQuantity()+ "," + product.getProductPrice() + "\n");
            }
            System.out.println("\nInventory data saved to file.");
        } catch (IOException e) {
            System.out.println("Unable to save Inventory data to file: " + e.getMessage());
        }
    }

    private static void saveCustomerToFile(ArrayList<Customer> CustomerList) {
        try (FileWriter writer = new FileWriter("Customers.txt")) {
            for (Customer customer : CustomerList) {
                writer.write(customer.getCustomerID() + "," + customer.getName() + "," + customer.getEmail() + "," + customer.getCustomerDOB() + "," + customer.getPassword() + "\n");
            }
            System.out.println("\nCustomer data saved to file.");
        } catch (IOException e) {
            System.out.println("Unable to save Customer data to file: " + e.getMessage());
        }
    }

    private static void saveOrderToFile(int OrderId, String CustomerName, String ProductName, String ProductAttributes, int ProductQuantity, double ProductPrice, String OrderDate) {
        try (FileWriter writer = new FileWriter("Order.txt", true)) {
            writer.write(OrderId + "," + CustomerName + "," + ProductName + "," + ProductAttributes + "," + ProductQuantity + "," + ProductPrice + "," + OrderDate + "\n");
            System.out.println("Order data saved to file.");
        } catch (IOException e) {
            System.out.println("Unable to save Order data to file: " + e.getMessage());
        }
    }
}
