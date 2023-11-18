/*
Author: Nicholas Amari
Date: 31/3/2021
Description: Using dynamically allocated arrays to make a carpet fractal maker
Usage:
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int width, height, xMin, xMax, yMin, yMax, numParticles, maxLife, waterLine;
int i, j;
void infoGathering() {
	printf("Enter a width and height\n");
	scanf("%d %d", &width, &height);

	//Getting mins and maxes and making sure they are proper values
	printf("Enter Xminimum and Xmaximum\n");
	scanf("%d %d", &xMin, &xMax);
	if (xMin < 0 || xMin > xMax || xMax > width) {	
		printf("Make sure your minimum is smaller than your maximum, and your minimum is greater than 0\n");
		printf("Also make sure your max is less than the width you have selected and try again\n");
		scanf("%d %d", &xMin, &xMax);
	}
	printf("Enter Yminimum and Ymaximum\n");
	scanf("%d %d", &yMin, &yMax);
	if (yMin < 0 || yMin > yMax || yMax > height) {	
		printf("Make sure your minimum is smaller than your maximum, and your minimum is greater than 0\n");
		printf("Also make sure your max is less than the height you have selected and try again\n");
		scanf("%d %d", &yMin, &yMax);
	}
	//Getting numParticles, maxLife, and waterLine	
	printf("Enter the number of particles you wish to drop.\n");
	scanf("%d", &numParticles);

	printf("Enter the max-life of the particles.\n");
	scanf("%d", &maxLife);

	printf("Enter a value for the water-line between 40 and 200.\n");
	scanf("%d", &waterLine);
	if (waterLine < 40 || waterLine > 200) {
		printf("Please enter a value for the water-line between 40 and 200.\n");
		scanf("%d", &waterLine);
	}
}

void dropRoll(int **arr, int partLife) {

	//finding random x and y location to drop at
	int x = (rand() % (xMax - xMin + 1))+ xMin;//column, j
	int y = (rand() % (yMax - yMin + 1))+ yMin;//row, i
	arr[x][y] = arr[x][y] + 1;
	//picking a random spot:
	while (partLife != 0) {
		int randomInt = (rand() % (8 - 1 + 1)) + 1;
		int spotToMove = 0;
		//deciding where to roll
		switch (randomInt) {
			case 1: ;
				if ( ((x-1) >= 0) && ((y+1) >= 0) ) {
					if ( ((x-1) < width) && ((y+1) < height) ) {
						if ( (arr[x-1][y+1] <= arr[x][y]) ) {
							spotToMove = 1;}}}break;
			case 2: ;
				if ( ((x) >= 0) && ((y+1) >= 0) ) {
					if ( ((x) < width) && ((y+1) < height) ) {
						if ( (arr[x][y+1] <= arr[x][y]) ) {
							spotToMove = 2;}}}break;
			case 3: ;
				if ( ((x+1) >= 0) && ((y+1) >= 0) ) { 
					if ( ((x+1) < width) && ((y+1) < height) ) {
						if ( (arr[x+1][y+1] <= arr[x][y]) ) {
							spotToMove = 3;}}}break;
			case 4: ;
				if ( ((x-1) >= 0) && ((y) >= 0) ) { 
					if ( ((x-1) < width) && ((y) < height) ) {
						if ( (arr[x-1][y] <= arr[x][y]) ) { 
							spotToMove = 4;}}}break;
			case 5: ;
				if ( ((x+1) >= 0) && ((y) >= 0) ) { 
					if ( ((x+1) < width) && ((y) < height) ) {
						if ( (arr[x+1][y] <= arr[x][y]) ) {
							spotToMove = 5;}}}break;
			case 6: ;
				if ( ((x-1) >= 0) && ((y-1) >= 0) ) {
					if ( ((x-1) < width) && ((y-1) < height) ) {
						if ( (arr[x-1][y-1] <= arr[x][y]) ) {
							spotToMove = 6;}}}break;
			case 7: ;
				if ( ((x) >= 0) && ((y-1) >= 0) ) {
					if ( ((x) < width) && ((y-1) < height) ) { 
						if ( (arr[x][y-1] <= arr[x][y]) ) {
							spotToMove = 7;}}}break;
			default: ;
				if ( ((x+1) >= 0) && ((y-1) >= 0) ) {
					if ( ((x+1) < width) && ((y-1) < height) ) {
						if ( (arr[x+1][y-1] <= arr[x][y]) && ((x+1) >= 0) && ((y-1) >= 0) && ((x+1) < width) && ((y-1) < height)) {
							spotToMove = 8;}}}break;//9 on numpad
		}
		//now spotToMove's value represents which direction to roll to
		switch (spotToMove) {
			case 1: ;
				x = x - 1;
				y = y + 1;
				arr[x][y] = arr[x][y] + 1;
				partLife = partLife - 1;
				break;
			case 2: ;
				y = y + 1;
				arr[x][y] = arr[x][y] + 1;
				partLife = partLife - 1;
				break;
			case 3: ;
				x = x + 1;
				y = y + 1;
				arr[x][y] = arr[x][y] + 1;
				partLife = partLife - 1;
				break;
			case 4: ;
				x = x - 1;
				arr[x][y] = arr[x][y] + 1;
				partLife = partLife - 1;
				break;
			case 5: ;
				x = x + 1;
				arr[x][y] = arr[x][y] + 1;
				partLife = partLife - 1;
				break;
			case 6: ;
				x = x - 1;
				y = y - 1;
				arr[x][y] = arr[x][y] + 1;
				partLife = partLife - 1;
				break;
			case 7: ;
				y = y - 1;
				arr[x][y] = arr[x][y] + 1;
				partLife = partLife - 1;
				break;
			case 8: ;
				x = x + 1;
				y = y - 1;
				arr[x][y] = arr[x][y] + 1;
				partLife = partLife - 1;
				break;
			default: ;
				partLife = 0;
				break;
		
		}
		//now the particle has dropped, rolled to a new location, and decremented its life
		//x and y are = to the roll location
	}
}

int findMax(int** arr, int width, int height) {
	//finding highest value in array
	int Value = 0;
	for (i = 0; i < height; i++) {
		for (j = 0; j < width; j++) {
			if (arr[i][j] > Value) {
				Value = arr[i][j];
			}
		}
	}
	return Value;
}

void normalizeMap(int** arr, int width, int height, int maxVal) {
	for (i = 0; i < height; i++) {
		for (j = 0; j < width; j++) {
			if (arr[i][j] > 0) {
				double tempValue;
				tempValue = (double) arr[i][j] / (double) maxVal;
				arr[i][j] = tempValue * 255;
			}
		}
	}	

}

int main (int argc, char* argv[]) {
	
	infoGathering();

	FILE *fptr;
	fptr = fopen("Island.txt", "w");
	//Generating an array of 0 of the given width and height
	int *array[height];
	for (i = 0; i < height; i++) {
		array[i] = (int *)malloc( width * sizeof(int));
	}

	for (i = 0; i < height; i++) { 
		for (j = 0; j < width; j++) {
			array[i][j] = 0;
		}
	}

	//drop a particle and itll roll, keep dropping particles until there are none left.
	int k;
	for (k = numParticles; k > 0; k--) {
		dropRoll(array, maxLife);
	}
	
	//Locate max value
	int maxValue = findMax(array, width, height);
	
	//normalizing the values in the arry
	normalizeMap(array, width, height, maxValue);
	
	//creating 2D character array	
	char *newArray[height];
	for (i = 0; i < height; i++) {
		newArray[i] = (char *)malloc( width * sizeof(char));
	}
	//setting new array values to art
	for (i = 0; i < height; i++) { 
		for (j = 0; j < width; j++) {
			int landZone = 255 - waterLine;
			if (array[i][j] < (waterLine / 2)) {
				newArray[i][j] = '#';
			}
			else if ((array[i][j] > (waterLine / 2)) && (array[i][j] <= waterLine)) {
				newArray[i][j] = '~';
			}
			else if ((array[i][j] > waterLine) && (array[i][j] < (waterLine + (landZone * .15)))) {
				newArray[i][j] = '.';
			}
			else if ((array[i][j] > waterLine) && (array[i][j] >= (waterLine + (landZone * .15))) && (array[i][j] < (waterLine + (landZone * .40)))) {
				newArray[i][j] = '-';
			}
			else if ((array[i][j] > waterLine) && (array[i][j] >= (waterLine + (landZone * .40))) && (array[i][j] < (waterLine + (landZone * .80)))) {
				newArray[i][j] = '*';
			}
			else {
				newArray[i][j] = '^';
			}

		}
	}
	
	//printing character array
	for (i = 0; i < height; i++) {
		for (j = 0; j < width; j++) {
			printf(" %c", newArray[i][j]);
		}
		printf("\n");
	}
	//printing output to file
	for (i = 0; i < height; i++) {
		for (j = 0; j < width; j++) {
			fprintf(fptr, " %c", newArray[i][j]);
		}
		fprintf(fptr, "\n");
	}

	return 0;
}
