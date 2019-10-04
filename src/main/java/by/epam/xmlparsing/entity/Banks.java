package by.epam.xmlparsing.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@XmlAccessorType(XmlAccessType.FIELD)//TODO
@XmlRootElement(name="banks")
public class Banks {
    @XmlElement(name="bank")
    private List<Bank> banks = new ArrayList<>();

    public Banks() {
    }

    public Banks(List<Bank> banks) {
        this.banks = banks;
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }

    public boolean add(Bank bank) {
        return banks.add(bank);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Banks.class.getSimpleName() + "[", "]")
                .add("banks=" + banks)
                .toString();
    }
}