import java.util.Objects;

public class GiganticPlantSociety implements StatusActions{

    private static double stockPriceForBuying;
    private static double stockPriceForSell;
    private static int id = 0;
    private int uID;
    CashBalance storage;


    GiganticPlantSociety(CashBalance storage){
        id++;
        uID = id;
        this.storage = storage;
        System.out.println("> Офис ОГР №" + uID + " открыт");
    }

    protected static void setPrices(double stockPriceForBying, double stockPriceForSale){
        GiganticPlantSociety.stockPriceForBuying = stockPriceForBying;
        GiganticPlantSociety.stockPriceForSell = stockPriceForSale;
        System.out.println("> Общество Гигантский Растений установило цены на акции:");
        System.out.println("Цена для покупки = " + stockPriceForBuying + " | Цена для продажи = " + stockPriceForSell);
    }

    protected static double getStockPriceForBuying(){
        return stockPriceForBuying;
    }

    protected static double getStockPriceForSold(){
        return stockPriceForSell;
    }

    @Override
    public void showBalance(){
        System.out.println("> Баланс отделения №" + uID + " = " + storage.getMoneyAmount());
    }

    @Override
    public void showTaboo() {
        System.out.println("> В офисе ОГР разрешено приобретать и продавать акции");
    }

    @Override
    public String toString() {
        return "GiganticPlantSociety{" +
                "uID=" + uID +
                ", storage=" + storage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GiganticPlantSociety)) return false;
        GiganticPlantSociety that = (GiganticPlantSociety) o;
        return uID == that.uID &&
                Objects.equals(storage, that.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uID, storage);
    }
}
