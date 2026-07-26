package parkinglot;

public class Card implements Payment {
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
    private PricingStrategy pricingStrategy;

    public Card(String cardNumber, String cardHolderName, String expiryDate, String cvv, PricingStrategy pricingStrategy) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.pricingStrategy = pricingStrategy;
    }

    @Override
    public String process(Vehicle vehicle, int hoursParked) {
        double totalAmount = pricingStrategy.calculatePrice(vehicle, hoursParked);
        String maskedCard = "xxxx-" + cardNumber.substring(cardNumber.length() - 4);
        return String.format("Card payment of Rs. %.2f processed for vehicle %s (Card: %s).",
                totalAmount, vehicle.getVehicleNumber(), maskedCard);
    }
}
