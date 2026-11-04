#include<stdio.h>
#include<stdlib.h>
struct Node{
    int data;
    struct Node*next;
};

struct Node*top = NULL;
void push(){
    struct Node*newnode;
    int value;
    newnode = (struct Node*)malloc (sizeof(struct Node));
    printf("enter value:");
    scanf("%d",&value);
    if (newnode == NULL){
        printf("overflow");
    }
    else{
        newnode -> data = value;
        newnode -> next = top;
        top = newnode;
        printf("value inserted sucessfully");
    }
}
void pop(){
    struct Node*temp;
    if(top == NULL){
        printf("stack is empty");
    }
    else{
        temp = top;
        printf("popped elements is :%d",top->data);
        top = top -> next;
    
    }
    free(temp);
}
void peep(){
    if(top == NULL){
        printf("underflow");
    }
    else{
        printf("top elemets is :%d",top -> data);
    }
}
void display(){
    struct Node*ptr;
    if(top == NULL){
        printf("underflow");
    }
    else{
        ptr = top;
        printf("stack");
        while(ptr != NULL){
            printf("%d",ptr->data);
            ptr = ptr->next;
        }
    }
}
int main(){
    int ch;
    do{
        
        printf("_________menu______");
        printf("1.push");
        printf("2.pop");
        printf("3.peep");
        printf("4.display");
        printf("enter choice:");
        scanf("%d",&ch);
        switch(ch){
            case 1:push();
            break;
            case 2 : pop();
            break;
            case 3: peep();
            break;
            case 4: display();
            break;
        }

    }while(ch != 4);
    return 0;
}