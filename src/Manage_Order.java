import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Manage_Order {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Order> OrderList = new ArrayList<>();
        int choice = 0; 

        try {
            File file = new File("Order.txt");
            if (!file.exists()) {
                file.createNewFile(); 
            }
            Scanner input = new Scanner(file);
        
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] parts = line.split(",");
                
                if (parts.length >= 7) {
                    int OrderId = Integer.parseInt(parts[0].trim());
                    String CustomerName = parts[1];
                    String ProductName = parts[2];
                    String ProductAttributes = parts[3];
                    int ProductQuantity = Integer.parseInt(parts[4].trim());
                    double ProductPrice = Double.parseDouble(parts[5]);
                    String OrderDate = parts[6];
        
                    Order neworder = new Order(OrderId, CustomerName, ProductName, ProductAttributes, ProductQuantity, ProductPrice, OrderDate);
                    OrderList.add(neworder);
                } else {
                    System.out.println("Invalid input line: " + line);
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Order file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading Order file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing Item Quantity: " + e.getMessage());
        }

        while (choice != 5) {
            System.out.println("----------------------");
            System.out.println("Manage Order");
            System.out.println("----------------------");
            System.out.println("1. Display All Order");
            System.out.println("2. Modify Order");
            System.out.println("3. Add Order");
            System.out.println("4. Delete Order");
            System.out.println("5. Exit");
            System.out.print("Enter your option: ");

            choice = scanner.nextInt();
            switch (choice) {

                case 1:
                    System.out.println("------------------------------------------------------------------");
                    System.out.println("Display all Order:");
                    System.out.println("------------------------------------------------------------------");
                    for (Order order : OrderList) {
                        System.out.println(order.toString());
                    }
                    break;

                case 2:
                    System.out.println("---------------------------");
                    System.out.println("Modify Order information:");
                    System.out.println("---------------------------");
                    System.out.print("\nCustomer Name: ");
                    scanner.nextLine();
                    String modifyCustomerName = scanner.nextLine();

                    System.out.println("\nInput new information");
                    boolean found = false;
                    for (Order order : OrderList) {
                        if (order.getName().equals(modifyCustomerName)) {
                            System.out.print("Product Name: ");
                            scanner.nextLine();
                            String newProductName = scanner.nextLine();
                            order.setProductName(newProductName);
                
                            System.out.println("Choose Product Attributes:");
                            System.out.println("1. Brakes");
                            System.out.println("2. Exhaust");
                            System.out.println("3. Suspension");
                            System.out.print("Enter your choice: ");
                            int attributesChoice = scanner.nextInt();
                            String newProductAttributes;
                
                            switch (attributesChoice) {
                                case 1:
                                    newProductAttributes = "Brakes";
                                    break;
                                case 2:
                                    newProductAttributes = "Exhaust";
                                    break;
                                case 3:
                                    newProductAttributes = "Suspension";
                                    break;
                                default:
                                    newProductAttributes = "Unknown";
                                    System.out.println("Invalid choice. Setting product attributes to 'Unknown'.");
                                    break;
                            }
                            order.setProductAttributes(newProductAttributes);
                
                            System.out.print("Product Quantity: ");
                            int newProductQuantity = scanner.nextInt();
                            order.setProductQuantity(newProductQuantity);
                
                            System.out.print("Product Price: ");
                            double newProductPrice = scanner.nextDouble();
                            order.setProductPrice(newProductPrice);
                
                            System.out.print("Date Sold (yyyy-MM-dd): ");
                            String newDateSoldStr = scanner.next();
                            LocalDate newDateSold = parseDate(newDateSoldStr);
                            order.setDateSold(newDateSold);
                
                            System.out.println("\nOrder information modified successfully.");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("\nOrder not found.");
                    }
                    saveOrderToFile(OrderList);
                    break;

                    case 3:
                    System.out.println("----------------------");
                    System.out.println("Add a new Order:");
                    System.out.println("----------------------");
                
                    System.out.print("Customer Name: ");
                    scanner.nextLine();
                    String customerName = scanner.nextLine();
                
                    System.out.print("Product Name: ");
                    String productName = scanner.nextLine();
                
                    System.out.println("Choose Product Attributes:");
                    System.out.println("1. Brakes");
                    System.out.println("2. Exhaust");
                    System.out.println("3. Suspension");
                    System.out.print("Enter your choice: ");
                    int attributesChoice = scanner.nextInt();
                    String productAttributes;
                
                    switch (attributesChoice) {
                        case 1:
                            productAttributes = "Brakes";
                            break;
                        case 2:
                            productAttributes = "Exhaust";
                            break;
                        case 3:
                            productAttributes = "Suspension";
                            break;
                        default:
                            productAttributes = "Unknown";
                            System.out.println("Invalid choice. Setting product attributes to 'Unknown'.");
                            break;
                    }
                
                    System.out.print("Product Quantity: ");
                    int productQuantity = scanner.nextInt();
                    System.out.print("Product Price: ");
                    double productPrice = scanner.nextDouble();
                    System.out.print("Order Date(yyyy-MM-dd): ");
                    String orderDate = scanner.next();
                
                    int newOrderId = generateOrderId();
                    Order newOrder = new Order(newOrderId, customerName, productName, productAttributes, productQuantity, productPrice, orderDate);
                
                    OrderList.add(newOrder);
                    System.out.println("\nOrder added successfully. Assigned ID: " + newOrder.getOrderId());
                
                    saveOrderToFile(OrderList);
                    System.out.println("Order added successfully.");
                    break;

                case 4:
                    System.out.println("----------------------------");
                    System.out.println("Delete an existing Order:");
                    System.out.println("----------------------------");
                    System.out.print("\nOrder No: ");
                    int deleteOrderId = scanner.nextInt();
                    boolean found1 = false;
                    for (int i = 0; i < OrderList.size(); i++) {
                        if (OrderList.get(i).getOrderId() == deleteOrderId) {
                            OrderList.remove(i);
                            System.out.println("\nOrder deleted successfully.");
                            found1 = true;
                            break;
                        }
                    }
                    if (!found1) {
                        System.out.println("\nOrder not found.");
                    }
                    saveOrderToFile(OrderList);
                    break;

                case 5:
                    try {
                        FileWriter writer = new FileWriter("Order.txt");
                        for (Order order : OrderList) {
                            writer.write(order.getOrderId() + "," + order.getName() + "," + order.getProductName() + "," + order.getProductAttributes() + "," + order.getProductQuantity() + "," + order.getProductPrice() + "," + order.getOrderDate() + "\n");
                        }
                        writer.close();
                        System.out.println("\nOrder data saved to file.");
                    } catch (IOException e) {
                        System.out.println("Unable to save Order data to file: " + e.getMessage());
                    }
                    System.out.println("\nExiting Menu...");
                    System.exit(0);
                
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
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

    private static LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateStr, formatter);
    }

    private static void saveOrderToFile(ArrayList<Order> OrderList) {
        try {
            FileWriter OrderWriter = new FileWriter("Order.txt");
            FileWriter InventoryWriter = new FileWriter("Inventory.txt");
    
            for (Order order : OrderList) {
                OrderWriter.write(order.getOrderId() + "," + order.getName() + "," + order.getProductName() + "," + order.getProductAttributes() + "," + order.getProductQuantity() + "," + order.getProductPrice() + "," + order.getOrderDate() + "\n");
    
                String ProductName = order.getProductName();
                String ProductAttributes = order.getProductAttributes();
                int ProductQuantity = order.getProductQuantity();
                double ProductPrice = order.getProductPrice();
    
                InventoryWriter.write(ProductName + "," + ProductAttributes + "," + ProductQuantity + "," + ProductPrice + "\n");
            }
    
            OrderWriter.close();
            InventoryWriter.close();
            System.out.println("\nOrder data saved to file.");
        } catch (IOException e) {
            System.out.println("Unable to save Order data to file: " + e.getMessage());
        }
    }
    
}



