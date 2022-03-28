import java.util.Objects;

public class Shorty implements StocksActions, MoneyActions{
    private String name;
    private Balance.CashBalance cash;
    private Balance.BankBalance bankBalance;
    private Locations place;
    private boolean isGiganticPlantSocietyOwner;
    private Legs legs;

    {
        try {
            legs = new Legs();
        } catch (TooManyLegsException e) {
            System.out.println(e.getMessage());;
        }
    }

    Shorty(Locations place, Balance.CashBalance cash, Balance.BankBalance account, String name, boolean isGiganticPlantSocietyOwner) {
        this.name = name;
        this.cash = cash;
        this.bankBalance = account;
        this.place = place;
        this.isGiganticPlantSocietyOwner = isGiganticPlantSocietyOwner;
    }

    Shorty(Locations place, Balance.CashBalance cash, Balance.BankBalance account, boolean isGiganticPlantSocietyOwner) {
        this.name = "неизвестный коротышка";
        this.cash = cash;
        this.bankBalance = account;
        this.place = place;
        this.isGiganticPlantSocietyOwner = isGiganticPlantSocietyOwner;
    }

    public boolean isGiganticPlantSocietyOwner() {
        return isGiganticPlantSocietyOwner;
    }

    public String getName() {
        return name;
    }

    public Balance.BankBalance getBankBalance() {
        return bankBalance;
    }

    public Balance.CashBalance getCash() {
        return cash;
    }

    public void goToGiganticPlantSociety(GiganticPlantSociety office, Shorty shorty) throws NotEnoughLegsException{
        if (legs == null) throw new NotEnoughLegsException("> у " + name
                + " нет ног, он не сможет перейти в " + shorty.getPosition().getLocation());
        else {
            if (place.equals(Locations.STREET) && office.isOfficeDoorOpen()) {
                this.place = Locations.GIGANTIC_PLANT_SOCIETY;
                System.out.println("> " + name + " переместился в " + shorty.getPosition().getLocation());
            } else if (place.equals(Locations.GIGANTIC_PLANT_SOCIETY))
                System.out.println("> " + name + " уже пришёл в " + shorty.getPosition().getLocation());
            else System.out.println("> " + name + " не смог зайти в " + shorty.getPosition().getLocation()
                        + ". Вход осуществляется только с улицы и при условии, что дверь открыта!");
        }
    }

    public void goToBank(Shorty shorty) throws NotEnoughLegsException{
        if (legs == null) throw new NotEnoughLegsException("> " + name + " не может пойти в "
                + shorty.getPosition().getLocation() + ", у него нет ног :(");
        else {
            if (place.equals(Locations.STREET)) {
                this.place = Locations.BANK;
                System.out.println("> " + name + " переместился в " + shorty.getPosition().getLocation());
            } else if (place.equals(Locations.BANK))
                System.out.println("> " + name + " уже в " + shorty.getPosition().getLocation());
            else System.out.println("> " + name + " не смог зайти в " + shorty.getPosition().getLocation()
                        + ". Вход в " + shorty.getPosition().getLocation() + " осуществляется только с улицы!");
        }
    }

    public void goToStreet(Shorty shorty) throws NotEnoughLegsException{
        if (legs == null) throw new NotEnoughLegsException("> " + name + " не может выйти на "
                + shorty.getPosition().getLocation() + ", у него нет ног :(");
        else {
            if (place.equals(Locations.STREET)) System.out.println("> " + name + " уже на " + shorty.getPosition().getLocation());
            else {
                this.place = Locations.STREET;
                System.out.println("> " + name + " переместился на " + shorty.getPosition().getLocation());
            }
        }
    }

    @Override
    public void buyStocks(int n, GiganticPlantSociety officeID) throws ShortyIsNotInSuitableLocationException, NotEnoughMoneyException{
        if (place.equals(Locations.GIGANTIC_PLANT_SOCIETY)) {
            int x = (int) (cash.getMoneyAmount() / GiganticPlantSociety.getStockPriceForBuying());
            if ((n <= x) && (n <= officeID.storage.getStocksAmount())) {
                cash.setStocksAmount(n);
                officeID.storage.setMoneyAmount(officeID.storage.getMoneyAmount() + n * GiganticPlantSociety.getStockPriceForBuying());
                officeID.storage.setStocksAmount(officeID.storage.getStocksAmount() - n);
                System.out.println("> " + name + " приобрёл " + n + " акций");
                cash.setMoneyAmount(cash.getMoneyAmount() - (n * GiganticPlantSociety.getStockPriceForBuying()));
            } else throw new NotEnoughMoneyException("> " + name + " не имеет достаточно денег для приобретения такого количества акций, или акции кончились");
        } else throw new ShortyIsNotInSuitableLocationException("> " + name + " не находится в офисе ОГР");
    }

