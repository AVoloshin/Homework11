import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, SAXException {

        SaxParse saxParse = new SaxParse();
        saxParse.httpToFile();
        saxParse.saxParser();
        saxParse.printoutHumans();
        SaxParseClasses saxParseClasses = new SaxParseClasses();
        saxParseClasses.httpToFile();
        saxParseClasses.saxParser();
        saxParseClasses.printoutClasses();
        GenerateXML generateXML = new GenerateXML();
        generateXML.generateXMLhumans(saxParse.humans);
    }
}
