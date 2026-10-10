abstract class payment{
    protected String customername;
    payment(String customername){
        this.customername = customername;
    }
    abstract void pay(double amount);
    void display(){
        System.out.println("customer name:"+ customername);
    }
}


        




    