.include "configuration.inc" 
.include "inter.inc"

		ldr r0, =GPBASE                                                               
		ldr r2, =0x200
		ldr r3, =0x400
		
		str r2 ,[r0,#GPSET0]
		str r3 ,[r0,#GPSET0]
		
	loop:	ldr r1, [r0, #GPLEV0]
		tst r1, #4
		streq r3 ,[r0,#GPCLR0]
		
		ldr r1, [r0, #GPLEV0]
		tst r1, #8
		streq r2 ,[r0,#GPCLR0]
		
		
		
		b loop
	


