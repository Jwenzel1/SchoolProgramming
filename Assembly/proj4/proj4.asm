;;   Created date    : 10/29/2014
;;   Last update     : 10/30/2014
;;   Author          : Joseph Wenzel
;;   Email           : jwenzel1@umbc.edu
;;   Class           : Sadeghian's tue/thur 11-12:45 class
;;   Description     : This program requires a command line argument to be given
;;                     This argument will be the name of the file it opens
;;                     This program will then ask for an input and a base to convert it to
;;                     it will convert the given input to the base of their choosing

;;MAcro for printing a number to the screen 
%macro PRINTNUM 2
	push %1			;This is the arguments for the %d
	push %2			;This is the string to print out
	call printf		;display the number
	add esp, 8
%endmacro

;;Macro to write numbers to the output file 
%macro WRITETOFILE 3
	push %1			;file name
	push %2			;arguments for the file
	push %3			;file handle
	call fprintf		;write to the file
	add esp, 12
%endmacro
	
section .data		; Section containing initialised data

	numberMsg db "Enter a number between 0 and 255: ",0 ;message to ask for a number
	baseMsg db "Enter a base between 2 and 9: ",0	    ;message to ask for a base
	valueFormat db "%d",0				    ;format to have numbers
	newLine db 10,0					    ;newline code
	writeCode db "w",0				    ;write mode used for opening the file

section .bss		; Section containing uninitialized data

	value resd 1  	; Reserve a byte to store the users input
	base resd 1	; Reserve a byte to store the given base
	i resd 1	;used for a loop counter
	fileName resd 255	;stores the file name
	fileHandle resd 255	;stores the file handle
	
section .text		; Section containing code

global main		; Required so linker can find entry point
	
extern printf		; Notify linker that we're calling printf
extern scanf		; Notify linker that we're calling scanf
extern fopen		; Notify linker that we're calling fopen
extern fprintf		; Notify linker that we're calling fprintf
extern fclose		; Notify linker that we're calling flcose
	
main:
	push ebp		; Set up stack frame for debugger
	mov ebp,esp
	push ebx		; Program must preserve ebp, ebx, esi, & edi
	push esi
	push edi
;;; ; Everything before this is boilerplate; use it for all

	mov ebx, [ebp + 12]	;pointer to arg table in ebx
	mov eax, [ebx + 4]	;address of first arg
	mov [fileName], eax	;save the filename into filename

	push writeCode		;push the code to open a file in write mode
	push eax		;push the filename
	call fopen		;open the file
	add esp, 8
	mov [fileHandle], eax	;move the file handle into filehandle for use later
getInput:
        push numberMsg		;push the message to print
        call printf		;print out the message
	add esp, 4

	push value		;push the variable to store the value
	push valueFormat	;push the code saying it will be an integer
	call scanf		;get the input
	add esp, 8

	mov eax, dword [value]	;move the user given value into eax for comparison
	cmp eax, 0		;compare it to zero
	jl getInput		;if less than zero get input again cus its wrong
	cmp eax, 255		;compare to 255
	jg getInput		;if its greater than 255 get a new value

getBase:
        push baseMsg		;push the message to print onto the stack
        call printf		;print it out
        add esp, 4

        push base		;push the address to store the base in
        push valueFormat	;push the format to get input into
        call scanf		;get user input
        add esp, 8

        mov eax, dword [base]	;move the user given base into eax to comapre
	cmp eax, 0		;compare it to 0
	je exit			;exit if it is exactly 0
        cmp eax, 2		;comapre it to 2
        jl getBase		;get a new base if it is less than two
        cmp eax, 9		;compare it to 9
        jg getBase		;get a new base if it is greater than 9
	mov eax, 0		;zero out registers so i can use them in div
	mov ebx, 0
	mov edx, 0
	mov eax, dword [value]	;move the user given value into eax so i can use div on it
	mov ebx, dword [base]	;move the given base into ebx so i can div eax with it

	mov ecx, 8		;initialize the loop counter
	mov [i], ecx		;move the loop counter into i
convert:
	mov edx, 0	   ;zero out edx so it can store the remainder
	div ebx		;eax has the quotient edx has the remainder
	push edx	;push the remainder onto the stack

	mov ecx, dword [i]	;move the loop counter into ecx
	dec ecx			;decrement ecx
	mov [i], ecx		;move the decremented counter back into i
	mov edx, 0		;zero out edx
	cmp ecx, edx		;compare the loop counter to zero
	jne convert		;if its not zero there are more digits to convert

	mov ecx, 8		;prepare another loop counter
	mov [i], ecx		;move it into i for safe keeping
printNumber:
	pop ebx			;pop the first digit off the stack
	PRINTNUM ebx, valueFormat ;print it to the screen
	mov eax, [fileHandle]	  ;move eax into the file handle to pass to WRITETOFILE
	WRITETOFILE ebx, valueFormat, eax ;write the digit to the file
	mov ecx, dword [i]		  ;move the loop counter into ecx
        dec ecx				  ;decrement it
        mov [i], ecx			  ;move it back into i for keeping
        mov edx, 0			  ;zero out edx to use for comparison
        cmp ecx, edx			  ;compare ecx to zero
        jne printNumber			  ;if its not zero there are more digits on the stack that need printing
	
	push newLine		;push a newline character on the stack
	call printf		;print it out
	add esp, 4

	mov eax, [fileHandle]	;move the filehandle into eax
	push newLine		;push the newline onto the stack
	push eax		;get the filehandle onto the stack
	call fprintf		;write a newline to the file
	add esp, 8

	jmp getBase		;get a new base and do it all over again
exit:
	mov eax, dword [fileHandle] ;move the file handle into eax so we can close the file
	push eax		    ;push it onto the stack
	call fclose		    ;close that file
	add esp, 4
;;; ; Everything after this is boilerplate; use it for all ordinary apps!
	pop edi			; Restore saved registers
	pop esi
	pop ebx
	mov esp,ebp		; Destroy stack frame before returning
	pop ebp
	ret			; Return control to Linux
	