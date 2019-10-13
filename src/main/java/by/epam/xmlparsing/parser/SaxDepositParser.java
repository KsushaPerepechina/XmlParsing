package by.epam.xmlparsing.parser;

import by.epam.xmlparsing.entity.Deposit;
import by.epam.xmlparsing.handler.DepositHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SaxDepositParser implements DepositParser {
    private static final Logger LOGGER = LogManager.getLogger();

    public List<Deposit> parse(String path) {
        return buildDepositList(path);
    }

    private List<Deposit> buildDepositList(String path) {
        DepositHandler depositHandler = new DepositHandler();
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(depositHandler);
            reader.parse(path);
        } catch (IOException e) {
            LOGGER.error("File error.", e);
        } catch (SAXException e) {
            LOGGER.error("Parsing failure.", e);
        }
        return depositHandler.getDeposits();
    }
}
