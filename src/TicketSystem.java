import java.io.IOException;
import java.util.*;

public class TicketSystem {
    private static TicketConfiguration config = new TicketConfiguration();
    private static boolean systemRunning = false;

    private static final TicketPool ticketPool = new TicketPool();
    private static final List<String> log = Collections.synchronizedList(new ArrayList<>());
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
            System.out.println("7. Exit the system");
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

    private static void configureTicketSystem(){
        int totalTickets = getPositiveInput("Enter the total number of tickets: ");
        int ticketReleaseRate = getPositiveInput("Enter the ticket release rate per second: ");
        int customerRetrievalRate = getPositiveInput("Enter the customer retrieval rate per second: ");
        int maxTicketCapacity;
        while (true){
            maxTicketCapacity = getPositiveInput("Enter the maximum ticket capacity: ");
            if (maxTicketCapacity >= totalTickets){
                break;
            }else {
                System.out.println("Maximum ticket capacity cannot be less than total number of tickets. ");
            }
        }

        config = new TicketConfiguration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);

        System.out.println("Configuration saved successfully!");
    }

    private static void saveTicketConfiguration(){
        try{
            config.saveToFile("config.txt");
            System.out.println("Configuration saved to config.txt");
        }catch (IOException e){
            System.err.println("Failed to save configuration: " + e.getMessage());
        }
    }

    private static void loadTicketConfiguration(){
        try{
            config = TicketConfiguration.loadFromFile("config.txt");
            System.out.println("Configuration loaded: " + config);
        }catch (IOException e){
            System.err.println("Failed to load configuration: " + e.getMessage());
        }
    }

    private static int getPositiveInput(String get){
        int value;
        while (true){
            System.out.println(get);
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value > 0) return value;
                System.out.println("Invalid input. Please enter a positive integer.");
            }catch (NumberFormatException e){
                System.out.println("Invalid input. Please enter a positive integer.");
            }
        }
    }

    private static void startTicketSystem(){
        if (systemRunning){
            System.out.println("System is already running.");
            return;
        }
        systemRunning = true;
        logEvent("System started");

        Thread vendor = new Thread(new TicketVendor(ticketPool, config.getTotalTickets(), config.getTicketReleaseRate()));
        Thread customer = new Thread(new TicketCustomer(ticketPool, config.getCustomerRetrievalRate()));

        vendor.start();
        customer.start();
    }

    private static void stopTicketSystem(){
        systemRunning = false;
        logEvent("System stopped");
        System.out.println("System stopped.");
    }

    private static void displayLogs(){
        System.out.println("--------------------- System Log ----------------------");
        log.forEach(System.out::println);
    }

    private static void logEvent(String message){
        log.add(new Date() + ": " + message);
    }
}
