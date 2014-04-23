package domain;




public class Customer{
	private String firstName;
	private String lastName;
	private Account[] account;
	int numberOfAccounts ;
	final int sizeOfAccounts =5;
	
	/*construct a customer with a specified name and account*/
	public Customer(String fName ,String lName){
		firstName =fName;
		lastName =lName;
		numberOfAccounts =0;
		account =new Account[sizeOfAccounts];
	}
	
	/*return Customer's firstName*/
	public String getFirstName(){
		return firstName;
	}
	
	/*return Customer's lastName*/
	public String getLastName(){
		return lastName;
	}
	
	/*assign the account attribute */
	public void addAccount(Account acct){
		account[numberOfAccounts]=acct;
		numberOfAccounts++;
	}
	
	/*retrieve the account attribute */
	public Account getAccount(int index){

		return account[index];
	}
	
	//return the number of the account of one customer
	public int getNumOfAccounts(){
		return numberOfAccounts;
	}
	
	//return the customer's account type by the index
	public int getTypeOfAccounts(int index){
		if(account[index] instanceof SavingsAccount)
			return 1;
		else {
			if(account[index] instanceof CheckingAccount)
				return 2;
			else return 3;
		}
	}
}
