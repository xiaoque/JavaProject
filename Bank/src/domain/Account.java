package domain;

public class Account {
	protected double balance;
	
	/* construct an account with a specified balance*/
	public Account (double init_balance){
		balance =init_balance;
	}
	/*return account*/
	public double getBalance(){
		return balance;
	}
	/* add the amount parameter to the current balance */
	public boolean deposit(double amount){
		balance = balance + amount;
		return true;
	}
	/*remove the amount parameter from the current balance */
	public void withdraw(double amount) throws OverdraftException{
		if(amount >0||amount ==0){
			if(amount<balance){	
				balance =balance -amount;
			}
			else{
				throw new OverdraftException("Insufficient funds",(amount-balance));
			}
		}
		else throw new OverdraftException("Insufficient funds",-amount);
	}


}