    @Override
    public void sellStocks(int n, GiganticPlantSociety officeID) throws ShortyIsNotInSuitableLocationException, NotEnoughMoneyException{
        if (place.equals(Locations.GIGANTIC_PLANT_SOCIETY)) {
            if ((n <= cash.getStocksAmount()) & (n * GiganticPlantSociety.getStockPriceForSold() <= officeID.storage.getMoneyAmount())) {
                cash.setMoneyAmount(n * GiganticPlantSociety.getStockPriceForSold());
                officeID.storage.setStocksAmount(officeID.storage.getStocksAmount() + n);
                officeID.storage.setMoneyAmount(officeID.storage.getMoneyAmount() - n * GiganticPlantSociety.getStockPriceForSold());
                System.out.println("> " + name + " продал " + n + " акций");
                cash.setStocksAmount(cash.getStocksAmount() - n);
            }
            else throw new NotEnoughMoneyException("> " + name + " не имеет столько акций, или отделение ОГР не имеет денег");
        } else throw new ShortyIsNotInSuitableLocationException("> " + name + " не находится в офисе ОГР");
    }

    @Override
    public void putMoneyToBank(double money) throws ShortyIsNotInSuitableLocationException, NotEnoughMoneyException{
        if (place.equals(Locations.BANK)){
            if (money <= cash.getMoneyAmount()){
                bankBalance.setMoneyAmount(bankBalance.getMoneyAmount() + money);
                cash.setMoneyAmount(cash.getMoneyAmount() - money);
                System.out.println("> " + name + " положил " + money + " в банк");
            } else throw new NotEnoughMoneyException("> у " + name + " нет налички");
        } else throw new ShortyIsNotInSuitableLocationException("> " + name + " не находится в банке");
    }

    @Override
    public void takeAllCashFromOffice(GiganticPlantSociety officeID) throws NotEnoughMoneyException,ShortyIsNotInSuitableLocationException {
        if (place.equals(Locations.GIGANTIC_PLANT_SOCIETY)) {
            if (isGiganticPlantSocietyOwner) {
                if (officeID.storage.getMoneyAmount() == 0.0D)
                    throw new NotEnoughMoneyException("> в офисе ОГР нет денег");
                else {
                    cash.setMoneyAmount(cash.getMoneyAmount() + officeID.storage.getMoneyAmount());
                    System.out.println("> Владелец ОГР " + name + " забрал все деньги из офиса");
                    officeID.storage.setMoneyAmount(0);
                }
            } else throw new RobberyAttemptException("Произошла попытка ограбления офиса ОГР");
        } else throw new ShortyIsNotInSuitableLocationException("> " + name + " не находится в офисе");
    }

    @Override
    public void putAllCashToBank() throws ShortyIsNotInSuitableLocationException, NotEnoughMoneyException{
        if (place.equals(Locations.BANK)){
            if (cash.getMoneyAmount() != 0.0D) {
                bankBalance.setMoneyAmount(bankBalance.getMoneyAmount() + cash.getMoneyAmount());
                System.out.println("> " + name + " положил " + cash.getMoneyAmount() + " в банк");
                cash.setMoneyAmount(0);
            } else throw new NotEnoughMoneyException("> у " + name + " нет налички");
        } else throw new ShortyIsNotInSuitableLocationException("> " + name + " не находится в банке");
    }

    @Override
    public void getMoneyFromBank(double money) throws ShortyIsNotInSuitableLocationException, NotEnoughMoneyException{
        if (place.equals(Locations.BANK)) {
            if (money <= bankBalance.getMoneyAmount()){
                cash.setMoneyAmount(cash.getMoneyAmount() + money);
                bankBalance.setMoneyAmount(bankBalance.getMoneyAmount() - money);
            }
            else throw new NotEnoughMoneyException("> " + name + " не имеет столько денег на банковском счету");
        }
        else throw new ShortyIsNotInSuitableLocationException("> " + name + " не находится в банке");

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

    private interface IPosition {
        Locations getLocation();
    }

    private IPosition getPosition() {
        class ShortyPosition implements IPosition {
            Locations location = Shorty.this.place;

            @Override
            public Locations getLocation() {
                return this.location;
            }
        }
        return new ShortyPosition();
    }

    private class Legs {
        Legs() throws TooManyLegsException {
            if (!(legs == null)) throw new TooManyLegsException("> У коротышки не должно быть более пары ног!");
        }
    }
}
