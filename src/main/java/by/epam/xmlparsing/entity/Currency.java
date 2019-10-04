package by.epam.xmlparsing.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Currency")
@XmlEnum
public enum Currency {
    USD, EUR, GBP, JPY, CHF, CNY, RUB
}
