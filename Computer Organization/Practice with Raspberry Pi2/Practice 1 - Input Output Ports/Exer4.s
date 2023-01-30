.include "configuration.inc" 
.include "inter.inc"

		ldr r0, =GPBASE                                                               
		ldr r2, =0x8000000
		ldr r3, =0x800
		
	loop:	ldr r1, [r0, #GPLEV0]
		tst r1, #4
		streq r2 ,[r0,#GPSET0]
		
		
		ldr r1, [r0, #GPLEV0]
		tst r1, #8
		streq r3 ,[r0,#GPSET0]
		
		
		
		b loop
	


