public interface StatusActions {

    void showBalance();

    default void showTaboo() {
        System.out.println("> нельзя совершить это действие в данной локации");
    }
}
