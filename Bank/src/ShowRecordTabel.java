import java.awt.*;

import java.awt.event.*;

import java.io.IOException;

import javax.swing.*;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import domain.*;



public class ShowRecordTabel extends JFrame {
	public RecordTableModel record = new RecordTableModel();
	private Bank bank;
	private Customer customer;
	private Account account;
	private Writexml writeXml;

	private boolean flag =false;
	
	final static int FIRST_NAME_SIZE = 15;
	final static int LAST_NAME_SIZE =15;
	final static int BALANCE_SIZE =15;
	final static int INTEREST_RATE_SIZE =15;
	final static int PROTECT_SIZE =10;
	final static int ID_SIZE =10;

	
	
	private JTextField jtffName =new JTextField(FIRST_NAME_SIZE);
	private JTextField jtflName =new JTextField(LAST_NAME_SIZE);
	private JTextField jtfBalance =new JTextField(BALANCE_SIZE);
	private JTextField jtfInterestRate =new JTextField(INTEREST_RATE_SIZE);
	private JTextField jtfProtect =new JTextField(PROTECT_SIZE);
	private JTextField jtfID =new JTextField(ID_SIZE);

	
	
	private JButton jbAddCustomer = new JButton("Add Customer");
	private JButton jbSortCustomer = new JButton("Sort Customer");
	private JButton jbDelete = new JButton("Delete Customer");
	private JButton jbSave = new JButton("Save Data");
	private JButton jbFirst = new JButton("|<");
	private JButton jbPrevious = new JButton("<");
	private JButton jbNext = new JButton(">");
	private JButton jbLast = new JButton(">|");
	
	final private JTable recordTabel=new JTable(record);
	 // radio Buttons
	private JRadioButton SavingAccountButton = new JRadioButton("Savings Account", false);
	private JRadioButton CheckingAccountButton = new JRadioButton("Checking Account",false);
	
	public ShowRecordTabel () throws ParserConfigurationException, SAXException, IOException{
		
		
		writeXml = new Writexml("test.xml");
		bank  =Bank.getBank();
		
		JPanel jplName = new JPanel();
		jplName.setLayout(new BorderLayout());
		jplName.add(new JLabel("Last Name"),BorderLayout.WEST);
		jplName.add(jtflName,BorderLayout.CENTER);
		
		JPanel jpfName = new JPanel();
		jpfName.setLayout(new BorderLayout());
		jpfName.add(new JLabel("First Name    "),BorderLayout.WEST);
		jpfName.add(jtffName,BorderLayout.CENTER);
		
		JPanel pName = new JPanel();
		pName.setLayout(new BorderLayout());
		pName.add(jplName,BorderLayout.CENTER);
		pName.add(jpfName,BorderLayout.WEST);	
		
		JPanel jpBalance = new JPanel();
		jpBalance.setLayout(new BorderLayout());
		jpBalance.add(new JLabel("Balance         "),BorderLayout.WEST);
		jpBalance.add(jtfBalance,BorderLayout.CENTER);
		
		JPanel jpProtect = new JPanel();
		jpProtect.setLayout(new BorderLayout());
		jpProtect.add(new JLabel("Protect      "),BorderLayout.WEST);
		jpProtect.add(jtfProtect,BorderLayout.CENTER);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p2.add(jpBalance,BorderLayout.WEST);
		p2.add(jpProtect,BorderLayout.CENTER);
		
		JPanel jpInterest = new JPanel();
		jpInterest.setLayout(new BorderLayout());
		jpInterest.add(new JLabel("Interest Rate"),BorderLayout.WEST);
		jpInterest.add(jtfInterestRate,BorderLayout.CENTER);
		
		JPanel jpID = new JPanel();
		jpID.setLayout(new BorderLayout());
		jpID.add(new JLabel("      ID           "),BorderLayout.WEST);
		jpID.add(jtfID,BorderLayout.CENTER);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		p3.add(jpInterest,BorderLayout.WEST);
		p3.add(jpID,BorderLayout.CENTER);
		

		  //set the ratio buttons
		ButtonGroup bgAccount = new ButtonGroup();
		bgAccount.add(SavingAccountButton);
		bgAccount.add(CheckingAccountButton);
				  
				  //Panel to hold the two buttons
		JPanel jpAccountType =new JPanel();
		jpAccountType.setLayout(new BorderLayout());
		jpAccountType.add(CheckingAccountButton,BorderLayout.CENTER);
		jpAccountType.add(SavingAccountButton,BorderLayout.WEST);
		
		JPanel jpAbove = new JPanel();
		jpAbove.setLayout(new GridLayout(4,1));
		jpAbove.add(pName);
		jpAbove.add(p2);
		jpAbove.add(p3);
		jpAbove.add(jpAccountType);
		
		
		JPanel jpButton1 =new JPanel();
		JPanel jpButton2 =new JPanel();
		jpButton1.add(jbAddCustomer);
		jpButton1.add(jbSortCustomer);
		jpButton1.add(jbSave);
		jpButton1.add(jbDelete);		
		jpButton2.add(jbFirst);
		jpButton2.add(jbPrevious);
		jpButton2.add(jbNext);
		jpButton2.add(jbLast);

		JPanel jpButton =new JPanel();
		jpButton.setLayout(new GridLayout(2,1));
		jpButton.add(jpButton1);
		jpButton.add(jpButton2);
		
		JScrollPane scroll = new JScrollPane(recordTabel);
		scroll.setHorizontalScrollBarPolicy( 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
				scroll.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
				
		this.setLayout(new BorderLayout());
		this.add(jpAbove,BorderLayout.NORTH);
		this.add(scroll,BorderLayout.CENTER);
		this.add(jpButton,BorderLayout.SOUTH);
		
		//when chose one ratio button ,the attribute of another is changed
		  SavingAccountButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {		
				if(SavingAccountButton.isSelected()){
					flag =true;
					jtfProtect.setEditable(false);
					jtfInterestRate.setEditable(true);
				}
			}
		  });
		  
