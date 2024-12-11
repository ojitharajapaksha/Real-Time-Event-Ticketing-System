// Ticket Configuration Class this is holds the configuration settings for the ticketing system
import java.io.*;

public class TicketConfiguration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public TicketConfiguration(){}

    // Constructor to initialize all the ticket configuration settings
    public TicketConfiguration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity){
        this.totalTickets=totalTickets;
        this.ticketReleaseRate=ticketReleaseRate;
        this.customerRetrievalRate=customerRetrievalRate;
        this.maxTicketCapacity=maxTicketCapacity;
    }

    // Getter method for ticket configuration
    public int getTotalTickets(){
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    // Setter method for  ticket configuration
    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    // Saves the current configuration to a text file
    public void saveToFile(String filename) throws IOException{
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            writer.write("Total number of tickets: " + totalTickets + "\n");
            writer.write("Ticket release rate per second: " + ticketReleaseRate + "\n");
            writer.write("Customer retrieval rate per second: " + customerRetrievalRate + "\n");
            writer.write("Maximum ticket capacity: " + maxTicketCapacity + "\n");
        }
    }

    // Loads the configuration from file
    public static TicketConfiguration loadFromFile(String filename) throws IOException{
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            TicketConfiguration config = new TicketConfiguration();
            config.setTotalTickets(Integer.parseInt(reader.readLine().split(": ")[1]));
            config.setTicketReleaseRate(Integer.parseInt(reader.readLine().split(": ")[1]));
            config.setCustomerRetrievalRate(Integer.parseInt(reader.readLine().split(": ")[1]));
            config.setMaxTicketCapacity(Integer.parseInt(reader.readLine().split(": ")[1]));
            return config;
        }
    }

    @Override
    public String toString(){
        return "Configuration{" + "totalTickets" + totalTickets + ", ticketReleaseRate=" + ticketReleaseRate + ", customerRetrievalRate=" + customerRetrievalRate + ", maxTicketRetrievalRate=" + maxTicketCapacity + '}';
    }
}
