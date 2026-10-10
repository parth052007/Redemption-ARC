class CardPayment extends payment implements PaymentMethod{

CardPayment(String customername){
    super(customername);
}
@Override
public void processpayment(double amount){
    pay(amount);
}
@Override
void pay(double amount){
    System.out.println("payment form card is:"+amount);
    }
}
