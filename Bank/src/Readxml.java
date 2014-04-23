import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.*;

import domain.*;

public class Readxml {
    private Document document;
    private String filename;
    Bank bank;
    Customer customer;
    Account account;
    
    public Readxml(String name) throws ParserConfigurationException, SAXException, IOException{
    	File targetFile =new File(name);
    	if(targetFile.exists()){
	    	filename = name;
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        document = builder.parse(filename);
	        bank =Bank.getBank();
	        document.getImplementation();
	        NodeList ncustomer =document.getElementsByTagName("customer");//得到一个NodeList对象。
	        NodeList nFirstName = document.getElementsByTagName("first_name");
	        NodeList nLastName = document.getElementsByTagName("last_name");
	        Element nodeCustomer=(Element) ncustomer.item(0);
            NodeList nAccount = nodeCustomer.getElementsByTagName("account");
            NodeList nFlag = nodeCustomer.getElementsByTagName("flag");
            NodeList nBalance = nodeCustomer.getElementsByTagName("balance");
            NodeList nAnother = nodeCustomer.getElementsByTagName("another");
	        for (int i=0;i<nFirstName.getLength();i++){
	            Element nodeFName =(Element)nFirstName.item(i);
	            Element nodeLName =(Element)nLastName.item(i);
	            String fName = nodeFName.getFirstChild().getNodeValue();
	            String lName = nodeLName.getFirstChild().getNodeValue();
	            bank.addCustomer(fName, lName);
				int index = bank.getNumOfCustomers();
				customer = bank.getCustomer(index-1);
            	 Element nodeFlag=(Element) nFlag.item(i);
                 Element nodeBalance =(Element)nBalance.item(i);
                 Element nodeAnother =(Element)nAnother.item(i);
                 int intFlag =  Integer.parseInt(nodeFlag.getFirstChild().getNodeValue());
                 double balance =Double.parseDouble(nodeBalance.getFirstChild().getNodeValue());
                 double another =Double.parseDouble(nodeAnother.getFirstChild().getNodeValue());
                 if(intFlag==1){
                	 customer.addAccount(new SavingsAccount(balance ,another));
                 }
                 if(intFlag==2){
                	 customer.addAccount(new CheckingAccount(balance, another));
                 }
	        }
    	}
    	else{
    		filename = name;
    	}
    }
}