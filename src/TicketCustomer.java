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
                String ticket=ticketPool.removeTicket();
                if (ticket!=null){
                    System.out.println("Customer purchased: " + ticket);
                }
                Thread.sleep(1000/retrievalRate);
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            System.err.println("Customer interrupted.");
        }
    }
}
