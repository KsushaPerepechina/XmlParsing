package by.epam.xmlparsing.factory;

import by.epam.xmlparsing.parser.*;

public class XmlParserCreator {
    public DepositParser createDepositParser(ParserType parserType) {
        switch (parserType) {
            case SAX:
                return new SaxDepositParser();
            case DOM:
                return new DomDepositParser();
            case STAX:
                return new StaxDepositParser();
            default:
                throw new EnumConstantNotPresentException(
                         parserType.getDeclaringClass(), parserType.name());
        }
    }
}
