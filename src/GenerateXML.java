import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class GenerateXML {
    public void generateXML(ArrayList<MyClass> classes){
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("application");
            doc.appendChild(rootElement);
            for(int i=0; i<classes.size(); i++){
                Element classElement = doc.createElement("class");
                rootElement.appendChild(classElement);
                classElement.setAttribute("name", classes.get(i).name);
                for (int j=0; j<classes.get(i).myMethod.size(); j++){
                    Element method = doc.createElement("method");
                    classElement.appendChild(method);
                    method.setAttribute("name", classes.get(i).myMethod.get(j));
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result =  new StreamResult(new File("testing.xml"));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
    public void generateXMLhumans(ArrayList<Human> humans){
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("humans");
            doc.appendChild(rootElement);
            for(int i=0; i<humans.size(); i++) {
                if (humans.get(i) instanceof Professor) {
                    Element classElement = doc.createElement("professor");
                    rootElement.appendChild(classElement);
                    classElement.setAttribute("name", ((Professor) humans.get(i)).name);
                    classElement.setAttribute("experiense", ((Professor) humans.get(i)).exp);
                    classElement.setAttribute("science", ((Professor) humans.get(i)).disc);
                }
                if (humans.get(i) instanceof Student) {
                    Element classElement = doc.createElement("student");
                    rootElement.appendChild(classElement);
                    classElement.setAttribute("name", ((Student)humans.get(i)).name);
                    classElement.setAttribute("kurs", ((Student) humans.get(i)).kurs);
                    classElement.setAttribute("science", ((Student) humans.get(i)).specs);
                }
                if (humans.get(i) instanceof Employee) {
                    Element classElement = doc.createElement("personal");
                    rootElement.appendChild(classElement);
                    classElement.setAttribute("name", ((Employee) humans.get(i)).name);
                    classElement.setAttribute("work", ((Employee) humans.get(i)).work);
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result =  new StreamResult(new File("testing.xml"));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
