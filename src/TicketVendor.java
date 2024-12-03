public class TicketVendor implements Runnable{
    private final TicketPool ticketPool;
    private final int ticketsAvailable;
    private final int releaseRate;

    public TicketVendor(TicketPool ticketPool, int ticketsToGenerate, int releaseRate){
        this.ticketPool=ticketPool;
        this.ticketsAvailable=ticketsToGenerate;
    }

    @Override
    public void run(){
        try{
            for(int i=1, i<=ticketsAvailable; i++){
                String ticket = "Ticket-" +i;
                ticketPool.addTicket(ticket);
                Thread.sleep(1000/releaseRate);
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            System.err.println("Vendor interrupted.");
        }
    }
}
