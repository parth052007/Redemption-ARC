#include<stdio.h>
#include<ctype.h>
#define size 5
struct item{
    char name [20];
    int price ;
    int id ;
    int quantity;
};
int front = -1;
int rear = -1;
struct item queue[size];

void insert(){
    if (rear == size -1){
        printf("queue is full");
    }
    else{
        if (front = -1)
        front = 0;
    rear ++;
    printf("enter item id:");
    scanf("%d",&queue[rear].id);

    printf("neter item name:");
    scanf("%s",queue[rear].name);

    printf("enter item price:");
    scanf("%d",queue[rear].price);

    printf("enter itemm  quantiity:");
    scanf("%d",&queue[rear].quantity);
    printf("item inserted sucessfully");
    }
}
    void delete(){
        if(front ==-1 || front> rear){
            printf("queue is empty");
        }
        else{
            printf("item deleted is:%s",queue[front].name);
            front ++;
            if(front>rear){
                front =-1;
                rear =-1;
            }
        }
    }

    void display(){
        int i;
        if (front =-1 || front >rear){
            printf("queue is empty");

        }
        else{
            for(i = front ;i<= rear;i++){
            printf("item are :");
            printf("id is :%d",queue[i].id);
            printf("name is :%s",queue[i].name);
            printf("price is :%d",queue[i].price);
            printf("quantity is :%d",queue[i].quantity);
        }
    }}

    void totalbill(){
        int i;
        int total = 0;
        if(front == -1 || front >rear){
            printf("queue is empty");

        }
        else{
            for ( i = front ; i< rear ; i++){
                total= total + (queue[i].price*queue[i].quantity );
            printf("total bill is :%d",total);

            }
        }
    }

int main(){
    int ch;
    do{
        printf("menu");
        printf("1.push");
        printf("2.pop");
        printf("3.display");
        printf("4.exit");
        printf("enter choice:");
        scanf("%d",&ch);
        switch(ch){
            case 1: insert();
            break;
            case 2: delete();
            break;
            case 3: display();
            break;
            default:
            printf("invlaid choice");
        }
    } while(ch !=3);
    return 0;

}    

