package by.epam.xmlparsing.entity;

import java.time.LocalDate;
import java.util.StringJoiner;

public class Deposit {
    private String depositorName;
    private double amount;
    private DepositType type;
    private Currency currency;
    private double profitability;
    private TimeConstraint timeConstraint;
    private LocalDate startDate;
    private String accountId;
    private String bankName;
    private Country country;

    public Deposit() {
    }

    public Deposit(String depositorName, double amount, DepositType type, Currency currency, double profitability,
                   TimeConstraint timeConstraint, LocalDate startDate, String accountId, String bankName, Country country) {
        this.depositorName = depositorName;
        this.amount = amount;
        this.type = type;
        this.currency = currency;
        this.profitability = profitability;
        this.timeConstraint = timeConstraint;
        this.startDate = startDate;
        this.accountId = accountId;
        this.bankName = bankName;
        this.country = country;
    }

    public void setDepositorName(String depositorName) {
        this.depositorName = depositorName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public DepositType getType() {
        return type;
    }

    public void setType(DepositType type) {
        this.type = type;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getProfitability() {
        return profitability;
    }

    public void setProfitability(double profitability) {
        this.profitability = profitability;
    }

    public TimeConstraint getTimeConstraint() {
        return timeConstraint;
    }

    public void setTimeConstraint(TimeConstraint timeConstraint) {
        this.timeConstraint = timeConstraint;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Country getCountry() {
        if (country == null) {
            return Country.USA;
        }
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Deposit deposit = (Deposit) o;
        return Double.compare(deposit.amount, amount) == 0 &&
                Double.compare(deposit.profitability, profitability) == 0 &&
                (bankName == deposit.bankName || (bankName != null && bankName.equals(deposit.bankName))) &&
                country != null && country == deposit.country &&
                (depositorName == deposit.depositorName || (depositorName != null && depositorName.equals(deposit.depositorName))) &&
                type != null && type == deposit.type &&
                currency != null && currency == deposit.currency &&
                (timeConstraint == deposit.timeConstraint || (timeConstraint != null && timeConstraint.equals(deposit.timeConstraint))) &&
                (startDate == deposit.startDate || (startDate != null && startDate.equals(deposit.startDate))) &&
                (accountId == deposit.accountId || (accountId != null && accountId.equals(deposit.accountId)));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((depositorName == null) ? 0 : depositorName.hashCode());
        result = prime * result + Double.hashCode(amount);
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((currency == null) ? 0 : currency.hashCode());
        result = prime * result + Double.hashCode(profitability);
        result = prime * result + ((timeConstraint == null) ? 0 : timeConstraint.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
        result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Deposit.class.getSimpleName() + "[", "]")
                .add("depositorName='" + depositorName + "'")
                .add("amount=" + amount)
                .add("type=" + type)
                .add("currency=" + currency)
                .add("profitability=" + profitability)
                .add("timeConstraint=" + timeConstraint)
                .add("startDate=" + startDate)
                .add("accountId='" + accountId + "'")
                .add("bankName='" + bankName + "'")
                .add("country=" + country)
                .toString();
    }
}
