

import java.io.Serializable;
import java.util.ArrayList;


public class ComputePrime implements Task, Serializable {

     String primeNumbers;
     private int number;
     public ComputePrime(){

     }

     public ComputePrime(int number){
         this.number=number;
     }

    @Override
    public void executeTask() {
        CalculatePrime calculatePrime = new CalculatePrime();
        boolean check;
        ArrayList<Integer> primeNumberList = new ArrayList<Integer>();

        for(int i=0; i<=number; i++){
			check = calculatePrime.isPrime(i);
			if(check){
				primeNumberList.add(i);
			}
		}
		primeNumbers = primeNumberList.toString();

    }

    @Override
    public Object getResult() {
       return primeNumbers;
    }
}
