;Joseph Wenzel
;10/1/14
;11:30 to 12:45 class with Sadeghian
;This program is supposed to take in any string of any characters
;and filter out any lowercase letters in it.
;Then it is supposed to sort the string using selection sort
;This did not happen. The best it can do is take in a string.
;I dont know what happened but when I tried to implement selection sort all of ym code broke. This is the best of what i have and i don't have anymore time to work on it so here is what i have 
section .bss		
	i 	resb	4	;Reserve 4 bytes for the i variable used in the selction sort for loop
	j 	resb	4	;Reserve 4 bytes for the j variable used in the second for loop for selection sort
	letter 	resb	4	;Reserve 4 bytes to store the letter that selection sort is going to process
	BUFFLEN 	equ 500	;Length of each buffer
	InputBuff: 	resb BUFFLEN ;create a buffer to take in input
	LowercaseBuff: 	resb BUFFLEN ;create a buffer to store the lowercase letters from the input
	SortingBuff:	resb BUFFLEN ;create a buffer to put the sorted characteres in
	
section .data
	Intro:	  db "Enter the input: " ;The introduction string
	IntroLen: equ $-Intro		 ;equate the length of the introduction string to use for printing
	SortMsg: db "Sorting string: "	 ;The String printed out when sorting begins
	SortLen: equ $-SortMsg		 ;The length of the sort message
	NewLine: db 10			 ;An easy to use new line character
	NewLineLen: equ $-NewLine	 ;the length of the newline character

section .text
	
global _start

_start:
	nop            		; This no-op keeps the debugger happy
Begin:
	mov eax,4		; Specify sys_write call
	mov ebx,1		; Specify File Descriptor 1: Standard output
	mov ecx,Intro		; Pass address of the character to write
	mov edx,IntroLen	; Pass number of chars to write
	int 80h			; Call sys_write...

	mov eax,3		; Specify sys_read call
	mov ebx,0		; Specify File Descriptor 0: Standard Input
	mov ecx,InputBuff	; Pass offset of the buffer to read to
	mov edx,BUFFLEN		; Pass number of bytes to read at one pass
	int 80h			; Call sys_read...

	dec eax			;decrement eax to skip the newline character
	mov esi, eax	        ;Move eax into esi for safe keeping
	mov ecx, esi		;move esi into ecx so we can safely use the length of the input string
	mov edx, LowercaseBuff	;move the address of the lwoercase buffer into edx for use later
	mov ebp, InputBuff	;move the address of the input buffer into ebp for later use aswell
	dec ecx			;decrement ecx. Useful to skip the blank space at the end of the input string
	
Scan:
	cmp byte [ebp+ecx], 'a'	;Compare element in buffer to 'a'
	jb NotLowercase
	cmp byte [ebp+ecx], 'z'	;Compare element in buffer to 'z'
	ja NotLowercase	

	mov eax, [ebp+ecx]	;if its a lowercase letter then move the letter into eax
	mov [edx+esi-1], eax	;move the letter into the correct memory location of Lowercase buffer
	dec edx			;decrement edx since that memory address is now being used
	dec ecx			;decrement ecx since we have less characters to process now
        cmp ecx, 0		;If there are more characters to process
        jge Scan		;jump to scan
	jmp BeginSort		;otherwise begin Selection sort
		
NotLowercase:
	dec esi			;decrement the total number of lowercase letters
	dec ecx			;decrement the current total of unprocessed letters
	cmp ecx, 0		;if there are more letters jump back to scan otherwise fall through and begin the sort
	jge Scan

BeginSort:
	mov eax,4		; Specify sys_write call
	mov ebx,1       ; Specify File Descriptor 1: Standard Input
        mov ecx,SortMsg ; Pass offset of the buffer to read to
        mov edx,SortLen	      ; Pass number of bytes to read at one pass
        int 80h		      ; Call sys_read...

	mov eax,4		; Specify sys_write call
        mov ebx,1	      ; Specify File Descriptor 1: Standard Input
        mov ecx,LowercaseBuff ; Pass offset of the buffer to read to
        mov edx,esi	      ; Pass number of bytes to read at one pass
        int 80h		      ; Call sys_read...

	mov eax,4		; Specify sys_write call
        mov ebx,1	; Specify File Descriptor 1: Standard output
        mov ecx,NewLine	; Pass address of the character to write
        mov edx,NewLineLen ; Pass number of chars to write
        int 80h		 ; Call sys_write...

	mov ecx, LowercaseBuff	;Take the address of lowercase buff and store it for use later
	mov edx, SortingBuff	;store the address of the sorting buff for later
	mov dword [i], 0	;set i to 0
	mov dword [j], 0	;set j to 0

SelectionSort:
	cmp esi, [i]		;compare esi to i and jump if i is less than esi
	ja ForLoop
	jmp Exit		;otherwise selection sort is done and exit the program
	
ForLoop:
	mov eax,4		; Specify sys_write call
	mov ebx,1       ; Specify File Descriptor 1: Standard output
        mov ecx,LowercaseBuff; Pass offset of the buffer to read to
	add ecx,[j]	     ;add the value of j to the buffer to get the next memory address needed for comparison
	mov edx,1 		; Pass number of chars to write
        int 80h		; Call sys_write...
	

AfterFoundLower:
	inc byte [j]		;increment j for the for loop
	cmp esi, [j]		;compare to see if the inner for loop needs to keep running
	jg ForLoop		;if it does then jump back to for loop

	mov eax,4		; Specify sys_write call
        mov ebx,1       ; Specify File Descriptor 1: Standard output
        mov ecx,NewLine ; Pass address of the character to write
        mov edx,NewLineLen ; Pass number of chars to write
	int 80h            ; Call sys_write...
	
	inc byte [i]		;increment i
	mov eax, [i]		;move i into eax so we can move eax into j
	mov [j], eax		;move eax into j
	jmp SelectionSort	;go back to selection sort

FoundLower:			;this was going to be where the sorting was going to occur but I kept running into problems so i deleted the code to start over. If this comment is still here when this is graded that means i was not able to correctly implement selection sort
	
Exit:	
	mov eax,1		; Code for Exit Syscall
	mov ebx,0		; Return a code of zero to Linux
	int 80H			; Make kernel call to exit program
	