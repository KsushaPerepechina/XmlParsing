package by.epam.xmlparsing.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Country")
@XmlEnum
public enum Country {
    USA, CANADA, GERMANY, FRANCE, UK, JAPAN, SWITZERLAND, CHINA, RUSSIA
}
