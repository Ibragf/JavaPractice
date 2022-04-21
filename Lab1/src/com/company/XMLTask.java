package com.company;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class XMLTask {
    private static String pathfile="file.xml";

    public static void addNewEmployee() throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        File file = new File(pathfile);
        if (!file.exists()) {
            file.createNewFile();
            Document newXml = builder.newDocument();
            Element dep = newXml.createElement("Departament");
            newXml.appendChild(dep);
            writeToXML(newXml);
        }

        Document doc = builder.parse(file);

        Node root = doc.getDocumentElement();
        Element employee=doc.createElement("employee");

        Element firstName=doc.createElement("FirstName");
        Element lastName=doc.createElement("LastName");
        Element city=doc.createElement("City");

        InputEmployee(firstName,lastName,city);//ввод данных сотрудника

        employee.appendChild(firstName);
        employee.appendChild(lastName);
        employee.appendChild(city);

        root.appendChild(employee);


        writeToXML(doc);
    }

    private static void writeToXML(Document document) throws TransformerException, FileNotFoundException {
        Transformer tr=TransformerFactory.newInstance().newTransformer();
        DOMSource source=new DOMSource(document);
        FileOutputStream fos=new FileOutputStream(pathfile);
        StreamResult result=new StreamResult(fos);
        tr.transform(source,result);
    }

    public static void InputEmployee(Element fname, Element lname, Element city)
    {
        Scanner in=new Scanner(System.in);
        System.out.println("Введите имя:");
        fname.setTextContent(in.nextLine());
        System.out.println("Введите фамилию:");
        lname.setTextContent(in.nextLine());
        System.out.println("Введите город:");
        city.setTextContent(in.nextLine());
    }


    public static void readXML() throws ParserConfigurationException, IOException, SAXException {
        File file=new File(pathfile);
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=factory.newDocumentBuilder();
        Document doc=builder.parse(file);

        NodeList employeesNodeList=doc.getElementsByTagName("employee");
        List<Employee> employees=new ArrayList<>();
        //employeesNodeList.item(0).removeChild(employeesNodeList.item(0).getFirstChild());

        for (int i=0;i<employeesNodeList.getLength();i++)
        {
            if (employeesNodeList.item(i).getNodeType()== Node.ELEMENT_NODE)
            {
                Element employeeElement=(Element) employeesNodeList.item(i);
                NodeList childNodes=employeeElement.getChildNodes();

                Employee employee=new Employee();
                for(int j=0;j<childNodes.getLength();j++)
                {
                    if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE)
                    {
                        Element childElement=(Element)childNodes.item(j);

                        switch (childElement.getNodeName()){
                            case "FirstName":{
                                employee.setFirstName(childElement.getTextContent());
                            }
                            case "LastName":{
                                employee.setLastName(childElement.getTextContent());
                            }
                            case "City":{
                                employee.setCity(childElement.getTextContent());
                            }
                        }
                    }
                }
                employees.add(employee);
            }
        }
        employees.forEach(System.out::println);
    }

    public static void deleteXML()
    {
        File file=new File(pathfile);
        file.delete();
    }

}


class Employee{
    private String firstName;
    private String lastName;
    private String city;

    public String getCity() {
        return city;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}