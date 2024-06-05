import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Manage_Inventory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        ArrayList<Inventory> ProductList = new ArrayList<>();

        try {
                File file = new File("Inventory.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
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
            System.out.println("Unable to load Product from file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error creating new file: " + e.getMessage());
        }

            while (choice != 6) {
                System.out.println("----------------------");
                System.out.println("Manage Inventory");
                System.out.println("----------------------");
                System.out.println("1. Add Product");
                System.out.println("2. Delete Product");
                System.out.println("3. Modify Product");
                System.out.println("4. Search Product");
                System.out.println("5. Display All Product");
                System.out.println("6. Exit");
                System.out.print("Enter your option: ");

                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("--------------------");
                        System.out.println("Add a new Product:");
                        System.out.println("--------------------");

                        System.out.println("Select Product Attributes:");
                        System.out.println("1. Brakes");
                        System.out.println("2. Exhaust");
                        System.out.println("3. Suspension");
                        System.out.print("Enter your choice: ");
                        int attributeChoice = scanner.nextInt();

                        String ProductName;
                        String ProductAttributes;
                        String Attributes1 = null;
                        String Attributes2 = null;

                        switch (attributeChoice) {
                            case 1:
                                ProductAttributes = "Brakes";
                                System.out.print("Caliper Pistons: ");
                                scanner.nextLine();
                                Attributes1 = scanner.nextLine();
                                System.out.print("Pad Materials: ");
                                Attributes2 = scanner.nextLine();
                                break;

                            case 2:
                                ProductAttributes = "Exhaust";
                                System.out.print("Configuration Section: ");
                                scanner.nextLine();
                                Attributes1 = scanner.nextLine();
                                System.out.print("Material: ");
                                Attributes2 = scanner.nextLine();
                                break;

                            case 3:
                                ProductAttributes = "Suspension";
                                System.out.print("Adjustability: ");
                                scanner.nextLine();
                                Attributes1 = scanner.nextLine();
                                System.out.print("Spring Rate: ");
                                Attributes2 = scanner.nextLine();
                                break;

                            default:
                                System.out.println("Invalid choice.");
                                return;
                        }

                        System.out.print("Product Name: ");
                        ProductName = scanner.nextLine();
                        System.out.print("Product Quantity: ");
                        int ProductQuantity = scanner.nextInt();
                        System.out.print("Product Price: ");
                        double ProductPrice = scanner.nextDouble();

                        Inventory newProduct = new Inventory(ProductName, ProductAttributes, Attributes1, Attributes2, ProductQuantity, ProductPrice);
                        ProductList.add(newProduct);

                        saveInventoryToFile(ProductList);
                        System.out.println("\nProduct added successfully.");
                        break;

                    case 2:
                        System.out.println("----------------------------");
                        System.out.println("Delete an existing Product:");
                        System.out.println("----------------------------");
                        System.out.print("\nProduct Name: ");
                        scanner.nextLine();
                        String deleteProductName = scanner.nextLine();
                        boolean found = false;
                        for (int i = 0; i < ProductList.size(); i++) {
                            if (ProductList.get(i).getProductName().equals(deleteProductName)) {
                                ProductList.remove(i);
                                System.out.println("\nProduct deleted successfully.");
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("\nProduct not found.");
                        }
                        saveInventoryToFile(ProductList);
                        break;

                    case 3:
                        System.out.println("---------------------------");
                        System.out.println("Modify Product information:");
                        System.out.println("---------------------------");
                    
                        System.out.print("\nProduct Name: ");
                        scanner.nextLine();
                        String modifyProductName = scanner.nextLine();
                    
                        boolean found1 = false;
                        for (int i = 0; i < ProductList.size(); i++) {
                            if (ProductList.get(i).getProductName().equals(modifyProductName)) {
                                found1 = true;
                    
                                System.out.println("\nCurrent Product Information:");
                                System.out.println("Product Name: " + ProductList.get(i).getProductName());
                                System.out.println("Product Attributes: " + ProductList.get(i).getProductAttributes());
                    
                                System.out.println("\nSelect New Product Attributes:");
                                System.out.println("1. Brakes");
                                System.out.println("2. Exhaust");
                                System.out.println("3. Suspension");
                                System.out.print("Enter your choice: ");
                                int AttributeChoice = scanner.nextInt();
                    
                                String newProductAttributes;
                                String newAttributes1;
                                String newAttributes2;
                    
                                switch (AttributeChoice) {
                                    case 1:
                                        newProductAttributes = "Brakes";
                                        System.out.print("Caliper Pistons: ");
                                        scanner.nextLine();
                                        newAttributes1 = scanner.nextLine();
                                        System.out.print("Pad Materials: ");
                                        newAttributes2 = scanner.nextLine();
                                        break;

                                    case 2:
                                        newProductAttributes = "Exhaust";
                                        System.out.print("Configuration Section: ");
                                        scanner.nextLine();
                                        newAttributes1 = scanner.nextLine();
                                        System.out.print("Material: ");
                                        newAttributes2 = scanner.nextLine();
                                        break;

                                    case 3:
                                        newProductAttributes = "Suspension";
                                        System.out.print("Adjustability: ");
                                        scanner.nextLine();
                                        newAttributes1 = scanner.nextLine();
                                        System.out.print("Spring Rate: ");
                                        newAttributes2 = scanner.nextLine();
                                        break;

                                    default:
                                        System.out.println("Invalid choice.");
                                        return;
                                }
                    
                                ProductList.get(i).setProductName(modifyProductName);
                                ProductList.get(i).setProductAttributes(newProductAttributes);
                                ProductList.get(i).setAttributes1(newAttributes1);
                                ProductList.get(i).setAttributes2(newAttributes2);
                    
                                System.out.print("Product Quantity: ");
                                int newProductQuantity = scanner.nextInt();
                                ProductList.get(i).setProductQuantity(newProductQuantity);
                    
                                System.out.print("Product Price: ");
                                double newProductPrice = scanner.nextDouble();
                                ProductList.get(i).setProductPrice(newProductPrice);
                    
                                System.out.println("\nProduct information modified successfully.");
                                saveInventoryToFile(ProductList);
                                break;
                            }
                        }
                    
                        if (!found1) {
                            System.out.println("\nProduct not found.");
                        }
                        break;

                    case 4:
                        System.out.println("-----------------------");
                        System.out.println("Search for a Product:");
                        System.out.println("-----------------------");

                        System.out.println("Select search criteria:");
                        System.out.println("1. Product Name");
                        System.out.println("2. Product Attributes");
                        System.out.print("Enter your choice: ");
                        int searchChoice = scanner.nextInt();

                        switch (searchChoice) {
                        case 1:
                            System.out.print("\nEnter Product Name: ");
                            scanner.nextLine();
                            String searchProductName = scanner.nextLine();
                            boolean foundByName = false;
                            for (Inventory product : ProductList) {
                                if (product.getProductName().equalsIgnoreCase(searchProductName)) {
                                    displayProduct(product);
                                    foundByName = true;
                                    break;
                                }
                            }
                            if (!foundByName) {
                                System.out.println("\nProduct not found.");
                            }
                            break;

                        case 2:
                            System.out.println("Select Product Attributes:");
                            System.out.println("1. Brakes");
                            System.out.println("2. Exhaust");
                            System.out.println("3. Suspension");
                            System.out.print("Enter your choice: ");
                            int Choice = scanner.nextInt();

                            String searchProductAttributes = null;
                            switch (Choice) {
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
                                System.out.println("------------------------------------------------------------------");
                                System.out.println("Products with Attributes: " + searchProductAttributes);
                                System.out.println("------------------------------------------------------------------");
                                System.out.println(String.format("%-20s%-3s%-20s%-3s%-20s%-3s%-20s%-3s%-20s%-3s%-20s", "Product Name", "|  ", "Product Attributes Name", "|  ", "Attributes1 Name", "|  ","Attributes2 Name", "|  ","Product Quantity", "|  ", "Product Price"));
                                System.out.println("------------------------------------------------------------------");
                                boolean foundInAttributes = false;
                                for (Inventory product : ProductList) {
                                    if (product.getProductAttributes().equals(searchProductAttributes)) {
                                        System.out.println(String.format("%-20s%-3s%-25s%-3s%-20s%-3s%-20s%-3s%-20s%-3s%-20s", product.getProductName(), "|  ", product.getProductAttributes(), "|  ", product.getAttributes1(), "|  ", product.getAttributes2(), "|  ", product.getProductQuantity(), "|  ", product.getProductPrice()));
                                        foundInAttributes = true;
                                    }
                                }
                                if (!foundInAttributes) {
                                    System.out.println("No products found with the selected attributes.");
                                }
                            }
                            break;

                        default:
                            System.out.println("Invalid choice.");
                            break;
                        }
                        break;
                    
                    case 5:
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("Display Products:");
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("Display products by Product Attributes");
                        System.out.println("Select Product Attributes:");
                        System.out.println("1. Brakes");
                        System.out.println("2. Exhaust");
                        System.out.println("3. Suspension");
                        System.out.print("Enter your choice: ");
                        int AttributeChoice = scanner.nextInt();
                    
                        String searchProductAttributes = null;
                        switch (AttributeChoice) {
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
                                return;
                        }
                    
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("Products with Attributes: " + searchProductAttributes);
                        System.out.println("------------------------------------------------------------------");
                        System.out.println(String.format("%-20s%-3s%-20s%-3s%-20s%-3s%-20s%-3s%-20s%-3s%-20s", "Product Name", "|  ", "Product Attributes Name", "|  ","Attributes1 Name", "|  ","Attributes2 Name", "|  ","Product Quantity", "|  ", "Product Price"));
                        System.out.println("------------------------------------------------------------------");
                        boolean foundInAttributes = false;
                        for (Inventory product : ProductList) {
                            if (product.getProductAttributes().equals(searchProductAttributes)) {
                                System.out.println(String.format("%-20s%-3s%-20s%-3s%-20s%-3s%-20s%-3s%-20s%-3s%-20s", product.getProductName(), "|  ", product.getProductAttributes(), "|  ", product.getAttributes1(), "|  ", product.getAttributes2(), "|  ", product.getProductQuantity(), "|  ", product.getProductPrice()));
                                foundInAttributes = true;
                            }
                        }
                        if (!foundInAttributes) {
                            System.out.println("No products found with the selected attributes.");
                        }
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                    
                    case 6:
                        try {
                            FileWriter writer = new FileWriter("Inventory.txt");
                            for (Inventory product : ProductList) {
                                writer.write(product.getProductName() + "," + product.getProductAttributes() + "," + product.getAttributes1() + "," + product.getAttributes2() + "," + product.getProductQuantity()+ "," + product.getProductPrice() + "\n");
                            }
                            writer.close();
                            System.out.println("\nInventory data saved to file.");
                        } catch (IOException e) {
                            System.out.println("Unable to save Inventory data to file: " + e.getMessage());
                        }
                        System.out.println("\nExit Menu.");
                        System.exit(0);
                        return;
                }
            }
        scanner.close();
        
    }

    public static void displayProduct(Inventory product) {
        System.out.println("Product Name: " + product.getProductName());
        System.out.println("Product Attributes: " + product.getProductAttributes());
        System.out.println("Attributes1: " + product.getAttributes1());
        System.out.println("Attributes2: " + product.getAttributes2());
        System.out.println("Product Quantity: " + product.getProductQuantity());
        System.out.println("Product Price: " + product.getProductPrice());
    }

    private static void saveInventoryToFile(ArrayList<Inventory> ProductList) {
        try {
            FileWriter writer = new FileWriter("Inventory.txt");
            for (Inventory product : ProductList) {
                writer.write(product.getProductName() + "," + product.getProductAttributes() + "," + product.getAttributes1() + "," + product.getAttributes2() + "," + product.getProductQuantity()+ "," + product.getProductPrice() + "\n");
            }
            writer.close();
            System.out.println("\nInventory data saved to file.");
        } catch (IOException e) {
            System.out.println("Unable to save Inventory data to file: " + e.getMessage());
        }
    }
}
