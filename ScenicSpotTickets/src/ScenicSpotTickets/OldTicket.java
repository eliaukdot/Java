package ScenicSpotTickets;

public class OldTicket extends Ticket{
    public OldTicket() {
        super();
    }
    public OldTicket(float price) {
        super(price);
    }
    public float singlePrice() {
        return getPrice() * 0.5f;
    }
}