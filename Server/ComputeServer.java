import java.net.*;
import java.io.*;

public class ComputeServer {


    public static void main (String args[]) {

        try{
            int serverPort = 6789;
            ServerSocket listenSocket = new ServerSocket(serverPort);

            while(true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
				c.start();
            }

	}
        catch(IOException e) {
            System.out.println("Listen socket:"+e.getMessage());
        }
    }

}

class Connection extends Thread {

    ObjectInputStream in;
    ObjectOutputStream out;
    Socket clientSocket;


    public Connection (Socket aClientSocket) {

        try {
            clientSocket = aClientSocket;
            in = new ObjectInputStream( clientSocket.getInputStream());
            out =new ObjectOutputStream( clientSocket.getOutputStream());
		}
        catch(IOException e) {
            System.out.println("Connection:"+e.getMessage());
        }
    }

    public void run(){

        try {

			while(true){

	            final Object task = in.readObject();

	            if(task instanceof ComputePi){
	           	    final ComputePi taskPi = (ComputePi)task;
	           	    System.out.println("performing a client task of CalculatePi");
	                taskPi.executeTask();
	                out.writeObject(taskPi);
	            }

	            else if(task instanceof ComputePrime){
				final ComputePrime taskPrime = (ComputePrime)task;
				System.out.println("performing a client task of CalculatePrime");
				taskPrime.executeTask();
				out.writeObject(taskPrime);
				}

				else if(task instanceof ComputeGCD){
				final ComputeGCD taskGCD = (ComputeGCD)task;
				System.out.println("performing a client task of CalculateGCD");
				taskGCD.executeTask();
				out.writeObject(taskGCD);
				}
			}

	}
	catch (EOFException e){
            System.out.println("EOF:"+e.getMessage());
	}
	catch(IOException e) {
            System.out.println("readline:"+e.getMessage());
	}
	catch(ClassNotFoundException ex){
		ex.printStackTrace();
	}
	finally{
            try {
				in.close();
				out.close();
				clientSocket.close();
				System.out.println("....Stopped");
            }
            catch (IOException e){
				e.printStackTrace();
            }
	}


    }
}

