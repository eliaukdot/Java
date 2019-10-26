package ScenicSpotTickets;

public class ChildTicket extends Ticket {
    public ChildTicket() {
        super();
    }
    public ChildTicket(float price) {
        super(price);
    }
    public float singlePrice() {
        return getPrice() * 0.3f;
    }
}