import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.*;

import domain.*;
import reports.*;

public class Writexml {
    private Document document;
    private String filename;
    Bank bank;
    Customer customer;
    Account account;
	  public  Writexml(String name) throws ParserConfigurationException {
          filename = name;
          DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
          DocumentBuilder builder = factory.newDocumentBuilder();
          document = builder.newDocument();
          bank =Bank.getBank();
}
	  public void toWrite() {
          Element root = document.createElement("Bank");
          document.appendChild(root);
          Element eleCustomer = document.createElement("customer");
          for(int i =0;i<=bank.getNumOfCustomers()-1;i++){
        	  customer = bank.getCustomer(i);
	          root.appendChild(eleCustomer);
	          Element fName = document.createElement("first_name");
	          fName.appendChild(document.createTextNode(customer.getFirstName()));
	          eleCustomer.appendChild(fName);	         
	          Element lName = document.createElement("last_name");
	          lName.appendChild(document.createTextNode(customer.getLastName()));
	          eleCustomer.appendChild(lName);
	          
	          for(int j=0;j<customer.getNumOfAccounts();j++){
	        	  account = customer.getAccount(j);
	              Element eleAccount = document.createElement("account");
	        	  eleCustomer.appendChild(eleAccount);
	              Element another =document.createElement("another");
	              Element flag =document.createElement("flag");
	        	    if ( account instanceof SavingsAccount ) {
	        	    	flag.appendChild(document.createTextNode("1"));
	        	    	eleAccount.appendChild(flag);
	        	    	another.appendChild(document.createTextNode(
	        	    			Double.toString(((SavingsAccount)account).getInterestRate()) ));
	  	        	  eleAccount.appendChild(another);

	        	  } else if ( account instanceof CheckingAccount ) {
	        		  flag.appendChild(document.createTextNode("2"));
		        	  eleAccount.appendChild(flag);
	        		  another.appendChild(document.createTextNode(
	        	    			Double.toString(((CheckingAccount)account).getOverdraftProtection()) ));
		        	  eleAccount.appendChild(another);

	        	  }
	        	  Element balance = document.createElement("balance");
	        	  String stBalance =Double.toString(account.getBalance());
	        	  balance.appendChild(document.createTextNode(  stBalance));
	        	  eleAccount.appendChild(balance);
		          }

          }
  }
	  public void toSave() {
          try {
                    TransformerFactory tf = TransformerFactory.newInstance();
                    Transformer transformer = tf.newTransformer();
                    DOMSource source = new DOMSource(document);
                    transformer.setOutputProperty(OutputKeys.ENCODING, "GB2312");
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    PrintWriter pw = new PrintWriter(new FileOutputStream(filename));
                    StreamResult result = new StreamResult(pw);
                    transformer.transform(source, result);
           } catch (TransformerException mye) {
                   mye.printStackTrace();
          } catch (IOException exp) {
                  exp.printStackTrace();
          }
    }
	
}
