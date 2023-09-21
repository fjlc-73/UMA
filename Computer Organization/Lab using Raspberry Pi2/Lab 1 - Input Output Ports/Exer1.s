.include "configuration.inc" 
.include "inter.inc"

	ldr r0, =GPBASE                                                               
	ldr r1, =0x200
	str r1 ,[r0,#GPSET0]
	
	ldr r1, =0x400000
	str r1 ,[r0,#GPSET0]
	
	ldr r1, =0x800
	str r1 ,[r0,#GPSET0]
	

END:	B END
