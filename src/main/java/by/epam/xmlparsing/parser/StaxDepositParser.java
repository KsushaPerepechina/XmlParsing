package by.epam.xmlparsing.parser;

import by.epam.xmlparsing.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StaxDepositParser implements DepositParser {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String UNDERSCORE_SYMBOL = "_";
    private static final String HYPHEN = "-";

    @Override
    public List<Deposit> parse(String path) {
        return buildDepositList(path);
    }

    private List<Deposit> buildDepositList(String fileName) {
        List<Deposit> depositList = new ArrayList<>();
        FileInputStream inputStream = null;
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (DepositNode.valueOf(name.toUpperCase()) == DepositNode.DEPOSIT) {
                        Deposit deposit = buildDeposit(reader);
                        depositList.add(deposit);
                    }
                }
            }
        } catch (XMLStreamException e) {
            LOGGER.error("StAX parsing error!", e);
        } catch (FileNotFoundException e) {
            LOGGER.error("File " + fileName + " not found!", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOGGER.error("Impossible close file " + fileName + ".", e);
            }
        }
        return depositList;
    }

    private Deposit buildDeposit(XMLStreamReader reader) throws XMLStreamException {
        Deposit deposit = new Deposit();
        String accountId = reader.getAttributeValue(null, DepositNode.ACCOUNT_ID.getTitle());
        deposit.setAccountId(accountId);
        String bankName = reader.getAttributeValue(null, DepositNode.BANK_NAME.getTitle());
        deposit.setBankName(bankName);
        String country = reader.getAttributeValue(null, DepositNode.COUNTRY.getTitle());
        if (country == null) {
            country = Country.USA.toString();
        }
        deposit.setCountry(Country.valueOf(country.toUpperCase()));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (DepositNode.valueOf(name.replace(HYPHEN, UNDERSCORE_SYMBOL).toUpperCase())) {
                        case DEPOSITOR_NAME:
                            String depositorName = getXMLText(reader);
                            deposit.setDepositorName(depositorName);
                            break;
                        case AMOUNT:
                            double amount = Double.parseDouble(getXMLText(reader));
                            deposit.setAmount(amount);
                            break;
                        case TYPE:
                            DepositType depositType = DepositType.valueOf(getXMLText(reader).toUpperCase());
                            deposit.setType(depositType);
                            break;
                        case CURRENCY:
                            Currency currency = Currency.valueOf(getXMLText(reader));
                            deposit.setCurrency(currency);
                            break;
                        case PROFITABILITY:
                            double profitability = Double.parseDouble(getXMLText(reader));
                            deposit.setProfitability(profitability);
                            break;
                        case TIME_CONSTRAINT:
                            deposit.setTimeConstraint(buildTimeConstraint(reader));
                            break;
                        case START_DATE:
                            LocalDate startDate = LocalDate.parse(getXMLText(reader));
                            deposit.setStartDate(startDate);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (DepositNode.valueOf(name.replace(HYPHEN, UNDERSCORE_SYMBOL).toUpperCase()) == DepositNode.DEPOSIT) {
                        return deposit;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag <deposit>.");
    }

    private TimeConstraint buildTimeConstraint(XMLStreamReader reader) throws XMLStreamException {
        TimeConstraint timeConstraint = new TimeConstraint();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (DepositNode.valueOf(name.replace(HYPHEN, UNDERSCORE_SYMBOL).toUpperCase())) {
                        case YEARS_NUMBER:
                            int yearsNumber = Integer.parseInt(getXMLText(reader));
                            timeConstraint.setYearsNumber(yearsNumber);
                            break;
                        case MONTHS_NUMBER:
                            int monthsNumber = Integer.parseInt(getXMLText(reader));
                            timeConstraint.setMonthsNumber(monthsNumber);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (DepositNode.getByTitle(name) == DepositNode.TIME_CONSTRAINT) {
                        return timeConstraint;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag <time-constraint>.");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}