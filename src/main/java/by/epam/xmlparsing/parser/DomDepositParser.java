package by.epam.xmlparsing.parser;

import by.epam.xmlparsing.entity.*;
import by.epam.xmlparsing.exception.DepositParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DomDepositParser implements DepositParser {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String EMPTY_STRING = "";
    private DocumentBuilder documentBuilder;

    public DomDepositParser() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error("Parser configuration error." , e);
        }
    }

    public List<Deposit> parse(String path) {
        return buildDepositList(path);
    }

    private List<Deposit> buildDepositList(String fileName) {
        List<Deposit> depositList = new ArrayList<>();
        try {
            if (documentBuilder == null) {
                throw new DepositParseException("File is empty.");
            }
            Document document = documentBuilder.parse(fileName);
            Element root = document.getDocumentElement();
            NodeList depositElementList = root.getElementsByTagName(DepositNode.DEPOSIT.getTitle());
            for (int i = 0; i < depositElementList.getLength(); i++) {
                Element depositElement = (Element) depositElementList.item(i);
                Deposit deposit = buildDeposit(depositElement);
                depositList.add(deposit);
            }
        } catch (IOException e) {
            LOGGER.error("File error or I/O error.", e);
        } catch (SAXException | DepositParseException e) {
            LOGGER.error("Parsing failure.", e);
        }
        return depositList;
    }

    private Deposit buildDeposit(Element depositElement) {
        Deposit deposit = new Deposit();
        String accountId = depositElement.getAttribute(DepositNode.ACCOUNT_ID.getTitle());
        deposit.setAccountId(accountId);
        String bankName = depositElement.getAttribute(DepositNode.BANK_NAME.getTitle());
        deposit.setBankName(bankName);
        String country = depositElement.getAttribute(DepositNode.COUNTRY.getTitle());
        if (EMPTY_STRING.equals(country)) {
            country = Country.USA.toString();
        }
        deposit.setCountry(Country.valueOf(country.toUpperCase()));
        String depositorName = getElementTextContent(depositElement, DepositNode.DEPOSITOR_NAME.getTitle());
        deposit.setDepositorName(depositorName);
        String amount = getElementTextContent(depositElement, DepositNode.AMOUNT.getTitle());
        deposit.setAmount(Double.parseDouble(amount));
        String type = getElementTextContent(depositElement, DepositNode.TYPE.getTitle());
        deposit.setType(DepositType.valueOf(type.toUpperCase()));
        String currency = getElementTextContent(depositElement, DepositNode.CURRENCY.getTitle());
        deposit.setCurrency(Currency.valueOf(currency));
        String profitability = getElementTextContent(depositElement, DepositNode.PROFITABILITY.getTitle());
        deposit.setProfitability(Double.parseDouble(profitability));
        Element timeConstraint = (Element)depositElement.getElementsByTagName(DepositNode.TIME_CONSTRAINT.getTitle()).item(0);
        deposit.setTimeConstraint(buildTimeConstraint(timeConstraint));
        String startDateString = getElementTextContent(depositElement, DepositNode.START_DATE.getTitle());
        LocalDate startDate = LocalDate.parse(startDateString);
        deposit.setStartDate(startDate);
        return deposit;
    }

    private TimeConstraint buildTimeConstraint(Element timeConstraintElement) {
        TimeConstraint timeConstraint = new TimeConstraint();
        String yearsNumber = getElementTextContent(timeConstraintElement, DepositNode.YEARS_NUMBER.getTitle());
        timeConstraint.setYearsNumber(Integer.parseInt(yearsNumber));
        String monthsNumber = getElementTextContent(timeConstraintElement, DepositNode.MONTHS_NUMBER.getTitle());
        timeConstraint.setMonthsNumber(Integer.parseInt(monthsNumber));
        return timeConstraint;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
