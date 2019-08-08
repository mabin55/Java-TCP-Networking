import java.io.Serializable;


public class ComputeGCD implements Task, Serializable {

    String greatestCommonDivisor;
    long firstInteger;
    long secondInteger;
	long computeGCD;

    public ComputeGCD(){
    }

    public ComputeGCD(long firstInteger, long secondInteger){
        this.firstInteger = firstInteger;
        this.secondInteger = secondInteger;
    }
    @Override
    public void executeTask() {
		CalculateGCD calculateCommonDivisor = new CalculateGCD();
		computeGCD = calculateCommonDivisor.CalculateGCD(firstInteger, secondInteger);
		greatestCommonDivisor = Long.toString(computeGCD);
    }

    @Override
    public Object getResult() {
       return greatestCommonDivisor;
    }

}
