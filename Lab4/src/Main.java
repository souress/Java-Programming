public class Main {

    public static void main(String[] args) {

        GiganticPlantSociety.setPrices(100, 80);
        GiganticPlantSociety office = new GiganticPlantSociety(new Balance.CashBalance(0, 50),false );

        Shorty buyer_1 = new Shorty(Locations.STREET, new Balance.CashBalance(Math.random() * 10000,
                0), new Balance.BankBalance(Math.random() * 1000), "покупатель_1", false);

        Shorty buyer_2 = new Shorty(Locations.STREET, new Balance.CashBalance(Math.random() * 10000,
                0), new Balance.BankBalance(1000), "покупатель_2", false);

        Shorty buyer_3 = new Shorty(Locations.STREET, new Balance.CashBalance(Math.random() * 10000,
                0), new Balance.BankBalance(Math.random() * 1000), "покупатель_3", false);

        Shorty buyer_4 = new Shorty(Locations.STREET, new Balance.CashBalance(Math.random() * 10000,
                0), new Balance.BankBalance(Math.random() * 1000), "покупатель_4", false);

        Shorty miga = new Shorty(Locations.STREET, new Balance.CashBalance(0, 0),
                new Balance.BankBalance(0), "Miga", true);

        BalanceStatus shortiesBalance = new BalanceStatus() {
            @Override
            public void showBalance(Shorty shorty) {
                System.out.println("> Баланс коротышки - " + shorty.getName());
                if (shorty.getCash() != null) System.out.println("Наличка: Деньги = " + shorty.getCash().getMoneyAmount()
                        + " | Акции = " + shorty.getCash().getStocksAmount());
                if (shorty.getBankBalance() != null) System.out.println("Банковский баланс: Деньги = "
                        + shorty.getBankBalance().getMoneyAmount());
            }
        };

        shortiesBalance.showBalance(buyer_1);

        office.openGiganticPlantSociety(miga);

        try {
            buyer_1.goToGiganticPlantSociety(office, buyer_1);
        } catch (NotEnoughLegsException e) {
            e.printStackTrace();
        }
        try {
            buyer_2.goToGiganticPlantSociety(office, buyer_2);
        } catch (NotEnoughLegsException e) {
            e.printStackTrace();
        }
        try {
            buyer_3.goToBank(buyer_3);
        } catch (NotEnoughLegsException e) {
            e.printStackTrace();
        }
        try {
            buyer_4.goToStreet(buyer_4);
        } catch (NotEnoughLegsException e) {
            e.printStackTrace();
        }

        try {
            buyer_3.getMoneyFromBank(1000.0D);
        } catch (ShortyIsNotInSuitableLocationException | NotEnoughMoneyException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            buyer_4.getMoneyFromBank(1000.0D);
        } catch (ShortyIsNotInSuitableLocationException | NotEnoughMoneyException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            buyer_1.buyStocks((int)(Math.random() * 30), office);
        }
        catch (ShortyIsNotInSuitableLocationException | NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
        }

        try {
            buyer_2.buyStocks((int)(Math.random() * 20), office);
        } catch (ShortyIsNotInSuitableLocationException | NotEnoughMoneyException e) {
            System.out.println(e.getMessage());
        }

        try {
            miga.goToGiganticPlantSociety(office, miga);
        } catch (NotEnoughLegsException e) {
            e.printStackTrace();
        }

        try {
            miga.takeAllCashFromOffice(office);
        } catch (NotEnoughMoneyException | ShortyIsNotInSuitableLocationException ex) {
            System.out.println(ex.getMessage());
        }

//        try {
////            buyer_1.takeAllCashFromOffice(office);
////        } catch (NotEnoughMoneyException | ShortyIsNotInSuitableLocationException ex) {
////            System.out.println(ex.getMessage());
////        }

        try {
            miga.goToStreet(miga);
        } catch (NotEnoughLegsException e) {
            e.printStackTrace();
        }

        office.closeGiganticPlantSociety(miga);

        try {
            miga.goToBank(miga);
        } catch (NotEnoughLegsException e) {
            e.printStackTrace();
        }

        try {
            miga.putAllCashToBank();
        } catch (ShortyIsNotInSuitableLocationException | NotEnoughMoneyException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
