import java.util.*;

public class TicketSystem {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        displayMenu();
    }

    private static void displayMenu(){
        while (true){
            System.out.println("-------------------------------------------------------");
            System.out.println("--- Welcome To The Real-Time Event Ticketing System ---");
            System.out.println("-------------------------------------------------------");
            System.out.println("1. Configure the system");
            System.out.println("2. Start the system");
            System.out.println("3. Stop the system");
            System.out.println("4. Display the logs");
            System.out.println("5. Load the previous configuration");
            System.out.println("6. Save the current configuration");
            System.out.println("5. Exit the system");
            System.out.println("Enter your choice between 1-7 : ");

            String choice = scanner.nextLine();
            switch (choice){
                case "1":
                    configureTicketSystem();
                    break;
                case "2":
                    startTicketSystem();
                    break;
                case "3":
                    stopTicketSystem();
                    break;
                case "4":
                    displayLogs();
                    break;
                case "5":
                    loadTicketConfiguration();
                    break;
                case "6":
                    saveTicketConfiguration();
                    break;
                case "7":
                    System.out.println("Exiting the system. See you again soon!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
