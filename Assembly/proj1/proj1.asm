;Joseph Wenzel
;9/25/14
;11:30 to 12:45 class with Sadeghian
;This program will take in 1 character as an input.
;If that one character is a lowercase letter it will print it out uppercase
;same for an Uppercase letter except it will print it out lowercase
;a number 1 through 9 will print out 10+that number of stars
;Any other character will be echoed back based on how many times the program has looped.
;Pressing Ctrl-D exits the program

SECTION .data			; Section containing initialised data
	BeginMsg:	db "Enter one char:" ;The Opening message at each iteration
	BeginLen:	equ $-BeginMsg	     ;Equate the length of the message
	NewLine:	db 10,"",10,10		     ;A newline character called when I need a new line printed
	Star:		db "*"		     ;Star character used to print out stars when a  number is enteres
	EndMsg:	 	db 10, "Thank you for using this program", 10 ; The message show when Ctrl+D is pressed
	EndLen:		equ $-EndMsg				    ;Compute the length of the end message
	OutputMsg:	 db "Here is the output: "		    ; Message shown with the output
	OutputLen:	 equ $-OutputMsg			    ;Length of the output message
	
SECTION .bss		; Section containing uninitialized data
	Buff resb 4	; Resever a 4 byte buffer for the input
	Iterations resb 4 	; Reserve 4 bytes for the Iterations variable
	i resb 4		; Reserve 4 bytes for the i used in the forloop
	
SECTION .text		; Section containing code
	
global 	_start		; Linker needs this to find the entry point!

_start:
	nop			; This no-op keeps gdb happy...
	mov byte [Iterations], 0 ;Initialize iterations as 0
	
Read:	add byte [Iterations], 1 ;Add 1 to iterations to show an iteration has occurred
	mov eax,4		; Specify sys_write call
	mov ebx,1		; Specify File Descriptor 1: Standard Output
	mov ecx,BeginMsg	; Pass offset of the message
	mov edx,BeginLen	; Pass the length of the message
	int 80H			; Make kernel call
Input:
	mov byte [Buff], 0 ; zero out the buffer before every Iteration
	mov eax,3      		; Specify sys_read call
	mov ebx,0      		; Specify File Descriptor 0: Standard Input
	mov ecx,Buff   		; Pass offset of the buffer to read to
	mov edx,1      		; Tell sys_read to read one char from stdin
	int 80h			; Call sys_read

	cmp byte [Buff], 0	; Compare to 0 then exit if its equal
	je Exit			;Jump to program Exit
	cmp byte [Buff], 10	;Compare to Buff to 10. If its equal redo that iteration
	je RandomLabel 		;Redo the Iteration
	cmp byte [Buff], 'a'	; Compare the input to lowercase a
	jb NotLower		;We dont have a lowercase letter
	cmp byte [Buff], 'z'	;compare to ascii z
	ja NotLower		;We dont have a lowercase letter

	mov eax,4       	; Specify sys_write call
        mov ebx,1       ; Specify File Descriptor 1: Standard Output
        mov ecx,OutputMsg 	; Pass offset of the message
        mov edx,OutputLen 	; Pass the length of the message
        int 80H			;Make Kernal call

	sub byte [Buff], 32	;Subtract 32 from the buffer to get the correct ascii value for uppercase
	mov eax,4	; Specify sys_write call
	mov ebx,1	; Specify File Descriptor 1: Standard Output
	mov ecx,Buff ; Pass offset of the message
	mov edx,1 ; Pass the length of the message
	int 80H	  ; Make Kernal Call

	mov eax,4       	; Specify sys_write call
        mov ebx,1       ; Specify File Descriptor 1: Standard Output
        mov ecx,NewLine 	; Pass offset of the message
        mov edx,1 	; Pass the length of the message
        int 80H	;Make a kernal call
	mov eax,4       	; Specify sys_write call
        mov ebx,1       ; Specify File Descriptor 1: Standard Output
        mov ecx,NewLine ; Pass offset of the message
        mov edx,1       ; Pass the length of the message
        int 80H         ;Kernal call
	
	jmp Read	;Jump back to read to get more input

