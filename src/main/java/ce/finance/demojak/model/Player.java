package ce.finance.demojak.model;

public class Player {

    private int playedId;
    private String firstName;
    private double amount;

    public Player() {
    }

    public Player(int playedId, String firstName, double amount) {
        this.playedId = playedId;
        this.firstName = firstName;
        this.amount = amount;
    }

    public int getPlayedId() {
        return playedId;
    }

    public void setPlayedId(int playedId) {
        this.playedId = playedId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
