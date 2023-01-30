.include "configuration.inc" 
.include "inter.inc"

		ldr r0, =GPBASE                                                               
		ldr r1, =0x10
		
		
	loop:	ldr r2, [r0, #GPLEV0]
		tst r2, #4
		beq do
		ldr r2, [r0, #GPLEV0]
		tst r2, #8
		beq sol
		b loop
		
	do :  str r1,[r0, #GPSET0]
		bl wait
		str r1,[r0, #GPCLR0]
		bl wait
		b do
		
	sol:   str r1,[r0, #GPSET0]
		bl wait2
		str r1,[r0, #GPCLR0]
		bl wait2
		b sol
		
		
	wait: ldr r8, =STBASE
		ldr r9, [r8,#STCLO]
		ldr r10, =3817
		add r10, r9, r10
		
	ret1: ldr r9,[r8,#STCLO]
		cmp r9, r10
		blt ret1
		bx lr
		
	wait2: ldr r8, =STBASE
		ldr r9, [r8,#STCLO]
		ldr r10, =2558
		add r10, r9, r10
		
	ret2: ldr r9,[r8,#STCLO]
		cmp r9, r10
		blt ret2
		bx lr
	
	


