package by.epam.xmlparsing.entity;

import java.util.StringJoiner;

public class TimeConstraint {
    private int yearsNumber;
    private int monthsNumber;

    public TimeConstraint() {
    }

    public TimeConstraint(int yearsNumber, int monthsNumber) {
        this.yearsNumber = yearsNumber;
        this.monthsNumber = monthsNumber;
    }

    public void setYearsNumber(int yearsNumber) {
        this.yearsNumber = yearsNumber;
    }

    public void setMonthsNumber(int monthsNumber) {
        this.monthsNumber = monthsNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeConstraint that = (TimeConstraint) o;
        return yearsNumber == that.yearsNumber &&
                monthsNumber == that.monthsNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + yearsNumber;
        result = prime * result + monthsNumber;
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TimeConstraint.class.getSimpleName() + "[", "]")
                .add("yearsNumber=" + yearsNumber)
                .add("monthsNumber=" + monthsNumber)
                .toString();
    }
}
