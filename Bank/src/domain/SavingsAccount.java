package domain;

public class SavingsAccount extends Account{
	private double interestRate;
	public SavingsAccount(double balance ,double interest_rate) {
		super(balance);
		setInterestRate(interest_rate);	
	}
	//return the interestRate
	public double getInterestRate() {
		return interestRate;
	}
	
	//set the interestRate
	private void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
}
