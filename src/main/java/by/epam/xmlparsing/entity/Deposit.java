package by.epam.xmlparsing.entity;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.StringJoiner;

@XmlRootElement
@XmlType(name = "Deposit", propOrder = {
        "amount",
        "type",
        "currency",
        "profitability",
        "timeConstraint",
        "localDate"
})
public class Deposit {
    @XmlAttribute(required = true)
    @XmlID
    private String accountId;
    @XmlAttribute()
    private String depositorName;
    @XmlElement(required = true)
    private double amount;
    @XmlElement(required = true)
    private DepositType type;
    @XmlElement(required = true)
    private Currency currency;
    @XmlElement(required = true)
    private double profitability;
    @XmlElement(required = true)
    private TimeConstraint timeConstraint;
    @XmlElement(required = true)
    private LocalDate startDate;

    public static class TimeConstraint {
        private int yearsNumber;
        private int monthNumber;

        public TimeConstraint() {
        }

        public TimeConstraint(int yearsNumber, int monthNumber) {
            this.yearsNumber = yearsNumber;
            this.monthNumber = monthNumber;
        }

        public int getYearsNumber() {
            return yearsNumber;
        }

        public void setYearsNumber(int yearsNumber) {
            this.yearsNumber = yearsNumber;
        }

        public int getMonthNumber() {
            return monthNumber;
        }

        public void setMonthNumber(int monthNumber) {
            this.monthNumber = monthNumber;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", TimeConstraint.class.getSimpleName() + "[", "]")
                    .add("yearsNumber=" + yearsNumber)
                    .add("monthNumber=" + monthNumber)
                    .toString();
        }
    }
}