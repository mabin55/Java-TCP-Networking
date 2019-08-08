

import java.io.Serializable;
import java.math.BigDecimal;

public class ComputePi implements Task, Serializable {

    String pi;
    int digits;
    BigDecimal computePi;

    public ComputePi(){

    }

    public ComputePi(int digits){
        this.digits= digits;
    }

    @Override
    public void executeTask() {
        CalculatePi calculatePi = new CalculatePi(digits);
        computePi= calculatePi.execute();
        pi=computePi.toString();
    }

    @Override
    public Object getResult() {
       return pi;
    }


}
