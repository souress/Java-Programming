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

    public static class BankBalance extends Balance{

        BankBalance(double moneyAmount) {
            super(moneyAmount);
        }
    }

    public static class CashBalance extends Balance{
        private int stocksAmount;

        CashBalance(double moneyAmount, int stocksAmount) {
            super(moneyAmount);
            this.stocksAmount = stocksAmount;
        }

        public void setStocksAmount(int stocksAmount){
            this.stocksAmount = stocksAmount;
        }

        public int getStocksAmount(){
            return stocksAmount;
        }
    }

}
