import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        try {
            System.out.println("Welcome to Transportation Accessory System!");
            System.out.println("Please select a feature to continue...");
            System.out.println("1. Customer");
            System.out.println("2. Staff");
            System.out.println("3. Exit application");
            System.out.print("\nEnter your choice: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                return;
            }

            switch (choice) {
                case 1:
                    Menu_Customer.main(args);
                    break;

                case 2:
                    Menu_Staff.main(null);
                    break;

                case 3:
                    System.out.println("Exiting Application...");
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}