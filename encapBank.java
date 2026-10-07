class encapBank{
    String name;
    int balance;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setBal(int balance){
        if(balance < 0){
            System.out.println("invalid ");
        }
    }
    public int getBal(){
        return this.balance;
    }
    public static void main(String args[]){
        encapBank b1 = new encapBank();
        b1.setBal(-1);
        b1.setName("parth");

        System.out.println("name is :"+b1.getName());
        System.out.println("balance:"+b1.getBal());
    }
}