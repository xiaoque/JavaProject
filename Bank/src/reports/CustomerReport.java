package reports;

import domain.*;
import java.text.NumberFormat;

public class CustomerReport {

	//return a String that conclude all the information of the bank
  public static String generateReport() {
    NumberFormat currency_format = NumberFormat.getCurrencyInstance();

    Bank         bank = Bank.getBank();
    Customer     customer;
    bank.sortCustomers();
    String s="";
    s =s+"\t\t\tCUSTOMERS REPORT\n";
    s =s+"\t\t\t================\n";

    for ( int cust_idx = 0; cust_idx < bank.getNumOfCustomers(); cust_idx++ ) {
      customer = bank.getCustomer(cust_idx);
      s =s+'\n'+'\n'+"Customer: "+ customer.getLastName() + ", "+ customer.getFirstName()+'\n';

      for ( int acct_idx = 0; acct_idx < customer.getNumOfAccounts(); acct_idx++ ) {
		Account account = customer.getAccount(acct_idx);
		s  =s+"account_type:";

		// Determine the account type
		if ( account instanceof SavingsAccount ) {
		  s = s+"Savings Account";
		} else if ( account instanceof CheckingAccount ) {
			s = s+ "Checking Account";
		} else {
			s = s+ "Unknown Account Type";
		}
	
		s = s+'\n'+"current balance is "
				 + currency_format.format(account.getBalance())+'\n';
	      }
	    }
    
    return s;
	  }

}
