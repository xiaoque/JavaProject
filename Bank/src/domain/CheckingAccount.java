package domain;

public class CheckingAccount extends Account{
	private double overdraftProtection;
	public CheckingAccount(double balance) {
		super(balance);
		overdraftProtection =0;
	}
	//construct a account with a specified balance and protection
	public CheckingAccount (double balance ,double protect){
		super(balance);
		overdraftProtection = protect;
	}
	
	//withdraw the balance 
	public void withdraw(double amount) throws OverdraftException{
		if(overdraftProtection==0){
			if(balance -amount<0)
				throw new OverdraftException("No overdraft protection",amount-balance);
			else{
				balance =balance-amount;
			}
		}
		else{
			if(balance+ overdraftProtection -amount <0){
				throw new OverdraftException("Insufficient funds for overdraft protection", amount);
			}
			else{
				balance = balance -amount;
				if(balance<0){
					overdraftProtection += balance ;
					balance =0;
				}
			}
		}
	}
	
	//return value of overdraftProtection
	public double getOverdraftProtection(){
		return overdraftProtection;
	}
}
