// Main class to run the Real-Time Ticket System
import java.io.IOException;
import java.util.*;

public class TicketSystem {
    // Maintain the configuration for the ticketing system
    private static TicketConfiguration ticketConfig = new TicketConfiguration();

    // Tracking the system is running
    private static boolean isSystemRunning = false;

    // Common resource for ticket management
    private static final TicketPool ticketPool = new TicketPool();

    // Thread safe list for storing the system logs
    private static final List<String> log = Collections.synchronizedList(new ArrayList<>());

    // Scanner for user inputs
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        // Displaying the main menu to the user
        displayMenu();
    }

    // Displays the main menu for user to choose choice
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
            System.out.println("7. Exit the system");
            System.out.print("Enter your choice between 1-7 : ");

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
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Configuring the ticket system with user inputs
    private static void configureTicketSystem(){
        int totalTickets = getPositiveInput("Enter the total number of tickets: ");
        int ticketReleaseRate = getPositiveInput("Enter the ticket release rate per second: ");
        int customerRetrievalRate = getPositiveInput("Enter the customer retrieval rate per second: ");
        int maxTicketCapacity;

        // Checking the maximum ticket capacity is greater than or equal to the total number of tickets
        while (true){
            maxTicketCapacity = getPositiveInput("Enter the maximum ticket capacity: ");
            if (maxTicketCapacity >= totalTickets){
                break;
            }else {
                System.out.println("Maximum ticket capacity cannot be less than total number of tickets. ");
            }
        }

        // Updating the system configuration
        ticketConfig = new TicketConfiguration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);

        System.out.println("Configuration saved successfully!");
    }

    // Saving the configuration to text file
    private static void saveTicketConfiguration(){
        try{
            ticketConfig.saveToFile("config.txt");
            System.out.println("Configuration saved to config.txt");
        }catch (IOException e){
            System.err.println("Failed to save configuration: " + e.getMessage());
        }
    }

    // Loading the configuration from saved text file
    private static void loadTicketConfiguration(){
        try{
            ticketConfig = TicketConfiguration.loadFromFile("config.txt");
            System.out.println("Configuration loaded: " + ticketConfig);
        }catch (IOException e){
            System.err.println("Failed to load configuration: " + e.getMessage());
        }
    }

    // Requesting positive integer from user
    private static int getPositiveInput(String get){
        int value;
        while (true){
            System.out.print(get);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value > 0) return value;
                System.out.println("Invalid input. Please enter a positive integer.");
            }catch (NumberFormatException e){
                System.out.println("Invalid input. Please enter a positive integer.");
            }
        }
    }

    // Starting the ticketing ssytem
    private static void startTicketSystem(){
        if (isSystemRunning){
            System.out.println("System is already running.");
            return;
        }
        isSystemRunning = true;
        logEvent("System started");

        // Threads created for vendor and  customer operations
        Thread vendor = new Thread(new TicketVendor(ticketPool, ticketConfig.getTotalTickets(), ticketConfig.getTicketReleaseRate()));
        Thread customer = new Thread(new TicketCustomer(ticketPool, ticketConfig.getCustomerRetrievalRate()));

        vendor.start();
        customer.start();
    }

    // Stops the ticketing system
    private static void stopTicketSystem(){
        isSystemRunning = false;
        logEvent("System stopped");
        System.out.println("System stopped.");
    }

    // Displaying the system logs
    private static void displayLogs(){
        System.out.println("--------------------- System Log ----------------------");
        log.forEach(System.out::println);
    }

    // Records the events in the log list
    private static void logEvent(String message){
        log.add(new Date() + ": " + message);
    }
}
