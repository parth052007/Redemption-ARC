#include<stdio.h>
#include<stdlib.h>
int size;
int top = -1;
struct box{
    char color[20];
    char dimension[20];
    char weight;

};
struct box stack[50];
void push(){
    if(top == size-1){
        printf("stack is full");
    }
    else{
        top ++;
    printf("enter color of box:");
    scanf("%d",stack[top].color);
    printf("enter dimension of box:");
    scanf("%d",stack[top].dimension);
    printf("enter weight of box:");
    scanf("%d",stack[top].weight);
    printf("box added sucesfully");

    }
}
    void peep(){
        if(top == -1){
        printf("stack is empty");
        }
        else{
            printf("box color is:",stack[top].color);
            printf("bx weight is :",stack[top].weight);
            printf("box dimension is :",stack[top].dimension);
        }

    }
    void pop(){
        if(top==-1){
            printf("stack is empty");
        }
        else{
            printf("box removed sucessfully");
            printf("removed box color is :",stack[top].color);
            printf("removed box  dimension is ",stack[top].dimension);
            printf("remved box weight is :",stack[top].weight);
            top--;
        }
    }
    void display()
{
    int i ;
    if (top ==-1){
        printf("stack is empty");
    }
    else{
        printf("box is ");
        for (i=0;i>=0;i--){
            printf("box is %d\n",i+1);
            printf("box color is :",stack[i].color);
            printf("box dimensioon is :",stack[i].dimension);
            printf("box weight is :",stack[i].weight);
        }
    }
}
int main(){
    int ch;
    printf("enter size of stack:");
    scanf("%d",&size);
    while(1){
        printf("\n menu\n");
        printf("\n 1.push");
        printf("\n 2.pop");
        printf("\n3.peep");
        printf("\n 4.display");
        printf("\n5.exut");

        printf("enter choice:");
        scanf("%d",&ch);
        switch(ch){
            case 1:
            push();
            break;
            case 2:
            pop();
            break;
            case 3:
            peep();
            break;
            case 4:
            display();
            break;
            case 5:
            exit(0);
            break;
            default:
            printf("invalid choice");
        }
    }
    return 0;
}