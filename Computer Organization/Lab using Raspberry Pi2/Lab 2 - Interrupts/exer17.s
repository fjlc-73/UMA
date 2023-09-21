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
	ldr r0, =STBASE
	ldr r1, [r0, #STCLO]
	ldr r2, =500000
	add r1, r2, r1     
	str r1, [r0, #STC1]
	
	ldr r0,=INTBASE
	mov r1, #0b0010 
	str r1,[r0,#INTENIRQ1]
	
	
	
	ldr r0, =INTBASE
	ldr r1, =0x083
	str r1, [r0, #INTFIQCON]
	
	mov r1, #0b00010011
	msr cpsr_c, r1
	
	mov r7, #0
	mov r6, #0
	
	ldr r0, =STBASE
	ldr r1, [r0, #STCLO]
	ldr r5, =7937
	add r1, r5, r1
	str r1, [r0, #STC3]


end:   b end

/* Regular interrupt (only if used) */
regular_interrupt: 
	push {r0, r1, r2, r3, lr}
	
	ldr r0, =GPBASE
	ldr r3, =0x08420E00
	str r3, [r0, #GPCLR0]
	
	cmp r7, #0
	beq cero

	cmp r7, #1
	beq uno

	cmp r7, #2
	beq dos

	cmp r7, #3
	beq tres
	
	cmp r7, #4
	beq cuatro
	
	cmp r7, #5
	beq cinco
	
cero: 
	        ldr r2, =0x0200
		ldr r5, =7937
		add r7, r7, #1
		b fin
	
uno: 
	       ldr r2, =0x0400
	       ldr r5, =6826
	       add r7, r7, #1
	       b fin
	
dos : 
	        ldr r2, =0x0800
		ldr r5, =6060
		add r7, r7, #1
		b fin
	
tres: 
	        ldr r2, =0x020000
		ldr r5, =5731
		add r7, r7, #1
		b fin
	
cuatro: 
	           ldr r2, =0x0400000
		   ldr r5, =5102
		   add r7, r7, #1
		   b fin
		   
cinco: 
		 ldr r2, =0x08000000
		 ldr r5, =4545
	         mov r7, #0
		 b fin


fin:	ldr r0, =GPBASE
	
	str r2, [r0, #GPSET0]
	

	ldr r0, =STBASE

	ldr r0, =STBASE
	mov r1, #0b0010   
	str r1,[r0,#STCS]
	
	ldr r0, =STBASE
	ldr r1, [r0, #STCLO]
	ldr r2, =500000
	add r1, r2, r1   
	str r1, [r0, #STC1]
	

	pop {r0, r1, r2, r3, lr}
	subs  pc, lr, #4

/* Fast interrupt (only if used) */
fast_interrupt: 
	push {r0, r1, lr}
	
	ldr r0, =GPBASE
	ldr r1, =0x10
	eors r6, #1
	
	streq r1,[r0,#GPSET0]
	strne r1,[r0,#GPCLR0]

	ldr r0, =STBASE
	mov r1, #0b01000 
	str r1,[r0,#STCS]
	
	ldr r0, =STBASE
	ldr r1, [r0, #STCLO]
	add r1, r5, r1 
	str r1, [r0, #STC3]



	pop {r0, r1, lr}
	subs  pc, lr, #4
	