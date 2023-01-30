The purpose of this project was to create a program which simulates the shell of UNIX. This program had to wait for the user to write a command through the keyboard and create a process to execute it using system calls such as fork, execvp, waitpid and exit.
Some of the shell's functionalities are: 
  Execute commands in an independent process.
  Wait (or not) for the process to finish depending on if it was executed in foreground or background. 
  Show messages informing of the termination (or not) of the command and it's metadata. 
  Continue processing next commands input by the user. 
  Manage which processes have access to the terminal.
  Manage the pid of the processes. 
  Manage signals sent to the processes. 
  Include the following internal commands: jobs, fg, bg.
