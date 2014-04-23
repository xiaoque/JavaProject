import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import reports.CustomerReport;

import domain.*;

public class Service extends JFrame{
	//the variable that do operate to bank
	  private Bank  bank;
	  private Customer customer;
	  private Account account;
	  
	  //static data to make sure of these input
	  final static int FIRST_NAME_SIZE = 15;
	  final static int LAST_NAME_SIZE =15;
	  final static int BALANCE_SIZE =15;
	  final static int INTEREST_RATE_SIZE =15;
	  final static int PROTECT_SIZE =10;
	  final static int ACCOUNT_INDEX =10;
	  final static int WITHDRAW_SIZE =15;
	  final static int DEPOSIT_SIZE =10; 
	  
	  //xml files
	  private boolean flag ;
	  private Writexml writeXml ;
	  private Readxml readXml;
	  
	  // Text fields
	  private JTextField jtffName =new JTextField(FIRST_NAME_SIZE);
	  private JTextField jtflName =new JTextField(LAST_NAME_SIZE);
	  private JTextField jtfBalance =new JTextField(BALANCE_SIZE);
	  private JTextField jtfInterestRate =new JTextField(INTEREST_RATE_SIZE);
	  private JTextField jtfProtect =new JTextField(PROTECT_SIZE);
	  private JTextField jtfAccountIndex = new JTextField(ACCOUNT_INDEX);
	  private JTextField jtfWithdraw = new JTextField(WITHDRAW_SIZE);
	  private JTextField jtfDeposit = new JTextField(DEPOSIT_SIZE);
	  
	 // Buttons
	  private JButton jbAddCustomer = new JButton("Add Customer");
	  private JButton jbAddAccount = new JButton("Add Account");
	  private JButton jbSearchCustomer = new JButton("Search Customer");
	  private JButton jbSortCustomer = new JButton("Sort Customer");
	  private JButton jbSetAccount = new JButton("Select Account");
	  private JButton jbWithdraw = new JButton("Withdraw");
	  private JButton jbDeposit = new JButton("Deposit");
	  private JButton jbDelete = new JButton("Delete Customer");
	  private JButton jbView = new JButton("View Customer");
	  
	  // radio Buttons
	  private JRadioButton SavingAccountButton = new JRadioButton("Savings Account", false);
	  private JRadioButton CheckingAccountButton = new JRadioButton("Checking Account",false);
	  
