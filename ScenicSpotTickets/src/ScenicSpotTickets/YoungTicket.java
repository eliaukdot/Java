package ScenicSpotTickets;

public class YoungTicket extends Ticket{
    public YoungTicket() {
        super();
    }
    public YoungTicket(float price) {
        super(price);
    }
    public float singlePrice() {
        return getPrice();
    }
}
