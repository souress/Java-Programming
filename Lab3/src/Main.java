public class Main {
    public static void main(String[] args) {
        GiganticPlantSociety.setPrices(100, 80);
        GiganticPlantSociety office1 = new GiganticPlantSociety(new CashBalance(0, 500));
        Shorty unnamed1 = new Shorty(Locations.GIGANTIC_PLANT_SOCIETY, new CashBalance(Math.random() * 7000,
                0), new BankBalance(Math.random() * 500));
        unnamed1.showBalance();
        Shorty unnamed2 = new Shorty(Locations.GIGANTIC_PLANT_SOCIETY, new CashBalance(Math.random() * 7000,
                0), new BankBalance(500));
        unnamed2.showBalance();
        Shorty unnamed3 = new Shorty(Locations.GIGANTIC_PLANT_SOCIETY, new CashBalance(Math.random() * 7000,
                0), new BankBalance(Math.random() * 500));
        unnamed3.showBalance();
        unnamed1.buyStocks((int)(Math.random() * 20), office1);
        unnamed2.buyStocks(5, office1);
        unnamed3.buyStocks(43, office1);
        office1.showBalance();
        Shorty Miga = new Shorty(Locations.BANK, new CashBalance(office1.storage.getMoneyAmount(), 0),
                new BankBalance(0), "Miga");
        office1.storage.setMoneyAmount(0);
        Miga.putMoneyToBank();
    }
}