	  public Service() throws ParserConfigurationException, SAXException, IOException{
		  
		  //set the xml file and bank
		  jtfAccountIndex.setText("0");
		  bank = Bank.getBank();
		  setCustomer(50);
		  readXml = new Readxml("test.xml");
          writeXml = new Writexml("test.xml");
          
          //Panel to hold labels first name,balance ,interest rate, withdraw
		  JPanel pLeft = new JPanel();
		  pLeft.setLayout(new GridLayout(4, 1));
		  pLeft.add(new JLabel("First Name"));
		  pLeft.add(new JLabel("Balance"));
		  pLeft.add(new JLabel("Interest Rate"));
		  pLeft.add(new JLabel("Withdraw"));
		  
		  //Panel to hold last name
		  JPanel jplName = new JPanel();
		  jplName.setLayout(new BorderLayout());
		  jplName.add(new JLabel("Last Name"),BorderLayout.WEST);
		  jplName.add(jtflName,BorderLayout.CENTER);
		  
		  //Panel to hold first name and last name
		  JPanel jpName =new JPanel();
		  jpName.setLayout(new BorderLayout());
		  jpName.add(jtffName,BorderLayout.WEST);
		  jpName.add(jplName,BorderLayout.CENTER);
		  
		  //Panel to hold protect
		  JPanel jpProtect = new JPanel();
		  jpProtect.setLayout(new BorderLayout());
		  jpProtect.add(new JLabel("Protect       "), BorderLayout.WEST);
		  jpProtect.add(jtfProtect, BorderLayout.CENTER);
		  
		  //Panel to hold account index
		  JPanel jpIndex =new JPanel();
		  jpIndex.setLayout(new BorderLayout());
		  jpIndex.add(new JLabel("Account index"),BorderLayout.WEST);
		  jpIndex.add(jtfAccountIndex,BorderLayout.CENTER);
		  
		  //Panel to hold interest rate and account index
		  JPanel jpInterestRate =new JPanel();
		  jpInterestRate.setLayout(new BorderLayout());
		  jpInterestRate.add(jtfInterestRate,BorderLayout.WEST);
		  jpInterestRate.add(jpIndex,BorderLayout.CENTER);
		  
		  //Panel to hold balance and protect
		  JPanel jpBalance = new JPanel();
		  jpBalance.setLayout(new BorderLayout());
		  jpBalance.add(jtfBalance,BorderLayout.WEST);
		  jpBalance.add(jpProtect,BorderLayout.CENTER);
		  
		  //Panel to hold deposit
		  JPanel jpDeposit = new JPanel();
		  jpDeposit.setLayout(new BorderLayout());
		  jpDeposit.add(new JLabel("Deposit      "),BorderLayout.WEST);
		  jpDeposit.add(jtfDeposit,BorderLayout.CENTER);
		  
		  //Panel to hold withdraw and deposit
		  JPanel jpWithDraw = new JPanel();
		  jpWithDraw.setLayout(new BorderLayout());
		  jpWithDraw.add(jtfWithdraw,BorderLayout.WEST);
		  jpWithDraw.add(jpDeposit,BorderLayout.CENTER);
		  
		  //Panel to hold all the small panel above
		  JPanel pRight =new JPanel();
		  pRight.setLayout(new GridLayout(4, 1));
		  pRight.add(jpName);
		  pRight.add(jpBalance);
		  pRight.add(jpInterestRate);
		  pRight.add(jpWithDraw);
		  
		  //set the ratio buttons
		  ButtonGroup bgAccount = new ButtonGroup();
		  bgAccount.add(SavingAccountButton);
		  bgAccount.add(CheckingAccountButton);
		  
		  //Panel to hold the two buttons
		  JPanel jpAccountType =new JPanel();
		  jpAccountType.setLayout(new BorderLayout());
		  jpAccountType.add(CheckingAccountButton,BorderLayout.CENTER);
		  jpAccountType.add(SavingAccountButton,BorderLayout.WEST);
		  
		  //Panel to hold all the other buttons
		  JPanel jpButton1 =new JPanel();
		  jpButton1.add(jbAddCustomer);
		  jpButton1.add(jbSearchCustomer);
		  jpButton1.add(jbSortCustomer);
		  jpButton1.add(jbDelete);
		  
		  JPanel jpButton2 =new JPanel();
		  jpButton2.add(jbAddAccount);
		  jpButton2.add(jbSetAccount);
		  jpButton2.add(jbWithdraw);
		  jpButton2.add(jbDeposit);
		  jpButton2.add(jbView);
		  
		  //panel to place the ratio button and other button
		  JPanel jDown = new JPanel();
		  jDown.setLayout(new GridLayout(3,1));
		  jDown.add(jpAccountType);
		  jDown.add(jpButton1);  
		  jDown.add(jpButton2);
		  
		  //add all the panel to the frame
		  this.add(pLeft,BorderLayout.WEST);
		  this.add(pRight,BorderLayout.CENTER);
		  this.add(jDown, BorderLayout.SOUTH);
		  
		  
		  jbAddCustomer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if (SavingAccountButton.isSelected()){
					flag =true;
				}
				else if(CheckingAccountButton.isSelected()){
					flag =false;
				}
				addCustomer();
				refreshXml();
			}
		  }); 
		  
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
		  
		  jbSearchCustomer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				findCustomer();
				setInfo();
			}
		  });
		  
		  jbSortCustomer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {	
				bank.sortCustomers();
				refreshXml();
			}
		  });
		  
		  jbWithdraw.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				findCustomer();
				setInfo();
				try {
					double withdraw = Double.parseDouble(jtfWithdraw.getText());
					account.withdraw(withdraw);
					refreshXml();
				}
				catch (OverdraftException ex) {
					  JOptionPane.showMessageDialog(getComponent(0), ex.getMessage()); 
				}
			}
		  });
		  
		  jbAddAccount.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				findCustomer();
				addAccount();
				refreshXml();
			}
		  });
		  
		  jbView.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {	
				try {
					showCustomer();
				} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		  });
		  
		  jbDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				deleteCustomer();
				refreshXml();
			}
		  });
		  jbDeposit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				findCustomer();
				setInfo();
				try {
					double deposit = Double.parseDouble(jtfDeposit.getText());
					account.withdraw(deposit);
					refreshXml();
				}
				catch (OverdraftException ex) {
					  JOptionPane.showMessageDialog(getComponent(0), ex.getMessage()); 
				}
				}
		  });
	  }
	  
	  //add a customer
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
	  
	  //add a account
	  public void addAccount(){
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
	  
	  public void findCustomer(){
		  if(jtffName.getText()==null||jtflName.getText()==null)
			  JOptionPane.showMessageDialog(getComponent(0),"please complete the input"); 
		  else{
			  String fName = jtffName.getText();
			  String lName = jtflName.getText();		  
			  int customerFlag = bank.searchCustomers(fName, lName);
			  if(customerFlag!=-1){
				  customer = bank.getCustomer(customerFlag);
				  }
			  }
	  }
	  
	  public void deleteCustomer(){
		  if(jtffName.getText()==""||jtflName.getText()=="")
			  JOptionPane.showMessageDialog(getComponent(0),"please complete the input"); 
		  else{
			  String fName = jtffName.getText();
			  String lName = jtflName.getText();		  
			  int customerFlag = bank.searchCustomers(fName, lName);
			  if(customerFlag!=-1){
				  bank.deleteCustomer(customerFlag);
			  }
			  else{
				  JOptionPane.showMessageDialog(getComponent(0),"please correct the input"); 
			  }
			  }
		  clearText();
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
	
	  public void showCustomer() throws ParserConfigurationException{

		  String s =CustomerReport.generateReport();
		  JOptionPane.showMessageDialog(getComponent(0), s);
	  }
	  
	  public void refreshXml(){
		  writeXml.toWrite();
		  writeXml.toSave();
	  }
	  
	  public void setInfo(){
		  int index = Integer.parseInt(jtfAccountIndex.getText());
		  if(index>customer.getNumOfAccounts()-1){
			  JOptionPane.showMessageDialog(getComponent(0)
					  ,"please enter a index that is available"); 
		  }
		  else{
			  account =customer.getAccount(index);
			  jtfBalance.setText( Double.toString(account.getBalance()) );
			  int accountFlag = customer.getTypeOfAccounts(index);
			  if (accountFlag==1){
				  SavingAccountButton.setSelected(true);
			  }
			  if(accountFlag==2){
				  CheckingAccountButton.setSelected(true);
			  }
			  }
	  }
	  private void setCustomer(int init_sum){
		  bank.setCustomerNumber(init_sum);
	  }
	  
	  public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		    Service frame = new Service();
		    frame.pack();
		    frame.setTitle("Bank");
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
			Point newLocation = new Point(middle.x - (frame.getWidth() / 2), 
			                              middle.y - (frame.getHeight() / 2));
			frame.setLocation(newLocation);
		    frame.setVisible(true);
  
	  }
	  
}
