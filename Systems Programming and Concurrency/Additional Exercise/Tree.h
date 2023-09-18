/*
 * Tree.h
 *
 *  Created on: 15 Mar 2023
 *      Author: Fernando Javier López Cerezo
 */

#ifndef TREE_H_
#define TREE_H_

typedef struct Node * Tree;
typedef struct Letter Letter;

struct Letter{
	char end;
	Tree next;
};

struct Node{
	Letter letters[26];
};



void createTree(Tree* ptrTree);

void insertTree(Tree* ptrTree, char* word);

int alphabetnum(char c);

char numalphabet(int a);

void loadTextFile(char* filename, Tree* ptrTree);

int isWordinTree(Tree t, char* word);

int depth(Tree t);

void longestPrefix(Tree t, char *prefix);



#endif /* TREE_H_ */
