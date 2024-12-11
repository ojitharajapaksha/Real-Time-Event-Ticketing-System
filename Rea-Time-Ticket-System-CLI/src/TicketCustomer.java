// This Customer class responsible for purchasing tickets from the pool
public class TicketCustomer implements Runnable{
    private final TicketPool ticketPool;
    private final int retrievalRate;

    public TicketCustomer(TicketPool ticketPool, int retrievalRate){
        this.ticketPool=ticketPool;
        this.retrievalRate=retrievalRate;
    }

    @Override
    public void run(){
        try{
            while(!Thread.currentThread().isInterrupted()){
                // Try to remove a ticket
                String ticket=ticketPool.removeTicket();
                if (ticket!=null){
                    // Log purchase
                    System.out.println("Customer Purchased: " + ticket);
                }
                // Sleep according to the retrieval rate
                Thread.sleep(1000/retrievalRate);
            }
        }catch (InterruptedException e){
            // Restore the interrupted status
            Thread.currentThread().interrupt();
            System.err.println("Customer interrupted.");
        }
    }
}
