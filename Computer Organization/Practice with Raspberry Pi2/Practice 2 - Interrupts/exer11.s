/* Basic skeleton for programs using interrupts */

.include "configuration.inc" 
.include "symbolic.inc"

/* Vector Table inicialization */
	mov r0,#0
	ADDEXC 0x18, regular_interrupt @only if used


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
	ldr r2, =2000000
	add r1, r1, r2    
	str r1, [r0, #STC1]
	
	ldr r0,=INTBASE
	mov r1, #0b0010 
	str r1,[r0,#INTENIRQ1]
	
	mov r1, #0b01010011
	msr cpsr_c, r1
	



end:   b end

/* Regular interrupt (only if used) */
regular_interrupt: 
	push {r0,r1}

	ldr r0, =GPBASE
	ldr r1, =0x800
	str r1, [r0, #GPSET0]
	ldr r1, =0x20000
	str r1, [r0, #GPSET0]
	
	ldr r0, =STBASE
	mov r1, #0b0010  
	str r1,[r0,#STCS]


	pop {r0,r1}
	subs  pc, lr, #4


	