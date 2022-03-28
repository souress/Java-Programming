public interface MoneyActions {

    void putMoneyToBank(double money) throws ShortyIsNotInSuitableLocationException, NotEnoughMoneyException;

    void takeAllCashFromOffice(GiganticPlantSociety officeID) throws NotEnoughMoneyException, ShortyIsNotInSuitableLocationException;

    void putAllCashToBank() throws ShortyIsNotInSuitableLocationException, NotEnoughMoneyException;

    void getMoneyFromBank(double money) throws ShortyIsNotInSuitableLocationException, NotEnoughMoneyException;
}
