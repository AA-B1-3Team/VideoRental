public enum PriceCode {
    REGULAR(1),
    NEW_RELEASE(2);

    private final int value;

    PriceCode(int value) {
        this.value = value;
    }

    public static PriceCode convertFrom(int value) {
        for (PriceCode priceCode : PriceCode.values()) {
            if (priceCode.value == value) {
                return priceCode;
            }
        }

        return null;
    }
}
