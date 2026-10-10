abstract class payment{
    protected  String customername;
    payment(String customername) // why we use construtor becoz customer name is want to acces in every class by using inheritance 
    {
        this.customername=customername;
    }
    abstract void pay(double amount);
    void show(){
        System.out.println("customer name:"+customername);
    }
}
class upiid extends payment{
    upiid(String customername){
        super(customername);
    }
    void pay(double amount){
        System.out.println("payment fromupi is:"+amount);
    }
}
class cash extends payment{
    cash(String customername){
    super(customername);
    } 
    void pay(double amount){
        System.out.println("payment from cash is:"+amount);
        
    }
}
public class abstraction{
    public static void main(String [] args){
        upiid u = new upiid("parth");
        u.show();
        u.pay(100);

        cash c  = new cash("shreyash");
        c.show();
        c.pay(200);
    }
}