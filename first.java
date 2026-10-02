
import java.util.Scanner;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;
public class first{
    Scanner sc = new Scanner(System.in);
    String name;
    int age;
   
    void takeinput(){
        System.out.println("enter name:");
        name=sc.nextLine();
        System.out.println("enter age:");
        sc.nextInt();
    }
    void showinfo(){
        System.out.println("name is:"+name);
        System.out.println("age is:"+age);
    }
    
    public static void main(String args[]){
        first fr = new first();
        fr.takeinput();
        fr.showinfo();
       
    }
}