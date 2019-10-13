package by.epam.xmlparsing.handler;

import by.epam.xmlparsing.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class DepositHandler extends DefaultHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private List<Deposit> deposits;
    private Deposit currentDeposit;
    private EnumSet<DepositNode> nodes;
    private DepositNode currentNode;

    public DepositHandler() {
        deposits = new ArrayList<>();
        nodes = EnumSet.range(DepositNode.DEPOSITOR_NAME, DepositNode.COUNTRY);
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    @Override
    public void startDocument() {
        LOGGER.info("Parsing started.");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (DepositNode.DEPOSIT.getTitle().equals(localName)) {
            currentDeposit = new Deposit();
            currentDeposit.setAccountId(attrs.getValue("account-id"));
            currentDeposit.setBankName(attrs.getValue("bank-name"));
            String countryName = attrs.getValue("country");
            if (countryName == null) {
                countryName = Country.USA.toString();
            }
            currentDeposit.setCountry(Country.valueOf(countryName.toUpperCase()));
        } else {
            DepositNode temp = DepositNode.getByTitle(localName);
            if (nodes.contains(temp)) {
                currentNode = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (DepositNode.DEPOSIT.getTitle().equals(localName)) {
            deposits.add(currentDeposit);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String content = new String(ch, start, length).trim();
        if (currentNode != null) {
            switch (currentNode) {
                case DEPOSITOR_NAME:
                    currentDeposit.setDepositorName(content);
                    break;
                case AMOUNT:
                    currentDeposit.setAmount(Double.parseDouble(content));
                    break;
                case TYPE:
                    DepositType type = DepositType.valueOf(content.toUpperCase());
                    currentDeposit.setType(type);
                    break;
                case CURRENCY:
                    Currency currency = Currency.valueOf(content);
                    currentDeposit.setCurrency(currency);
                    break;
                case PROFITABILITY:
                    currentDeposit.setProfitability(Double.parseDouble(content));
                    break;
                case YEARS_NUMBER:
                    TimeConstraint timeConstraint = new TimeConstraint();
                    timeConstraint.setYearsNumber(Integer.parseInt(content));
                    currentDeposit.setTimeConstraint(timeConstraint);
                    break;
                case MONTHS_NUMBER:
                    currentDeposit.getTimeConstraint().setMonthsNumber(Integer.parseInt(content));
                    break;
                case START_DATE:
                    LocalDate startDate = LocalDate.parse(content);
                    currentDeposit.setStartDate(startDate);
                    break;
                    default:
                        throw new EnumConstantNotPresentException(currentNode.getDeclaringClass(), currentNode.name());
            }
        }
        currentNode = null;
    }

    @Override
    public void endDocument() {
        LOGGER.info("Parsing ended.");
    }

}