NotLower:
	cmp byte [Buff], 'A'	; Compare the input to lowercase A
	jb NotUpper		;The input is not Uppercase
        cmp byte [Buff], 'Z'	;Compaer the input to Z
        ja NotUpper		;The input is not Uppercase Z

	mov eax,4		; Specify sys_write call
        mov ebx,1       ; Specify File Descriptor 1: Standard Output
        mov ecx,OutputMsg ; Pass offset of the message
        mov edx,OutputLen ; Pass the length of the message
        int 80H
	
	add byte [Buff], 32	;Add 32 to the buffer to get the
        mov eax,4       ; Specify sys_write call
        mov ebx,1       ; Specify File Descriptor 1: Standard Output
        mov ecx,Buff 	; Pass offset of the message
        mov edx,1 	; Pass the length of the message
        int 80H		; make Kernal call

        mov eax,4	; Specify sys_write call
        mov ebx,1       ; Specify File Descriptor 1: Standard Output
        mov ecx,NewLine	; Pass offset of the message
        mov edx,1       ; Pass the length of the message
	int 80H		;make Kernal call
	mov eax,4	; Specify sys_write call
        mov ebx,1      ; Specify File Descriptor 1: Standard Output
        mov ecx,NewLine ; Pass offset of the message
        mov edx,1       ; Pass the length of the message
        int 80H         ;Kernal call
	
        jmp Read	;Jump back up to read to get more input
	
NotUpper:
	cmp byte [Buff], '0'    ; Compare the input to the character 0
        jb NotNumber		;Jump if the ascii value is below
        cmp byte [Buff], '9'	;Compare the input to the character 9
        ja NotNumber		;Jump if it is above 9
	mov byte [i], 0		;Initialize i as 0
	sub byte [Buff], 38	;subtract 38 from the buffer to get the correct Ascii value to print out
ForLewp:
	mov eax, [i]		;Move i into eax so we can compare it
        cmp eax, [Buff]		;compare eax to whats in the buffer
	je EndForLoop		;end the for loop if it is equal
	mov eax,4		; Specify sys_write call
        mov ebx,1       ; Specify File Descriptor 1: Standard Output
        mov ecx, Star   ; Pass offset of the message
        mov edx, 1      ; Pass the length of the message
	int 80H		;make Kernal call
        inc byte [i]		;Increment i for the forloop
	jmp ForLewp		;Repeat the forLoop
	
NotNumber:
	mov byte [i], 0	  ;Zero out i for the for loop
	mov eax,4		; Specify sys_write call
        mov ebx,1       ; Specify File Descriptor 1: Standard Output
        mov ecx,OutputMsg ; Pass offset of the message
        mov edx,OutputLen ; Pass the length of the message
        int 80H		  ;Make Kernal Call
	
ForLoop:
	mov ebx, [i]		;move i into ebx so we can compare
	cmp ebx, [Iterations]	;compare with how many iterations were gone through
	je EndForLoop		;End the loop if theyre equal
	mov eax,4		; Specify sys_write call
        mov ebx,1       ; Specify File Descriptor 1: Standard Output
	mov ecx, Buff 	; Pass offset of the message
        mov edx, 1	; Pass the length of the message
        int 80H		;make Kernal Call
	inc byte [i]		;Increment i for the forloop
	jmp ForLoop		;Repeat the forloop until done

EndForLoop:	
        mov eax,4	; Specify sys_write call
        mov ebx,1       ; Specify File Descriptor 1: Standard Output
        mov ecx,NewLine ; Pass offset of the message
        mov edx,1       ; Pass the length of the message
        int 80H		;Kernal call
	mov eax,4	; Specify sys_write call
        mov ebx,1       ; Specify File Descriptor 1: Standard Output
        mov ecx,NewLine ; Pass offset of the message
        mov edx,1       ; Pass the length of the message
        int 80H         ;Kernal call
	
	jmp Read	;jump back up to Read

RandomLabel:
	sub byte [Iterations], 1 ;Subtract one from the Iterations to redo that iteration
	jmp Input		 ;Jump back up and take in input again
	
Exit:
	mov eax,4       ; Specify sys_write call
	mov ebx,1       ; Specify File Descriptor 1: Standard Output
        mov ecx,EndMsg    ; Pass offset of the message
        mov edx,EndLen       ; Pass the length of the message
        int 80H		     ;Kernal call
	mov eax,1		; Code for Exit Syscall
	mov ebx,0		; Return a code of zero
	int 80H			; Make kernel call
	