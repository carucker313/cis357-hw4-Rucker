public class Payment {
    private float amount;
    public Payment(float cashTendered) { amount = cashTendered; }
    public float getAmount() { return amount; }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
