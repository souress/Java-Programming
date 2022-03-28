public interface StocksActions {
    void buyStocks(int n, GiganticPlantSociety officeID) throws ShortyIsNotInSuitableLocationException, NotEnoughMoneyException;
    void sellStocks(int n, GiganticPlantSociety officeID) throws ShortyIsNotInSuitableLocationException, NotEnoughMoneyException;
}
