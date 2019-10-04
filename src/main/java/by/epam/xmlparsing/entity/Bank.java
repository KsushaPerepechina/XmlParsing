package by.epam.xmlparsing.entity;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;
import java.util.StringJoiner;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Bank", propOrder = {"deposit"})
public class Bank {
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)//TODO
    @XmlID
    private String name;
    @XmlAttribute()
    private Country country;
    @XmlElement(name="deposit", required = true)
    private List<Deposit> deposits;

    public Bank() {
    }

    public Bank(String name, Country country, List<Deposit> deposits) {
        this.name = name;
        this.country = country;
        this.deposits = deposits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Bank.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("country=" + country)
                .add("deposits=" + deposits)
                .toString();
    }
}
