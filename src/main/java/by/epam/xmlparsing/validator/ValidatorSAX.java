package by.epam.xmlparsing.validator;

import by.epam.xmlparsing.handler.BankErrorHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ValidatorSAX {
    private static final Logger LOGGER = LogManager.getLogger(ValidatorSAX.class);
    private static final String XSD_PATH = "src/main/resources/banks.xsd";

    public boolean isValid(String path) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(XSD_PATH));
            Validator validator = schema.newValidator();
            Source source = new StreamSource(path);
            BankErrorHandler errorHandler = new BankErrorHandler();
            validator.setErrorHandler(errorHandler);
            validator.validate(source);
            return true;
        } catch (SAXException e) {
            LOGGER.error("Validation " + path + " is not valid because " + e.getMessage());
        } catch (IOException e) {
            LOGGER.error(path + " is not valid because " + e.getMessage());
        }
        return false;
    }
}
