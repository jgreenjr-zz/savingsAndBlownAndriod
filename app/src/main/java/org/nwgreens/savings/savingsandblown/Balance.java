package org.nwgreens.savings.savingsandblown;

public class Balance{
    private double ActualBalance;
    private double ClearedBalance;

    public double getActualBalance() {
        return ActualBalance;
    }

    public void setActualBalance(double actualBalance) {
        ActualBalance = actualBalance;
    }

    public double getClearedBalance() {
        return ClearedBalance;
    }

    public void setClearedBalance(double clearedBalance) {
        ClearedBalance = clearedBalance;
    }

    public double getBalance(int displayMode) {
        switch(displayMode){
            case R.id.currentBalance:
                return getClearedBalance();
            default:
                return getActualBalance();

        }
    }
}