		  CheckingAccountButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {		
					if(CheckingAccountButton.isSelected()){
						flag =false;
						jtfInterestRate.setEditable(false);
						jtfProtect.setEditable(true);
					}
				}
		  });
		  
		  jbAddCustomer.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e) {	
					if (SavingAccountButton.isSelected()){
						flag =true;
					}
					else if(CheckingAccountButton.isSelected()){
						flag =false;
					}
					addCustomer();

					recordTabel.updateUI();
				}
		  });
		  
		  jbSortCustomer.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e) {	
				  bank.sortCustomers();
				  recordTabel.updateUI();
			  }
			  });
		  
		  jbDelete.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e) {	
				  deleteCustomer();
				  recordTabel.updateUI();
			  }
		  });
		  
		  jbFirst.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e) {	
				  setText(0);
			  }
		  });
		  
		  jbPrevious.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e) {	
				  int index =recordTabel.getSelectedRow();
				  setText(index-1);
			  }
		  });
		  
		  jbNext.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e) {	
				  int index =recordTabel.getSelectedRow();
				  setText(index+1);
			  }
		  });
		  
		  jbLast.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e) {	
				  int index =bank.getNumOfCustomers();
				  setText(index-1);
			  }
		  });
		  recordTabel.addMouseListener(new MouseAdapter(){
	    	  public void mouseClicked(MouseEvent e){
	    		  int index =recordTabel.getSelectedRow();
	    		  setText(index);
	    	}
	    });
		  jbSave.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent e) {	
					refreshXml();
			  }
		  });
	}


	public void setText(int index){
		if(bank.getNumOfCustomers()>index&&index>=0){
			  customer = bank.getCustomer(index);
			  account =customer.getAccount(index);
			  jtffName.setText(customer.getFirstName());
			  jtflName.setText(customer.getLastName());
			  jtfBalance.setText(Double.toString(account.getBalance()));
			  if(customer.getTypeOfAccounts(0)==2){
				  jtfProtect.setText(Double.toString(((CheckingAccount)customer.getAccount(0)).getOverdraftProtection()));
			  }
			  else if(customer.getTypeOfAccounts(0)==1){
				  jtfInterestRate.setText(Double.toString(((SavingsAccount)customer.getAccount(0)).getInterestRate()));
			  }
			  jtfID.setText(Integer.toString(index+1));
		  }
		else
			JOptionPane.showMessageDialog(getComponent(0),"Sorry ,we do not have the data you need");
	}
	
	public void refreshXml(){
	  writeXml.toWrite();
	  writeXml.toSave();
	  }
	  
	  public void addCustomer(){
		  if(jtffName.getText()==null||jtflName.getText()==null||jtfBalance.getText()==null){
			  JOptionPane.showMessageDialog(getComponent(0)
					  ,"please complete the input"); 
		  }
		  else{
			  String fName = jtffName.getText();
			  String lName = jtflName.getText();
			  bank.addCustomer(fName, lName);
			  int index = bank.getNumOfCustomers();
			  customer = bank.getCustomer(index-1);
			  double balance = Double.parseDouble(jtfBalance.getText());
			  if(flag){
				  if(jtfInterestRate.getText()==null){
					  JOptionPane.showMessageDialog(getComponent(0)
							  ,"please complete the input"); 
					  }
				  else{
					  double interestRate = Double.parseDouble(jtfInterestRate.getText());
					  customer.addAccount(new SavingsAccount(balance ,interestRate));	  
				  }
			  }
			  else{
				  if(jtfProtect.getText()==null){
					  JOptionPane.showMessageDialog(getComponent(0)
							  ,"please complete the input"); 
					  }
				  else{
					  double protect =Double.parseDouble(jtfProtect.getText());
					  customer.addAccount(new CheckingAccount(balance, protect));			
				  }
			  }
			  clearText();
		  }
	  }
	  
	  public void deleteCustomer(){
		  if(recordTabel.getSelectedRow()<bank.getNumOfCustomers()){
			  bank.deleteCustomer(recordTabel.getSelectedRow()-1);
		  }
		  else JOptionPane.showMessageDialog(getComponent(0),"please choose the correct row"); 
	  }
	  public void clearText(){
		  jtffName.setText("");
		  jtflName.setText("");
		  jtfBalance.setText("");
		  jtfProtect.setEnabled(true);
		  jtfProtect.setText("");
		  jtfInterestRate.setEnabled(true);
		  jtfInterestRate.setText("");	
		  SavingAccountButton.setSelected(false);
		  CheckingAccountButton.setSelected(false);
	  }
	
	  public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		ShowRecordTabel frame = new ShowRecordTabel();
	    frame.pack();
	    frame.setTitle("BankingBook");
	    frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	  }
}
