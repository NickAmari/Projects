/*
Author: Nicholas Amari
Date: 3/15/2021
Description: file input to art file output
Usage:
*/
#include <stdio.h>
#include <string.h>

char str[5];
const char* hexToArt(char given) {
        switch (given) {
                case '0': ;
                 str[0] = '-';
                 str[1] = '-';
                 str[2] = '-';
                 str[3] = '-';
                 break;
                case '1': ;
                 str[0] = '-';
                 str[1] = '-';
                 str[2] = '-';
                 str[3] = '#';
                 break;
                case '2': ;
                 str[0] = '-';
                 str[1] = '-';
                 str[2] = '#';
                 str[3] = '-';
                 break;
                case '3': ;
                 str[0] = '-';
                 str[1] = '-';
                 str[2] = '#';
                 str[3] = '#';
                 break;
                case '4': ;
                 str[0] = '-';
                 str[1] = '#';
                 str[2] = '-';
                 str[3] = '-';
                 break;
                case '5': ;
                 str[0] = '-';
                 str[1] = '#';
                 str[2] = '-';
                 str[3] = '#';
                 break;
                case '6': ;
                 str[0] = '-';
                 str[1] = '#';
                 str[2] = '#';
                 str[3] = '-';
                 break;
                case '7': ;
                 str[0] = '-';
                 str[1] = '#';
                 str[2] = '#';
                 str[3] = '#';
                 break;
                case '8': ;
                 str[0] = '#';
                 str[1] = '-';
                 str[2] = '-';
				 str[3] = '-';
                 break;
                case '9': ;
                 str[0] = '#';
                 str[1] = '-';
                 str[2] = '-';
                 str[3] = '#';
                 break;
                case 'A': ;
                 str[0] = '#';
                 str[1] = '-';
                 str[2] = '#';
                 str[3] = '-';
                 break;
                case 'B': ;
                 str[0] = '#';
                 str[1] = '-';
                 str[2] = '#';
                 str[3] = '#';
                 break;
                case 'C': ;
                 str[0] = '#';
                 str[1] = '#';
                 str[2] = '-';
                 str[3] = '-';
                 break;
                case 'D': ;
                 str[0] = '#';
                 str[1] = '#';
                 str[2] = '-';
                 str[3] = '#';
                 break;
                case 'E': ;
                 str[0] = '#';
                 str[1] = '#';
                 str[2] = '#';
                 str[3] = '-';
                 break;
                case 'F': ;
                 str[0] = '#';
                 str[1] = '#';
                 str[2] = '#';
                 str[3] = '#';
                 break;
                default: ;
                 str[0] = 'e';
                 str[1] = 'e';
                 str[2] = 'e';
                 str[3] = 'e';
                 break;
        }
        return str;

}
int main(int argc, char* argv[]) {

        char* inputFileName = argv[1];
        char* paramTwo = argv[2];
        char* outputFileName = argv[3];

        char currentChar;
		
        FILE *fptr;
        fptr = fopen(inputFileName, "r");
        FILE *outFptr;
        outFptr = fopen(outputFileName, "w");

        if (strcmp(paramTwo, "-a") == 0) {
                while (1) {
                        currentChar = fgetc(fptr);
                        if (currentChar == EOF) {
                                break;
                        }
                        if (currentChar != '\n') {
                                fprintf(outFptr, "%s", hexToArt(currentChar));
                        }
                        else {
                                fprintf(outFptr, "\n");
                        }
                }
        }
        else {
                while (1) {
                        currentChar = fgetc(fptr);
                        if (currentChar == EOF) {
                                break;
                        }
                        if (currentChar != '\n') {
                                switch (currentChar) {
                                        case '-': ;
                                         str[0] = '#';
                                         break;
                                        case '#': ;
                                         str[0] = '-';
                                         break;
                                        default: ;
                                         str[0] = 'E';
                                         break;
                                }
                                fprintf(outFptr, "%c", str[0]);
                        }
                        else {
                                fprintf(outFptr, "\n");
                        }
                }
        }
        fclose(fptr);
        fclose(outFptr);
        return(0);

}