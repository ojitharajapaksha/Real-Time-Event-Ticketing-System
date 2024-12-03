import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {
    private final BlockingQueue<String> tickets = new LinkedBlockingQueue<>();
    private final int maxCapacity;
    private final ReentrantLock lock = new ReentrantLock();

    public TicketPool(){
        this.maxCapacity = 10;
    }

    public void addTicket(String ticket){
        try{
            lock.lock();
            while (tickets.size() >= maxCapacity){
                Thread.sleep(100);
            }
            ticket.put(ticket);
            System.out.println("TicketPool Added " + ticket);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }finally {
            lock.unlock();
        }
    }

    public String removeTicket(){
        try{
            lock.lock();
            return tickets.poll();
        }finally {
            lock.unlock();
        }
    }

    public int getTicketCount(){
        return tickets.size();
    }
}
