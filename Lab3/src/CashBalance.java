public class CashBalance extends Balance{
    private int stocksAmount;

    CashBalance(double moneyAmount, int amount) {
        super(moneyAmount);
        this.stocksAmount = amount;
    }

    public void setStocksAmount(int stocksAmount){
        this.stocksAmount = stocksAmount;
    }

    public int getStocksAmount(){
        return stocksAmount;
    }
}
