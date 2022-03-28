import java.util.Objects;

public class Shorty implements StocksActions, MoneyActions, StatusActions{
    private String name;
    private CashBalance cash;
    private BankBalance bankBalance;
    private Locations place;


    Shorty(Locations place, CashBalance cash, BankBalance account, String name) {
        this.name = name;
        this.cash = cash;
        this.bankBalance = account;
        this.place = place;
    }

    Shorty(Locations place, CashBalance cash, BankBalance account) {
        this.name = "неизвестный коротышка";
        this.cash = cash;
        this.bankBalance = account;
        this.place = place;
    }

    @Override
    public void showBalance() {
        System.out.println("> Баланс коротышки - " + name);
        if (cash != null) System.out.println("Наличка: Деньги = " + cash.getMoneyAmount() + " | Акции = " + cash.getStocksAmount());
        if (bankBalance != null) System.out.println("Банковский баланс: Деньги = " + bankBalance.getMoneyAmount());
    }

    @Override
    public void buyStocks(int n, GiganticPlantSociety officeID) {
        if (place.equals(Locations.GIGANTIC_PLANT_SOCIETY)) {
            int x = (int) (cash.getMoneyAmount() / GiganticPlantSociety.getStockPriceForBuying());
            if ((n <= x) && (n <= officeID.storage.getStocksAmount())) {
                cash.setStocksAmount(n);
                officeID.storage.setMoneyAmount(officeID.storage.getMoneyAmount() + n * GiganticPlantSociety.getStockPriceForBuying());
                officeID.storage.setStocksAmount(officeID.storage.getStocksAmount() - n);
                System.out.println("> " + name + " приобрёл " + n + " акций");
                cash.setMoneyAmount(cash.getMoneyAmount() - (n * GiganticPlantSociety.getStockPriceForBuying()));
            } else System.out.println("> " + name + " не имеет достаточно денег для приобретения такого количества акций, или акции кончились");
        } else showTaboo();
    }

    @Override
    public void sellStocks(int n, GiganticPlantSociety officeID) {
        if (place.equals(Locations.GIGANTIC_PLANT_SOCIETY)) {
            if ((n <= cash.getStocksAmount()) & (n * GiganticPlantSociety.getStockPriceForSold() <= officeID.storage.getMoneyAmount())) {
                cash.setMoneyAmount(n * GiganticPlantSociety.getStockPriceForSold());
                officeID.storage.setStocksAmount(officeID.storage.getStocksAmount() + n);
                officeID.storage.setMoneyAmount(officeID.storage.getMoneyAmount() - n * GiganticPlantSociety.getStockPriceForSold());
                System.out.println("> " + name + " продал " + n + " акций");
                cash.setStocksAmount(cash.getStocksAmount() - n);
            }
            else System.out.println("> " + name + " не имеет столько акций, или отделение ОГР не имеет денег");
        } else showTaboo();
    }

    @Override
    public void putMoneyToBank(double money){
        if (place.equals(Locations.BANK)){
            if (money <= cash.getMoneyAmount()){
                bankBalance.setMoneyAmount(bankBalance.getMoneyAmount() + money);
                cash.setMoneyAmount(cash.getMoneyAmount() - money);
                System.out.println("> " + name + " положил " + money + " в банк");
            }
            else System.out.println("> " + name + " не имеет столько наличных или Общество не может купить его акции");
        }
        else showTaboo();
    }

    public void putMoneyToBank(){
        if (place.equals(Locations.BANK)){
            bankBalance.setMoneyAmount(bankBalance.getMoneyAmount() + cash.getMoneyAmount());
            System.out.println("> " + name + " положил " + cash.getMoneyAmount() + " в банк");
            cash.setMoneyAmount(0);
        }
        else System.out.println("> " + name + " не имеет столько наличных или Общество не может купить его акции");
    }

    @Override
    public void getMoneyFromBank(double money){
        if (place.equals(Locations.BANK)){
            if (money <= bankBalance.getMoneyAmount()){
                cash.setMoneyAmount(cash.getMoneyAmount() + money);
                bankBalance.setMoneyAmount(bankBalance.getMoneyAmount() - money);
            }
            else System.out.println("> " + name + " не имеет столько денег на банковском счету");
        }
        else showTaboo();
    }

    @Override
    public String toString() {
        return "Shorty{" +
                "name='" + name + '\'' +
                ", cash=" + cash +
                ", account=" + bankBalance +
                ", place=" + place +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shorty)) return false;
        Shorty shorty = (Shorty) o;
        return Objects.equals(name, shorty.name) &&
                Objects.equals(cash, shorty.cash) &&
                Objects.equals(bankBalance, shorty.bankBalance) &&
                place == shorty.place;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cash, bankBalance, place);
    }
}
