//package Compute;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import javafx.concurrent.Task;

public class ComputeClient{

    public static void main(String args[]) throws ClassNotFoundException{

        Scanner input = new Scanner(System.in);

        Socket s = null;
            try{
                int option = 0;

                int numOfDigits;
                int numOfPrime;
                long firstInteger;
                long secondInteger;

                int serverPort = 6789;

		s = new Socket("localhost", serverPort);

		ObjectInputStream in = null;
		ObjectOutputStream out =null;

		out =new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream( s.getInputStream());

                while(option == 0){

					System.out.println();
                    System.out.println("***************************************");
                    System.out.println("Calculate Pi -------------------------1");
                    System.out.println("Calculate Primes ---------------------2");
                    System.out.println("Calculate Greatest Common Divisor ----3");
                    System.out.println("Exit ---------------------------------4");
                    System.out.println("***************************************");

                    System.out.println();
                    System.out.print("Enter your option: ");
                    option= input.nextInt();

                    switch(option){
                        case 1:{
                            System.out.println();
                            System.out.print("Enter the number of digits after the decimal point of Pi: ");
                            numOfDigits = input.nextInt();

                            ComputePi taskPi = new ComputePi(numOfDigits);
                            out.writeObject(taskPi);

                            ComputePi finalTaskPi =(ComputePi) in.readObject();
                            System.out.println(finalTaskPi.getResult());

                            option = 0;
                            break;
                        }

                        case 2:{
							System.out.println();
                            System.out.print("Enter the number till which the prime numbres are to be calculated: ");
                            numOfPrime = input.nextInt();

                            ComputePrime taskPrime = new ComputePrime(numOfPrime);
                            out.writeObject(taskPrime);

							ComputePrime finalTaskPrime = (ComputePrime) in.readObject();
							String primeNumbers ;
							primeNumbers =(String) finalTaskPrime.getResult();
                            int count =0;
                            boolean isPreviousDigit = false;

                            for(int i=0; i<primeNumbers.length(); i++){
								if(Character.isDigit(primeNumbers.charAt(i))){
									if(!isPreviousDigit){
										count++;
										isPreviousDigit = true;
									}
								}
								else {
									isPreviousDigit = false;
								}
							}
                            System.out.println("The number of primes is:" + count + ", and they are: " + primeNumbers );

                            option = 0;
                            break;
                        }

                        case 3:{
							System.out.println();
                            System.out.print("Enter the first integer: ");
                            firstInteger = input.nextLong();
                            System.out.println();
                            System.out.print("Enter the second integer: ");
                            secondInteger = input.nextLong();

                            ComputeGCD taskGCD = new ComputeGCD(firstInteger, secondInteger);
                            out.writeObject(taskGCD);

                            ComputeGCD finalTaskGCD = (ComputeGCD) in.readObject();
                            System.out.println("The greatest common divisor of "+firstInteger+ " and "+secondInteger+" is "+ finalTaskGCD.getResult());

                            option = 0;
                            break;
                        }

                        case 4:{
                            System.out.println("Thankyou for using the application. ");
                            System.exit(0);
                        }
                    }
                }


		}
		catch (UnknownHostException e){
                    System.out.println("Socket:"+e.getMessage());
		}
                catch (EOFException e){
                    System.out.println("EOF:"+e.getMessage());
		}
                catch (IOException e){
                    System.out.println("readline:"+e.getMessage());
		}
                catch(ClassNotFoundException ex){
                     ex.printStackTrace();
		}
                finally {
                    if(s!=null)
                    try {
                        s.close();
                    }
                    catch (IOException e){
			System.out.println("close:"+e.getMessage());
                    }
		}
     }

}
