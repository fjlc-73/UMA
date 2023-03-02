/*
 * Main.c
 *
 *  Created on: 1 Mar 2023
 *      Author: Fernando Javier López Cerezo
 */

#include <stdio.h>
#include <stdlib.h>

const unsigned int DELTA = 0x9e3779b9;

void decrypt(unsigned int* v, unsigned long* k){

	int sum = 0xC6EF3720;

	for(int i = 0; i < 32; i++){
		v[1]-=((v[0]<<4)+k[2])^(v[0]+sum)^((v[0]>>5)+k[3]);
		v[0]-=((v[1]<<4)+k[0])^(v[1]+sum)^((v[1]>>5)+k[1]);
		sum -= DELTA;
	}
}

int main(){

	FILE * in = fopen("image01.png", "rb");

	if(in==NULL){
		perror("I can't open image01.png");
	}

	unsigned int finalSize; // size of the original deciphered size
	fread(&finalSize,sizeof(unsigned int),1,in);
	int numBlocks;

	if((finalSize%8) == 0){
		numBlocks = finalSize/8;
	} else {
		numBlocks = finalSize/8 + 1;
	}

	unsigned int * buffer = (unsigned int *)malloc(numBlocks*8);
	//we read the file
	fread(buffer,8,numBlocks,in);//buffer contains image01.png - block with size info
	fclose(in);
	unsigned long k[4] = {128, 129, 130, 131};

	for(int i = 0; i < numBlocks; i++){
		decrypt(buffer+i*2,k);
	}

	// to write the final file
	FILE * out = fopen("decryptedImage.png", "wb");

	if(out == NULL){
		perror("We can't open decryptedImage.png");
	}

	fwrite(buffer,finalSize,1,out);
	fclose(out);
	free(buffer);

}
