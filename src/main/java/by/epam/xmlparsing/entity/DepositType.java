package by.epam.xmlparsing.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Deposit-Type")
@XmlEnum
public enum DepositType {
    DEMAND, URGENT, SETTLEMENT, SAVINGS, METAL
}
