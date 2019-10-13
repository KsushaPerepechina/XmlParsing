package by.epam.xmlparsing.parser;

import by.epam.xmlparsing.entity.*;
import by.epam.xmlparsing.exception.DepositParseException;
import by.epam.xmlparsing.factory.ParserType;
import by.epam.xmlparsing.factory.XmlParserCreator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

public class SaxDepositParserTest {
    private static final String PATH = ClassLoader.getSystemResource("data/validFile.xml").toString();
    private DepositParser depositParser;

    @BeforeClass
    public void setUp() {
        XmlParserCreator xmlParserCreator = new XmlParserCreator();
        depositParser = xmlParserCreator.createDepositParser(ParserType.SAX);
    }

    @Test
    public void parsePositiveTest() throws DepositParseException {
        List<Deposit> banks = depositParser.parse(PATH);
        Deposit actual = banks.get(0);
        TimeConstraint timeConstraint = new TimeConstraint(1, 6);
        LocalDate startDate = LocalDate.of(2019, 10, 1);
        Deposit expected = new Deposit("James Arthur Gosling", 1000000,
                DepositType.SAVINGS, Currency.USD, 2.3, timeConstraint, startDate,
                "CA84TNSL06709108137902387984", "PNC", Country.CANADA);
        Assert.assertEquals(actual, expected);
    }
}
