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

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

public class SaxParse extends DefaultHandler {

    public static  ArrayList<Human> humans = new ArrayList<>();
    public void printoutHumans (){
        System.out.println(humans.size());
        for (Human human:humans){
            System.out.println(human.getClass());
        }
    }
    public void httpToFile () {
        try {
            URL url = new URL("https://goo.gl/tFpBDV");
            URL url2 = new URL("https://goo.gl/AZnd2V");
            HttpsURLConnection connection = (HttpsURLConnection) url2.openConnection();
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
        }catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void saxParser () throws ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser =factory.newSAXParser();
        SaxParse saxParse = new SaxParse();
        try {
            parser.parse("text.txt", saxParse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("<"+qName+">");
        if(qName.equals("professor")){
            Professor prof = new Professor(attributes.getValue(0),attributes.getValue(1),attributes.getValue(2));
            humans.add(prof);
            System.out.println(prof.name);
            System.out.println(humans.get(1).name);
        }
        if(qName.equals("member")) {
            humans.add(new Employee(attributes.getValue(0)));
        }
        if(qName.equals("student")) {
            humans.add(new Student(attributes.getValue(0), attributes.getValue(1),attributes.getQName(2)));
            System.out.println(humans.size());
        }
        /**for(int i=0; i<= attributes.getLength();i++){
            if(attributes.getQName(i)!=null){
                System.out.print(""+attributes.getQName(i)+"="+attributes.getValue(i));
            }
        }**/
    }
}
