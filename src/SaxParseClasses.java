import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;

public class SaxParseClasses extends DefaultHandler {

    public static  ArrayList<MyClass> classes = new ArrayList<>();
    public void printoutClasses (){
        System.out.println(classes.size());
        for (int i =0; i<classes.size();i++){
            System.out.println(classes.get(i).name);
        }
    }
    public void httpToFile () {
        try {
            URL url = new URL("https://goo.gl/tFpBDV");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                 FileWriter write = new FileWriter("text.txt")) {
                String str;
                while ((str = reader.readLine()) != null) {
                    stringBuilder.append(str);
                }
                write.write(stringBuilder.toString().replaceAll("  ",""));
            } catch (MalformedInputException e) {
                e.printStackTrace();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saxParser () throws ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser =factory.newSAXParser();
        SaxParseClasses saxParse = new SaxParseClasses();
        try {
            parser.parse("text.txt", saxParse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equals("class")){
            MyClass myClass = new MyClass(attributes.getValue(0));
            classes.add(myClass);
        }
        if(qName.equals("method")){
            classes.get(classes.size()-1).myMethod.add(attributes.getValue(0));
        }
    }
    @Override
    public void characters (char ch[], int start, int length)
    {
        System.out.print("Characters:    \"");
        String str="";
        for (int i = start; i < start + length; i++) {

            switch (ch[i]) {
                case '\\':
                    System.out.print("\\\\");
                    str+=ch[i];
                    break;
                case '"':
                    System.out.print("\\\"");
                    str+=ch[i];
                    break;
                case '\n':
                    System.out.print("\\n");
                    str+=ch[i];
                    break;
                case '\r':
                    System.out.print("\\r");
                    str+=ch[i];
                    break;
                case '\t':
                    System.out.print("\\t");
                    str+=ch[i];
                    break;
                default:
                    System.out.print(ch[i]);
                    str+=ch[i];
                    break;
            }
        }
        System.out.print("\"\n");
        String tmp = classes.get(0).myMethod.get(0);
        classes.get(0).myMethod.add(0, tmp+" value = "+str);

    }

}
