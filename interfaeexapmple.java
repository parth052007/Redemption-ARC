interface Animal{
    void sound();
}
class dog implements Animal{
    public void sound(){
        System.out.println("bhawww bhaww");
    }
}
class cat implements Animal{
    public void sound(){
        System.out.println("mewooo");
    }
}
public class interfaeexapmple{
    public static void main(String [] args){
        dog d = new dog();
        d.sound();
        cat c = new cat();
        c.sound();
    }
}