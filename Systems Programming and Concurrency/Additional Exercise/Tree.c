/*
 * Tree.c
 *
 *  Created on: 15 Mar 2023
 *      Author: Fernando Javier López Cerezo
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include "Tree.h"

void createTree(Tree *ptrTree) {
	*ptrTree = NULL;
}

int alphabetnum(char c) {
	c = tolower(c);
	int ans = 0;
	char cont = 'a';

	while (cont != c) {
		ans++;
		cont++;
	}
	return ans;

}

char numalphabet(int a) {
	char ans = 'a';
	int cont = 0;

	while (cont != a) {
		ans++;
		cont++;
	}
	return ans;
}

void insertTree(Tree *ptrTree, char *word) {

	if (*ptrTree == NULL) {
		*ptrTree = (Tree) malloc(sizeof(struct Node));
		for (int i = 0; i < 26; i++) {
			(*ptrTree)->letters[i].end = '/';
			(*ptrTree)->letters[i].next = NULL;
		}
	}
	int c = alphabetnum(*word);
	if (strlen(word) == 1) {
		(*ptrTree)->letters[c].end = 'x';
	} else {
		word++;
		insertTree(&((*ptrTree)->letters[c].next), word);
	}
}

void loadTextFile(char *filename, Tree *ptrTree) {
	FILE *f = fopen(filename, "rt");
	if (f == NULL) {
		perror("File couldn't be opened");
	}
	char word[30];
	while (fscanf(f, "%s ", word) == 1) {
		insertTree(ptrTree, word);
	}
	fclose(f);
}

int isWordinTree(Tree t, char *word) {
	int ans = 0;
	if (t != NULL) {
		int c = alphabetnum(*word);
		if (strlen(word) == 1) {
			if (t->letters[c].end == 'x') {
				ans = 1;
			}
		} else {
			word++;
			ans = isWordinTree(t->letters[c].next, word);
		}
	}
	return ans;
}

int depth(Tree t) {
	if (t == NULL) {
		return 0;
	}
	int ans = 1 + depth(t->letters[0].next);
	int aux;
	for (int i = 1; i < 26; i++) {
		aux = 1 + depth(t->letters[i].next);
		if (aux > ans) {
			ans = aux;
		}
	}
	return ans;
}

void longestPrefix(Tree t, char *prefix) {
	char ans[20];
	strcpy(ans,prefix);
	int c;

	while (strlen(prefix)>0) {
		c = alphabetnum(*prefix);
		if (t->letters[c].next == NULL) {
			perror("There is no word with this prefix in the tree");
		} else {
			prefix++;
			t = t->letters[c].next;
		}
	}

	while (t != NULL) {
		int maxdepth = depth(t->letters[0].next);
		int index = 0;
		int auxdepth;

		for (int i = 1; i < 26; i++) {
			auxdepth = depth(t->letters[i].next);
			if (auxdepth > maxdepth) {
				maxdepth = auxdepth;
				index = i;
			}
		}

		char cc;

		if (maxdepth == 0) {
			int found = 0;
			int i = 0;
			while (!found) {
				if (t->letters[i].end == 'x') {
					found = 1;
				} else {
					i++;
				}
			}
			cc = numalphabet(i);
		} else {
			cc = numalphabet(index);
		}
		strncat(ans, &cc, 1);
		t = t->letters[index].next;
	}
	printf("%s\n",ans);

}

