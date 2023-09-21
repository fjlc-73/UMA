.include "configuration.inc" 
.include "inter.inc"

		ldr r0, =GPBASE                                                               
		ldr r2, =0x200
		ldr r3, =0x400
		ldr r4, =0x800
		ldr r5, =0x20000
		ldr r6, =0x400000
		ldr r7, =0x8000000
		
	loop:	str r2 ,[r0,#GPSET0]
		str r3 ,[r0,#GPSET0]
		str r4 ,[r0,#GPSET0]
		str r5 ,[r0,#GPSET0]
		str r6 ,[r0,#GPSET0]
		str r7 ,[r0,#GPSET0]
		
		bl wait
		
		str r2 ,[r0,#GPCLR0]
		str r3 ,[r0,#GPCLR0]
		str r4 ,[r0,#GPCLR0]
		str r5 ,[r0,#GPCLR0]
		str r6 ,[r0,#GPCLR0]
		str r7 ,[r0,#GPCLR0]
		
		bl wait2
		
		b loop
		
		
	wait: ldr r8, =STBASE
		ldr r9, [r8,#STCLO]
		ldr r10, =1000000
		add r10, r9, r10
		
	ret1: ldr r9,[r8,#STCLO]
		cmp r9, r10
		blt ret1
		bx lr
		
	wait2: ldr r8, =STBASE
		ldr r9, [r8,#STCLO]
		ldr r10, =250000
		add r10, r9, r10
		
	ret2: ldr r9,[r8,#STCLO]
		cmp r9, r10
		blt ret2
		bx lr


