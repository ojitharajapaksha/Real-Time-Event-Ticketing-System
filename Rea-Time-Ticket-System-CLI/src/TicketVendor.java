// This Vendor class responsible for adding tickets to the pool
public class TicketVendor implements Runnable{
    private final TicketPool ticketPool;
    private final int ticketsAvailable;
    private final int releaseRate;

    public TicketVendor(TicketPool ticketPool, int ticketsToGenerate, int releaseRate){
        this.ticketPool=ticketPool;
        this.ticketsAvailable=ticketsToGenerate;
        this.releaseRate=releaseRate;
    }

    @Override
    public void run(){
        try{
            for (int i = 1; i <= ticketsAvailable; i++){
                // Generate a ticket identifier
                String ticket = "Ticket-" +i;
                // Adding ticket to the pool
                ticketPool.addTicket(ticket);
                // Sleep the system according to the release rate
                Thread.sleep(1000/releaseRate);
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            // Restore the interrupted status
            System.err.println("Vendor interrupted.");
        }
    }
}
