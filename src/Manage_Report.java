import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Manage_Report {
    private static final String ORDER_FILE_PATH = "Order.txt";

    public static void main(String[] args) {
        try {
            double BrakesTotal = 0.0;
            double ExhaustTotal = 0.0;
            double SuspensionTotal = 0.0;
            double TotalAmount = 0.0;

            File file = new File(ORDER_FILE_PATH);
            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] parts = line.split(",");

                if (parts.length >= 6) {
                    String productAttributes = parts[3];
                    String quantity = parts[4];
                    double pricePerUnit = Double.parseDouble(parts[5]);

                    double amount = Double.parseDouble(quantity) * pricePerUnit;
                    TotalAmount += amount;

                    switch (productAttributes.toUpperCase()) {
                        case "BRAKES":
                            BrakesTotal += amount;
                            break;
                        case "EXHAUST":
                            ExhaustTotal += amount;
                            break;
                        case "SUSPENSION":
                            SuspensionTotal += amount;
                            break;
                        default:
                            System.out.println("Invalid product attribute: " + productAttributes);
                            break;
                    }
                } else {
                    System.out.println("Invalid input line: " + line);
                }
            }
            input.close();

            System.out.println("Total Amount for Brakes: " + BrakesTotal);
            System.out.println("Total Amount for Exhaust: " + ExhaustTotal);
            System.out.println("Total Amount for Suspension: " + SuspensionTotal);
            System.out.println("Total Amount for all products: " + TotalAmount);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to load Transaction from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing item quantity or price: " + e.getMessage());
        }
        
        System.out.println("Exiting Application......");
        System.exit(0);
    }
}
