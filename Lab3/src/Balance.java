abstract class Balance {

    private double moneyAmount;

    Balance(double moneyAmount){
        this.moneyAmount = moneyAmount;
    }

    public void setMoneyAmount(double moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public double getMoneyAmount() {
        return moneyAmount;
    }
}
