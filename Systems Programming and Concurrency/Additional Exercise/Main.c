/*
 * Main.c
 *
 *  Created on: 15 Mar 2023
 *      Author: Fernando Javier López Cerezo
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "Tree.h"

int main(void) {
	Tree t;
	createTree(&t);
	printf("Loading...\n");
	loadTextFile("data.txt", &t);
	printf("%d\n", isWordinTree(t,"lake"));
	printf("%d\n", isWordinTree(t,"last"));
	printf("%d\n", isWordinTree(t,"land"));
	printf("%d\n", isWordinTree(t,"lord"));
	printf("%d\n", isWordinTree(t,"my"));
	printf("%d\n", isWordinTree(t,"lastly"));
	printf("%d\n", depth(t));
	longestPrefix(t,"m");



}
