package ScenicSpotTickets;

abstract class Ticket {
    private float price;
    public Ticket() {
        price = 100;
    }
    public Ticket(float price) {
        this.price = price;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public abstract float singlePrice();
}
