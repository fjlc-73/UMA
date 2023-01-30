/**
UNIX Shell Project

Sistemas Operativos
Grados I. Informatica, Computadores & Software
Dept. Arquitectura de Computadores - UMA

Some code adapted from "Fundamentos de Sistemas Operativos", Silberschatz et al.

To compile and run the program:
   $ gcc Shell_project.c job_control.c -o Shell
   $ ./Shell          
	(then type ^D to exit program)

**/

#include "job_control.h"   // remember to compile with module job_control.c 
#include <string.h>

#define MAX_LINE 256 /* 256 chars per line, per command, should be enough. */

// -----------------------------------------------------------------------
//                            MAIN          
// -----------------------------------------------------------------------

int main(void)
{
	char inputBuffer[MAX_LINE]; /* buffer to hold the command entered */
	int background;             /* equals 1 if a command is followed by '&' */
	int arrow;
	char *args[MAX_LINE/2];     /* command line (of 256) has max of 128 arguments */
	// probably useful variables:
	int pid_fork, pid_wait; /* pid for created and waited process */
	int status;             /* status returned by wait */
	enum status status_res; /* status processed by analyze_status() */
	int info;				/* info processed by analyze_status() */

	ignore_terminal_signals();
	
	job * list_bg_stp = new_list("BG/STOPPED");
		
	void manejador(int senal){
	for(int i = list_size(list_bg_stp); i > 0; i--){
		job * j = get_item_bypos(list_bg_stp, i);
		if((j -> pgid) == waitpid((j -> pgid), &status, WUNTRACED | WNOHANG | WCONTINUED)){
			status_res = analyze_status(status, &info);
			if(status_res == SIGNALED || status_res == EXITED){
				if((j -> state) == RESPAWNABLE){
					pid_fork = fork();
					if (pid_fork == 0) {
						new_process_group(getpid());
						restore_terminal_signals();
						execvp((j ->args[0]), (j ->args));
						printf("Error, command not found: %s\n", (j ->args[0]));
						exit(-1);
					} else {
						(j -> pgid) = pid_fork;
						printf("Respawnable job running... pid: %d, command: %s\n", pid_fork, (j -> args[0]));
					}
				} else {

					if(status_res == SIGNALED){
						printf("Stopped process %s (%d) Signaled \n", (j -> command), (j -> pgid));
					} else {
						printf("Background process %s (%d) Exited \n", (j -> command), (j -> pgid));
					}
					delete_job(list_bg_stp, j);
				}
			} else if (status_res == SUSPENDED){
				(j -> state) = STOPPED;
			} else {
				(j -> state) = BACKGROUND;
			}
		}
	}
	}
		
	signal(SIGCHLD, manejador);
		
	while (1)   /* Program terminates normally inside get_command() after ^D is typed*/
	{   		
		printf("cuéntame bb->");
		fflush(stdout);
		
		
		get_command(inputBuffer, MAX_LINE, args, &background, &arrow);  /* get next command */
		
		if(args[0]==NULL) {
		
		} else if(strcmp(args[0], "cd") == 0) {
			int n = chdir(args[1]);
			if(n==-1 && args[1] != NULL){
				printf("No se puede cambiar al directorio %s \n", args[1]);
			}
			
		} else if(strcmp(args[0], "jobs") == 0){
			print_job_list(list_bg_stp);
			
		} else if(strcmp(args[0], "fg") == 0){
			int n;
			if(args[1] == NULL){
				n = 1;
			} else {
				n = atoi(args[1]);
			}
			
			if(n<1 || n>list_size(list_bg_stp)){
				printf("Second parameter of fg not acceptable \n");
			} else {
			
				job * j = get_item_bypos(list_bg_stp, n);
				int auxpid = (j -> pgid);
				char * auxname = strdup((j -> command));
			
				set_terminal(auxpid);
				delete_job(list_bg_stp, j);
				killpg(auxpid,SIGCONT);
			
				waitpid(auxpid, &status, WUNTRACED);
				set_terminal(getpid());
				status_res = analyze_status(status, &info);
			
				if(status_res == SUSPENDED){ 
					add_job(list_bg_stp, new_job(auxpid, auxname, STOPPED));
					printf("Foreground pid: %d, command: %s, Suspended, info: %d \n", auxpid, auxname, info);
				} else {
					printf("Foreground pid: %d, command: %s, Exited, info: %d \n", auxpid, auxname, info);
				}
			}
				
		} else if(strcmp(args[0], "bg") == 0){
			int n;
			if(args[1] == NULL){
				n = 1;
			} else {
				n = atoi(args[1]);
			}
			
			if(n<1 || n>list_size(list_bg_stp)){
				printf("Second parameter of bg not acceptable \n");
			} else {
				job * j = get_item_bypos(list_bg_stp, n);
				(j -> state) = BACKGROUND;
				killpg((j -> pgid) ,SIGCONT);
				printf("Background job running... pid: %d, command: %s \n", (j -> pgid), (j -> command));
			}
			
		} else if(strcmp(args[0], "mask") == 0){
		
			
			pid_fork = fork();
			if (pid_fork == 0) {
				int i = 0;
				int c = 0;
				char *aux[MAX_LINE/2];
				new_process_group(getpid());
				
				while(args[i]!=NULL){
					if(strcmp(args[i], "-c") == 0) {
						c = 1;
					}
					i++;
				}
				
				if(c != 1){
					printf("Mask sin -c no valido\n");
					exit(-1);
				}
				
				i = 1;
				
				while(strcmp(args[i], "-c") != 0){
					int a = atoi(args[i]);
					if(a<=0){
						printf("Señal de mask invalida\n");
						exit(-1);
					}
					block_signal(a, 1);
					i++;
				}
				
				if(i=1){
					printf("No se ha indicado señal para el mask\n");
					exit(-1);
				}
				
				int j = i;
				while(args[j] != NULL){
					j++;
				}
				
				for(int k = 0; k < j-i-1; k++){
					aux[k] = strdup(args[i+k+1]);
				}
				aux[j-i-1]=NULL;
				
				set_terminal(getpid());
				restore_terminal_signals();
				execvp(aux[0], aux);
				printf("Error, command not found: %s\n", args[0]);
				exit(-1);
			} else {
				waitpid(pid_fork, &status, WUNTRACED);
				status_res = analyze_status(status, &info);
				set_terminal(getpid());
				if(status_res == SUSPENDED){ 
					add_job(list_bg_stp, new_job(pid_fork, args[0], STOPPED));
				}
				printf("Foreground pid: %d, command: %s, %s, info: %d\n", pid_fork, args[0], status_strings[status_res], info);
			}
	
		} else if(arrow == 1){
			
			FILE *outfile;
			int fnum1,fnum2;
			int cont = 0;
			
			while(args[cont]!=NULL) {cont++;};

        		//Requiere 3 argumentos incluido el nombre del propio ejecutable
        		if (2>cont){
				printf("\tError: Redirección con insuficientes argumentos\n");
				
			} else if (NULL==(outfile=fopen(args[cont-1],"w")))
			{
				printf("\tError: No tienes los permisos para escribir en %s\n",args[cont-1]);

			} else {


			args[cont-1]=NULL;

			pid_fork = fork();
			if(pid_fork == 0){
				fnum1=fileno(outfile);
				fnum2=fileno(stdout);
				dup2(fnum1,fnum2);
				execvp(args[0],args);
				exit(-1);
			} else {
				waitpid(pid_fork, &status, WUNTRACED);
				fclose(outfile);
				status_res = analyze_status(status, &info);
				if(info != 0){
					printf("Command %s not found\n", args[0]);
				}
			}
			
	
			}
			
			
		} else if(arrow == 2) {
			FILE *infile;
			int fnum1,fnum2;
			int cont = 0;
			
			while(args[cont]!=NULL) {cont++;};

        		//Requiere 4 argumentos incluido el nombre del propio ejecutable
       		 if (2>cont){
				printf("\tError: Redirección con insuficientes argumentos\n");
			} else if (NULL==(infile=fopen(args[1],"r"))) {
			
				printf("\tError: abriendo: %s\n",args[1]);
			} else {
			
			args[cont-1]=NULL;
			
			pid_fork = fork();
			if(pid_fork == 0) {
				fnum1=fileno(infile);
				fnum2=fileno(stdin);
				dup2(fnum1,fnum2);
				execvp(args[0],args);
				fclose(infile); // solo si exec falla
				exit(-1);
			} else {
				waitpid(pid_fork, &status, WUNTRACED);
				status_res = analyze_status(status, &info);
				if(info != 0){
					printf("Command %s not found\n", args[0]);
				}
			}
			



			
		} }else {
		
		pid_fork = fork();
		if (pid_fork == 0) {
			new_process_group(getpid());
			if(background == 0){
				set_terminal(getpid());
			}
			restore_terminal_signals();
			execvp(args[0], args);
			printf("Error, command not found: %s\n", args[0]);
			exit(-1);
		} else {
			if(background == 0){ 
				waitpid(pid_fork, &status, WUNTRACED);
				status_res = analyze_status(status, &info);
				set_terminal(getpid());
				
				if(status_res == SUSPENDED){ 
				add_job(list_bg_stp, new_job(pid_fork, args[0], STOPPED));
				}
				
				printf("Foreground pid: %d, command: %s, %s, info: %d\n", pid_fork, args[0], status_strings[status_res], info);
			} else if(background == 1){
				add_job(list_bg_stp, new_job(pid_fork, args[0], BACKGROUND));
				printf("Background job running... pid: %d, command: %s\n", pid_fork, args[0]);
			} else {
				job * g = new_job(pid_fork, args[0], RESPAWNABLE);
				add_job(list_bg_stp, g);
				
				
				int i = 0;
				while(args[i] != NULL){
					(g -> args)[i] = strdup(args[i]);
					i++;
				}
				(g -> args)[i] = NULL;
				
				printf("Respawnable job running... pid: %d, command: %s\n", pid_fork, args[0]);
			}
			
		}
		
		

	}

	} 
}


