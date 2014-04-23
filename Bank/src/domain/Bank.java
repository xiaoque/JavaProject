package domain;

public class Bank {
	int sizeOfCustomers;
	Customer[] customers;
	int numberOfCustomers ;
	private static Bank bank = new Bank();
	
	//construct a bank with a number of customers
	private Bank(){
	//	customers = new Customer[sizeOfCustomers];
		numberOfCustomers =0;
	}
	
	//add a customer
	public void addCustomer (String fName,String lName){
		customers[numberOfCustomers] = new Customer(fName ,lName);
		numberOfCustomers ++;
	}
	
	//get the number of customers
	public int getNumOfCustomers(){
		return numberOfCustomers;
	}
	
	//get the instance of one customer by index
	public Customer getCustomer(int index){
		return customers[index];
	}
	
	//return the only instance of Bank
	public static Bank getBank(){
		return bank;
	}
	
	//sort the customer by their name
	public void sortCustomers(){
		int flag =0;
		for(int i =0;i<numberOfCustomers;i++){
			flag=0;
			for(int j=0;j<numberOfCustomers-1;j++){
				if( (  (customers[j].getLastName()).compareTo(customers[j+1].getLastName()) ) >0 ){
					Customer temp = customers[j];
					customers[j] =customers[j+1];
					customers[j+1] = temp;
					flag=1;
				}
				else {
					if((customers[j].getLastName().compareTo(customers[j+1].getLastName()))==0){
						if ((customers[j].getFirstName().compareTo(customers[j+1].getFirstName()))>0){
							Customer temp = customers[j];
							customers[j] =customers[j+1];
							customers[j+1] = temp;
							flag=1;
						}
					}
				}
			}
			if(flag==0) break;
		}
	}
	
	//search a certain customer using his name
	public int searchCustomers (String fName ,String lName){
		int index =0;
		for(;index<numberOfCustomers;index++){
			if (fName.equals(customers[index].getFirstName())
					&& lName.equals(customers[index].getLastName()) )
				break;
			
		}
		if(index<numberOfCustomers-1)
		return index;
		else {
			if(fName.equals(customers[index].getFirstName())
				&& lName.equals(customers[index].getLastName()))
				return index;
			else return -1;
		}
	}
	
	//remove a customer by index
	public void deleteCustomer(int index){
		for(;index<numberOfCustomers-2;index++){
			customers[index]=customers[index+1];
		}
		numberOfCustomers--;
	}
	
	public void setCustomerNumber(int sum){
		sizeOfCustomers =sum;
		customers  = new Customer[sizeOfCustomers]; 
	}
}

