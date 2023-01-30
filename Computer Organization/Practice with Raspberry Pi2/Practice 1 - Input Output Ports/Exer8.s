.include "configuration.inc" 
.include "inter.inc"

		ldr r0, =GPBASE          
		ldr r1,=0x10
		
		
	loop:	str r1,[r0, #GPSET0]
		bl wait
		str r1,[r0, #GPCLR0]
		bl wait
		b loop
		
		
	wait: ldr r8, =STBASE
		ldr r9, [r8,#STCLO]
		ldr r10, =2273
		add r10, r9, r10
		
	ret1: ldr r9,[r8,#STCLO]
		cmp r9, r10
		blt ret1
		bx lr
		
	


