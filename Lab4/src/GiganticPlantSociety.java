import java.util.Objects;

public class GiganticPlantSociety {

    private static double stockPriceForBuying;
    private static double stockPriceForSell;
    private static int id = 0;
    private int uID;
    private boolean isOfficeDoorOpen;
    Balance.CashBalance storage;

    public void openGiganticPlantSociety(Shorty shorty) {
        if (shorty.isGiganticPlantSocietyOwner() && !this.isOfficeDoorOpen) {
            this.isOfficeDoorOpen = true;
            System.out.println("> " + shorty.getName() + " открыл офис ОГР");
        }
        else System.out.println("> " + shorty.getName() + " не является владельцем ОГР или дверь уже открыта");
    }

    public void closeGiganticPlantSociety(Shorty shorty) {
        if (shorty.isGiganticPlantSocietyOwner() && this.isOfficeDoorOpen) {
            this.isOfficeDoorOpen = false;
            System.out.println("> " + shorty.getName() + " закрыл офис ОГР");
        }
        else System.out.println("> " + shorty.getName() + " не является владельцем ОГР или дверь уже закрыта");
    }

    public boolean isOfficeDoorOpen() {
        return isOfficeDoorOpen;
    }

    GiganticPlantSociety(Balance.CashBalance storage, boolean isOfficeDoorOpen) {
        id++;
        uID = id;
        this.storage = storage;
        System.out.println("> Офис ОГР №" + uID + " материализовался из воздуха");
        this.isOfficeDoorOpen = isOfficeDoorOpen;
    }

    protected static void setPrices(double stockPriceForBuying, double stockPriceForSale) {
        GiganticPlantSociety.stockPriceForBuying = stockPriceForBuying;
        GiganticPlantSociety.stockPriceForSell = stockPriceForSale;
        System.out.println("> Общество Гигантский Растений установило цены на акции:");
        System.out.println("Цена для покупки = " + stockPriceForBuying + " | Цена для продажи = " + stockPriceForSell);
    }

    protected static double getStockPriceForBuying() {
        return stockPriceForBuying;
    }

    protected static double getStockPriceForSold() {
        return stockPriceForSell;
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
