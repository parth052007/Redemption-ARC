class employee{
    int id;
    int salary;
    employee(int id , int salary){
        this.id = id;
        this.salary = salary;

    }
    void show(){
        System.out.println("id:"+id);
        System.out.println("salary:"+salary);
    }
}
class manager extends employee{
    int bonus;
    String name;
    manager(int id,int salary,int bonus, String name){
        super(id,salary);
        this.bonus= bonus;
        this.name= name;

    }
    void display(){
        show();
        System.out.println("bonus:"+bonus);
        System.out.println("name:"+name);
    }


}
public class employee1{
public static void main (String[]args){
    manager m = new manager(101,5000,1000,"parth");
    m.display();
}
}

