package org.nwgreens.savings.savingsandblown;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by greenj on 11/6/15.
 */
public class BanksResponse {
    private Bank [] banks;
    private String userName;

    public String [] GetBanks(){
       String [] returnValue = new String[banks.length];
        for(int i = 0; i < banks.length; i++){
            returnValue[i] = banks[i].bankName;
        }
        return returnValue;
    }

    public Bank GetBankDetail(String bankName){
        for(int i= 0; i < banks.length; i++){
            if(banks[i].bankName.equals( bankName)){
                return banks[i];
            }
        }
        return null;
    }

    public class Bank{
        private String bankName;
        private String BudgetStartDate;
        private String BudgetEndDate;

        public String getBudgetStartDate() {
            return BudgetStartDate;
        }

        public String getBudgetStartEndDate() {
            return BudgetEndDate;
        }
    }
}
