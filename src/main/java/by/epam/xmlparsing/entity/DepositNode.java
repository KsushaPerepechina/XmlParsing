package by.epam.xmlparsing.entity;

public enum DepositNode {
    DEPOSITS("deposits"),
    DEPOSIT("deposit"),
    DEPOSITOR_NAME("depositor-name"),
    AMOUNT("amount"),
    TYPE("type"),
    CURRENCY("currency"),
    PROFITABILITY("profitability"),
    YEARS_NUMBER("years-number"),
    MONTHS_NUMBER("months-number"),
    START_DATE("start-date"),
    ACCOUNT_ID("account-id"),
    BANK_NAME("bank-name"),
    COUNTRY("country"),
    TIME_CONSTRAINT("time-constraint");

    private String title;

    DepositNode(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static DepositNode getByTitle(String title) {
        for (DepositNode node: DepositNode.values()) {
            if (node.title.equals(title)) {
                return node;
            }
        }
        throw new IllegalArgumentException(title);
    }
}
