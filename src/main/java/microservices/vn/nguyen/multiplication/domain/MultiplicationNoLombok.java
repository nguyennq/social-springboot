package microservices.vn.nguyen.multiplication.domain;

/**
 * Created by nals on 1/3/18.
 */
public class MultiplicationNoLombok {
    private int factorNumber1;
    private int factorNumber2;
    private int resultMulTwoFactor;

    public MultiplicationNoLombok(int factorNumber1, int factorNumber2) {
        this.factorNumber1 = factorNumber1;
        this.factorNumber2 = factorNumber2;
        this.resultMulTwoFactor = factorNumber1 * factorNumber2;
    }

    public int getFactorNumber1() {
        return factorNumber1;
    }

    public void setFactorNumber1(int factorNumber1) {
        this.factorNumber1 = factorNumber1;
    }

    public int getFactorNumber2() {
        return factorNumber2;
    }

    public void setFactorNumber2(int factorNumber2) {
        this.factorNumber2 = factorNumber2;
    }

    public int getResultMulTwoFactor() {
        return resultMulTwoFactor;
    }

    @Override
    public String toString() {
        return "Multiplication{" +
                "factorNumber1=" + factorNumber1 +
                ", factorNumber2=" + factorNumber2 +
                ", resultMulTwoFactor=" + resultMulTwoFactor +
                '}';
    }
}
