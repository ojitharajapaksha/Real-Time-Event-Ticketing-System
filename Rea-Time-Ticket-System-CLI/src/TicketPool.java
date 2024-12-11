// Ticket pool class this manages the shared tickets
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {

    // This BlockingQue is store tickets with thread safety
    private final BlockingQueue<String> tickets = new LinkedBlockingQueue<>();

    // Maximum ticket capacity of the ticket pool
    private final int maxCapacity;

    // Lock to directly manage synchronisation
    private final ReentrantLock lock = new ReentrantLock();

    public TicketPool(){
        // Default maximum ticket capacity sets to 10
        this.maxCapacity = 10;
    }

    // Adds a ticket to the pool while maintaining maximum capacity and synchronisation.
    public void addTicket(String ticket){
        try{
            lock.lock();
            while (tickets.size() >= maxCapacity){
                // Wait until ticket pool is getting free
                Thread.sleep(100);
            }
            tickets.put(ticket);
            System.out.println("TicketPool Added: " + ticket);
        }catch (InterruptedException e){
            // Restore the interrupted state.
            Thread.currentThread().interrupt();
        }finally {
            // Unlocking the lock
            lock.unlock();
        }
    }

    // Ensures synchronisation by removing a ticket from the pool.
    public String removeTicket(){
        try{
            lock.lock();
            return tickets.poll(); // Get and delete a ticket or null if none is available.
        }finally {
            lock.unlock();
        }
    }

    // Return the current number of tickets in the pool
    public int getTicketCount(){
        return tickets.size();
    }
}
