
#include <stdio.h>
#include <string.h>

struct Book {
    char title[50];
    char author[50];
    char isbn[50];
    int quantity;
};

int binarysearch(struct Book book[], int n, char target[]) {

    int low = 0;
    int high = n - 1;
    int mid;
    int comparison = 0;

    while (low <= high) {

        mid = (low + high) / 2;
        comparison++;

        if (strcmp(book[mid].title, target) == 0) {

            printf("\nBook Found!\n");
            printf("Position: %d\n", mid + 1);
            printf("Book Title: %s\n", book[mid].title);
            printf("Book Author: %s\n", book[mid].author);
            printf("Book ISBN: %s\n", book[mid].isbn);
            printf("Book Quantity: %d\n", book[mid].quantity);
            printf("Comparisons: %d\n", comparison);

            return mid;
        }

        else if (strcmp(book[mid].title, target) < 0) {
            low = mid + 1;
        }

        else {
            high = mid - 1;
        }
    }

    printf("\nBook Not Found!\n");
    printf("Comparisons: %d\n", comparison);

    return -1;
}

int main() {

    struct Book book[100];
    char target[50];
    int i, n;

    printf("Enter number of books: ");
    scanf("%d", &n);

    printf("\nEnter book details in alphabetical order:\n");

    for (i = 0; i < n; i++) {

        printf("\nBook %d\n", i + 1);

        printf("Title: ");
        scanf(" %49[^\n]", book[i].title);

        printf("Author: ");
        scanf(" %49[^\n]", book[i].author);

        printf("ISBN: ");
        scanf("%49s", book[i].isbn);

        printf("Quantity: ");
        scanf("%d", &book[i].quantity);
    }

    printf("\nEnter book title to search: ");
    scanf(" %49[^\n]", target);

    binarysearch(book, n, target);

    return 0;
}
