#include <stdio.h>

int a[20][20], visited[20], n;

void DFS(int v)
{
    int i;

    printf("%d", v + 1);
    visited[v] = 1;

    for(i = 0; i < n; i++)
    {
        if(a[v][i] == 1 && visited[i] == 0)
        {
            printf(" -> ");
            DFS(i);
        }
    }
}

void BFS(int start)
{
    int queue[20], front = 0, rear = 0;
    int i, v;

    visited[start] = 1;
    queue[rear++] = start;

    while(front < rear)
    {
        v = queue[front++];

        printf("%d", v + 1);

        for(i = 0; i < n; i++)
        {
            if(a[v][i] == 1 && visited[i] == 0)
            {
                visited[i] = 1;
                queue[rear++] = i;
            }
        }

        if(front < rear)
            printf(" -> ");
    }
}

int main()
{
    int i, j, start, choice;

    printf("Enter number of locations: ");
    scanf("%d", &n);

    printf("Enter adjacency matrix:\n");

    for(i = 0; i < n; i++)
    {
        for(j = 0; j < n; j++)
        {
            scanf("%d", &a[i][j]);
        }
    }

    printf("Enter starting location (1 to %d): ", n);
    scanf("%d", &start);

    start--;

    printf("\n1. BFS");
    printf("\n2. DFS");

    printf("\nEnter your choice: ");
    scanf("%d", &choice);

    for(i = 0; i < n; i++)
        visited[i] = 0;

    if(choice == 1)
    {
        printf("\nBFS Route: ");
        BFS(start);
    }
    else if(choice == 2)
    {
        printf("\nDFS Route: ");
        DFS(start);
    }
    else
    {
        printf("\nInvalid Choice");
    }

    return 0;
}