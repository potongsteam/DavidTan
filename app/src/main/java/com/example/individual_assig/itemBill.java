package com.example.individual_assig;

public class itemBill {
    package com.example.individual_assig;

    public class itemBill {

        private int id;
        private double TotalAmount;
        private int numOfPeople;
        private String breakdown;
        private String CustomShare;

        public itemBill(int id, double TotalAmount, int numOfPeople, String breakdown,
                        String CustomShare)
        {
            this.id = id;
            this.TotalAmount = TotalAmount;
            this.numOfPeople = numOfPeople;
            this.breakdown = breakdown;
            this.CustomShare = CustomShare;
        }

        public int getId()
        {
            return id;
        }

        public double getTotalAmount()
        {
            return TotalAmount;
        }

        public int getNumOfPeople()
        {
            return numOfPeople;
        }
        public String getBreakdown()
        {
            return breakdown;
        }

        public String getCustomShare()
        {
            return CustomShare;
        }



    }

}
