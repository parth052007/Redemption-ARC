class upi extends payment implements PaymentMethod{
    upi(String customername){
        super(customername);
        }
        @Override
        public void processpayment(double amount){
        pay(amount);
        }
        @Override
        void pay(double amount){
            
            
            System.out.println("payment from upi:"+amount);
        }
    }