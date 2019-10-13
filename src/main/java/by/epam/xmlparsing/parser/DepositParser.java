package by.epam.xmlparsing.parser;

import by.epam.xmlparsing.entity.Deposit;
import by.epam.xmlparsing.exception.DepositParseException;

import java.util.List;

public interface DepositParser {
    List<Deposit> parse(String path) throws DepositParseException;
}
