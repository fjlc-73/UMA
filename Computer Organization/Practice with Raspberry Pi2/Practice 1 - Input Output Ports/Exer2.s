.include "configuration.inc" 
.include "inter.inc"

	loop:	ldr r0, =GPBASE                                                               
		ldr r2, =0x800
		ldr r1, [r0, #GPLEV0]
		tst r1, #4
		streq r2 ,[r0,#GPSET0]
		beq loop
		str r2 ,[r0,#GPCLR0]
		b loop
	


