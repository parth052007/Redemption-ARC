#include<stdio.h>
struct delivery{
    char place[50];
    int distance;

};
int main(){
    struct delivery d[50],temp;
    int n ,i , j ,min;
    printf("enter number of delivery locations:");
    scanf("%d",&n);
    for (i = 0; i<n ;i++){
        printf("enter place:");
        scanf("%s",&d[i].place);

        printf("enter distance from courier hub:");
        scanf("%d",&d[i].distance);
    }
    printf("selection sort passes:");
    for ( i = 0;i<n-1;i++){
        min = i;
        for (j=i+1;j<n;j++){
            if(d[j].distance < d[min].distance)
            min = j;
        }
        temp = d[i];
        d[i]=d[min];
        d[min]=temp;
        printf("pass:%d",i+1);
        for (j=0;j<n;j++)
        printf("%d",d[j].distance);
    printf("\n");
    }
    printf("\n final sorted delivery locations are :\n");
    for(i=0;i<n;i++){
        printf("%s-%d km",d[i].place,d[i].distance);
        if(i<n-1)
        printf("|");
    }
    return 0;

}