import java.io.IOException;

import javax.swing.table.*;
import javax.xml.parsers.*;

import org.xml.sax.SAXException;

import domain.*;


public class RecordTableModel extends AbstractTableModel{

	private Bank bank;
	private Customer customer;
	private Account account;
	private Readxml readXml;
	
	String[] columnNames=new String[] {"ID","FirstName","LastName","Balance","Proteced","InterestRate"};
	
	public RecordTableModel() throws ParserConfigurationException, SAXException, IOException{

		bank = Bank.getBank();
		setCustomer(50);
		readXml = new Readxml("test.xml");
		
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return bank.getNumOfCustomers();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	public String getColumnName(int col){
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		customer = bank.getCustomer(rowIndex);
		switch(columnIndex){
			case 0:return rowIndex;
			case 1:return customer.getFirstName();
			case 2:return customer.getLastName();
			case 3:return customer.getAccount(0).getBalance();
			case 4:{
				if(customer.getTypeOfAccounts(0)==2)
					return ((CheckingAccount)customer.getAccount(0)).getOverdraftProtection();
				else return "null";
			}
			case 5:{
				if(customer.getTypeOfAccounts(0)==1)
					return ((SavingsAccount)customer.getAccount(0)).getInterestRate();
				else return "null";
			}
		}

		return "null";

		
	}
	
	
	  private void setCustomer(int init_sum){
		  bank.setCustomerNumber(init_sum);
	  }
	  
	


}
