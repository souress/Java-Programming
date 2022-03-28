public enum Locations {
    GIGANTIC_PLANT_SOCIETY("офис ОГР"),
    BANK("банк"),
    STREET("улица");

    private String value;

    Locations(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
