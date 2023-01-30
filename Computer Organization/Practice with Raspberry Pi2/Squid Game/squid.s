/* Basic skeleton for programs using interrupts */

.include "configuration.inc" 
.include "symbolic.inc"

/* Vector Table inicialization */
	mov r0,#0
	ADDEXC 0x18, regular_interrupt @only if used
	ADDEXC 0x1C, fast_interrupt      @only if used


/* Stack init for IRQ mode */	
	mov     r0, #0b11010010   
	msr     cpsr_c, r0
	mov     sp, #0x8000
/* Stack init for FIQ mode */	
	mov     r0, #0b11010001
	msr     cpsr_c, r0
	mov     sp, #0x4000
		mov r8,#0
/* Stack init for SVC mode	*/
	mov     r0, #0b11010011
	msr     cpsr_c, r0
	mov     sp, #0x8000000
	
/* Continue my program here */

		ldr r0, =GPBASE                                                               
		ldr r1, =0x8000000
		str r1 ,[r0,#GPSET0]
		
		

check1:	mov r5, #0
		ldr r1, [r0, #GPLEV0]
		tst r1, #0b00100
		bne check1
		
		bl wait
		
		mov r1, #0b01000
		str r1,[r0,#GPFEN0]
		
		ldr r0, =STBASE
		ldr r1, [r0, #STCLO]
		ldr r2, =5000000
		add r1, r2     @y microseconds
		str r1, [r0, #STC3]
		
		ldr r0, =INTBASE 
		ldr r1,  =0x00100000
		str r1,[r0,#INTENIRQ2]
		
		ldr r1, =0x083
		str r1, [r0, #INTFIQCON]
		
		mov r1, #0b00010011
		msr cpsr_c, r1
		
		ldr r0, =GPBASE
		
		ldr r1, =0x400000
		str r1 ,[r0,#GPSET0]
		
check2:	ldr r1, [r0, #GPLEV0]
		tst r1, #0b00100
		bne check2
		
		bl wait
		
		ldr r1, =0x400000
		str r1 ,[r0,#GPCLR0]
		
		ldr r1, =0x20000
		str r1 ,[r0,#GPSET0]
		
check3:	ldr r1, [r0, #GPLEV0]
		tst r1, #0b00100
		bne check3
		
		bl wait
		
		ldr r1, =0x20000
		str r1 ,[r0,#GPCLR0]
		
		ldr r1, =0x800
		str r1 ,[r0,#GPSET0]
		
check4:	ldr r1, [r0, #GPLEV0]
		tst r1, #0b00100
		bne check4
		
		bl wait
		
		ldr r1, =0x800
		str r1 ,[r0,#GPCLR0]
		
		ldr r1, =0x400
		str r1 ,[r0,#GPSET0]
		
check5:	ldr r1, [r0, #GPLEV0]
		tst r1, #0b00100
		bne check5
		mov r5, #1
		
loop:	ldr r1, =0x08420E00
		str r1 ,[r0,#GPSET0]
		bl wait
		str r1 ,[r0,#GPCLR0]
		bl wait
		b loop
		
		
		
wait: 	ldr r7,=STBASE
		ldr r3,[r7,#STCLO]
		ldr r4, =200000
		add r4, r3, r4  
ret1:   	ldr r3,[r7,#STCLO]
		cmp r3,r4     
		blt ret1
		bx lr
		

end:   b end

/* Regular interrupt (only if used) */
regular_interrupt: 
	push {r0, r1, r2, r3, r4, r7}
	
	ldr r0, =GPBASE                                                               
	ldr r1, =0x8000000
	str r1 ,[r0,#GPCLR0]
	ldr r1, =0x200
	str r1 ,[r0,#GPSET0]

	
	
wait2: 	ldr r7,=STBASE
		ldr r3,[r7,#STCLO]
		ldr r4, =500000
		add r4, r3, r4  
ret2:   	ldr r3,[r7,#STCLO]
		ldr r2, [r0,#GPLEV0]
		tst r2,#0b00100
		beq sound
		
		cmp r3,r4     
		blt ret2
		
		mov r1, #0b01000
		str r1,[r0,#GPEDS0]
		
		ldr r1, =0x8000000
		str r1 ,[r0,#GPSET0]
		
		ldr r1, =0x200
		str r1 ,[r0,#GPCLR0]
		b endil
	
sound: 	ldr r1, =0x010
		str r1,[r0,#GPSET0] /* HIGH */
		bl   wait4
		str r1,[r0,#GPCLR0] /* LOW */
		bl   wait4
		b    sound
	
wait4: 	ldr r7,=STBASE
		ldr r3,[r7,#STCLO]
		ldr r4, =2000
		add r4, r3, r4  
ret4:   	ldr r3,[r7,#STCLO]
		cmp r3,r4     
		blt ret4
		bx lr
endil:
	pop {r0, r1, r3, r4, r7}
	subs  pc, lr, #4
	


/* Fast interrupt (only if used) */
fast_interrupt: 
	push {r0, r1, r3, r4, r7}
	
	ldr r0, =GPBASE  
	ldr r1, =0x010
	cmp r5, #1
	beq endi
	
	
loop2: 	str r1,[r0,#GPSET0] /* HIGH */
		bl   wait3
		str r1,[r0,#GPCLR0] /* LOW */
		bl   wait3
		b    loop2
	
wait3: 	ldr r7,=STBASE
		ldr r3,[r7,#STCLO]
		ldr r4, =2000
		add r4, r3, r4  
ret3:   	ldr r3,[r7,#STCLO]
		cmp r3,r4     
		blt ret3
		bx lr

endi:
	pop {r0, r1, r3, r4, r7}
	b loop
	subs  pc, lr, #4
	