;  Executable name : proj3
;  Created date    : 10/07/2014
;  Author          : Joseph Wenzel
;  Class	    : Sadeghian T/Thr 11:30 - 12:45
;  Description     : This program will promt the user for 5 ID's and 5 names.Id's
;must be 2 digits. The names must be at max 9 characters in length. After inputting, the
;program will ask for an ID. The user then gives an ID. If it matches with something they
;have already entered they will be allowed to change the name. If it does not match an
;error message will print. If it is 00, the program will print out all names and ID's and
;then exit.

SECTION .bss			;Section containing uninitialized data
	NameBuff1 resb 10	;BUffer to hold the first name
	NameBuff2 resb 10	;Buffer to hold the second name
	NameBuff3 resb 10	;Buffer to hold the third name
	NameBuff4 resb 10	;Buffer to hold the fourth name
	NameBuff5 resb 10	;buffer to hold the fifth name

	ID1	  resb 2	;Buffer to hold the first id
	ID2       resb 2	;buffer to hold the second id
	ID3       resb 2	;Buffer to hold the third id
	ID4       resb 2	;buffer to hold the fourth id
	ID5       resb 2	;buffer to hold the fifth id

	InputID	  resb 2	;buffer to hold the user given id after 5 ids have been given
	
SECTION .data			; Section containing initialised data
	InputMsg:	db "Enter a name for that ID: " ;asks for a name to associate to an id
	InputMsgLen:	equ $-InputMsg			;the length of the previous string

	InputId:	db "Enter an ID: " ;asks for an ID from the user
	InputIdLen:	equ $-InputId	   ;The length of the previous string

	CheckInputId:	db "Enter an ID to check: " ;Asks for the user to give an id after giving 5 ids
	CheckLen:	equ $-CheckInputId	    ;length of the string

	QuitName:	db "   Name: " ;the name prints out when the user enters 00
	QuitNameLen:	equ $-QuitName	 ;the length of the previous string

	QuitID:		db "ID: " ;The id prints out after the user puts in 00
	QuitIDLen:	equ $-QuitID ;the length of the previoius string

	NoMatch:	db "The inputted ID matched no previously given ID." ;Error message used in the program
	NoMatchLen:	equ $-NoMatch ;length of the error message

	Match:		db "Enter a new name for that ID: " ;prompt when a user puts in a valid id
	MatchLen:	equ $-Match			    ;length of the previous string
	
	newLine:	db 10	      ;newline character
	newLineLen:	equ $-newLine ;length of the newline character
SECTION .text			;Section containing code

global _start			;Linker needs this to find the entry point!

;-------------------------------------------------------------------------
; getInput: 	Populate an ID buffer and its respective name buffer
; IN: 		ID buffer, NameBuffer
; RETURNS:	Nothing
; MODIFIES: 	eax, ebx, ecx, edx, the given name and ID buffers
; DESCRIPTION:	prompts to input what the user wants the ID and name to be for that ID.
;It then puts those names and ID's in their respective buffers.
%macro getInput 2
	mov eax,4               ;Specify sys_write call
	mov ebx,1               ;Specify File Descriptor 1: Standard Output
	mov ecx,InputId        	;Pass offset of the message
	mov edx,InputIdLen     	;Pass the length of the message
	int 80H                 ;Make kernel call

	mov eax,3               ;Specify sys_read call
	mov ebx,0               ;Specify File Descriptor 0: Standard Input
	mov ecx,%2       	;Pass offset of the buffer to read to
	mov edx,4              	;Tell sys_read to read three char from stdin
	int 80h                 ;Call sys_read	

	mov eax,4               ;Specify sys_write call
	mov ebx,1               ;Specify File Descriptor 1: Standard Output
	mov ecx,InputMsg        ;Pass offset of the message
	mov edx,InputMsgLen     ;Pass the length of the message
	int 80H                 ;Make kernel call

	mov eax,3               ;Specify sys_read call
	mov ebx,0               ;Specify File Descriptor 0: Standard Input
	mov ecx,%1       	;Pass offset of the buffer to read to
	mov edx,9              	;Tell sys_read to read ten char from stdin
	int 80h                 ;Call sys_read
%endmacro

;-------------------------------------------------------------------------
; printTuple: Prints out the id and name from the given name and id buffers
; IN: 		ID buffer, NameBuffer
; RETURNS:	Nothing
; MODIFIES: 	eax, ebx, ecx, edx
; DESCRIPTION:Takes in a name and in ID buffer to print. It then prints them out
%macro printTuple 2
	
        mov eax,4	  	;Specify sys_write call
        mov ebx,1		;Specify File Descriptor 1: Standard Output
        mov ecx,QuitID 		;Pass offset of the message
        mov edx,QuitIDLen 	;Pass the length of the message
        int 80H		    	;Make kernel call

 	mov eax,4       	;Specify sys_write call
        mov ebx,1       	;Specify File Descriptor 1: Standard Output
        mov ecx,%2 		;Pass offset of the message
        mov edx,2 		;Pass the length of the message
        int 80H		  	;Make kernel call

	mov eax,4               ;Specify sys_write call
        mov ebx,1		;Specify File Descriptor 1: Standard Output
	mov ecx,QuitName	;Pass offset of the message
        mov edx,QuitNameLen 	;Pass the length of the message
        int 80H		   	;Make kernel call

	mov eax,4               ;Specify sys_write call
        mov ebx,1       	;Specify File Descriptor 1: Standard Output
        mov ecx,%1 		;Pass offset of the message
        mov edx,10 		;Pass the length of the message
        int 80H		    	;Make kernel call	
%endmacro

;The purpose of this for loop is to make everything in the buffer that is
;placed into eax 0. ebx is used as a loop control variable. This
;function modifies eax and ebx 
forloop:
	mov byte [eax+ebx], 0	;move zero into the position in the buffer
	inc ebx			;increment the loop control variable
        cmp ebx, 10		;compare to see if ebx is less than 10 since the name can be a max of 9 characters we need to clear out those 9 characters
        jle forloop		;repeat until the whole buffer is zero
	ret			;return to FoundMatch when done
	
;-------------------------------------------------------------------------
; FoundMAtch: Changes the name for a matched ID the user inputted
; IN: 		NameBuffer
; RETURNS:	Nothing
; MODIFIES: 	eax, ebx, ecx, edx
; DESCRIPTION:Takes in a new name to put in the name buffer
%macro FoundMatch 1
	mov eax, %1		;Move the address of the buffer that needs to be zeroed into eax
	mov ebx, 0		;Move 0 into ebx since ebx is my loop control variable
	
	call forloop		;Begin the forloop that will zero out the buffer placed in eax
	
        mov eax,4       	;Specify sys_write call
        mov ebx,1       	;Specify File Descriptor 1: Standard Output
        mov ecx,Match 		;Pass offset of the message
        mov edx,MatchLen 	;Pass the length of the message
        int 80H		  	;Make kernel call

        mov eax,3               ;Specify sys_read call
        mov ebx,0		;Specify File Descriptor 0: Standard Input
        mov ecx,%1		;Pass offset of the buffer to read to
        mov edx,10		;Tell sys_read to read ten char from stdin
        int 80h			;Call sys_read

	ret			;return to promptForID
%endmacro	

;-------------------------------------------------------------------------
; getInputID: Asks the user to put in an ID after inputting 5 names and ID's
; IN: 		Nothing
; RETURNS:	Nothing
; MODIFIES: 	InputID buffer
; DESCRIPTION:Takes in an ID to check	
getInputID:
	push eax		;save the current registers
	push ebx		;save ebx for later
	push ecx		;save ecx for later
	push edx		;save edx for later
	
	mov eax,4		;Specify sys_write call
        mov ebx,1		;Specify File Descriptor 1: Standard Output
        mov ecx,CheckInputId 	;Pass offset of the message
        mov edx,CheckLen 	;Pass the length of the message
        int 80H		    	;Make kernel call

        mov eax,3		;Specify sys_read call
        mov ebx,0		;Specify File Descriptor 0: Standard Input
	mov ecx,InputID		;Pass offset of the buffer to read to
	mov edx,4		;Tell sys_read to read ten char from stdin
	int 80h			;Call sys_read

	pop edx			;restore edx
	pop ecx			;restore ecx
	pop ebx			;restore ebx
	pop eax			;restore eax

	ret			;return to promptForID

;-------------------------------------------------------------------------
; checkInputID: Checks the input ID against all 5 of the other ID's
; IN: 		Nothing
; RETURNS:	Nothing
; MODIFIES: 	eax, ecx
; DESCRIPTION: Checks the given input id against all of the other ID's already given.
;If it does not find a match it will print an error.

checkInputID:
	mov ax, [InputID]	;move the contents of InputID into ax so we can compare it

;Check if the input is zero 
.checkZero:
	cmp word ax, '00'	;compare ax to a string of two zeroes to see if the user inputted 00 to quit the program
	je Exit			;If it was two zeroes jump to the exit
	jmp .notZeroCompareToID1 ;go and begin comparing to ID1 since it isnt 00

;compare the input to the first given ID 
.notZeroCompareToID1:
	mov cx, [ID1]		;move the contents of ID1 into cx for comparison
	cmp word ax,cx		;compare InputID to ID1
	jne .notZeroCompareToID2 ;jump to compare to ID2 if theyre not equal
	FoundMatch NameBuff1	 ;change the name in NameBUff1 if theyre equal

;compare the input to the second given ID
.notZeroCompareToID2:
	mov cx, [ID2]		;move the contents of ID2 into cx for comparison
	cmp word ax, cx		;compare InputID to ID2
	jne .notZeroCompareToID3 ;jump to compare to ID2 if theyre not equal
	FoundMatch NameBuff2	 ;change the name in NameBUff2 if theyre equal

;compare the input to the third given ID
.notZeroCompareToID3:
	mov cx, [ID3]		;move the contents of ID3 into cx for comparison
	cmp word ax, cx		;compare InputID to ID3
	jne .notZeroCompareToID4 ;jump to compare to ID3 if theyre not equal
	FoundMatch NameBuff3	 ;change the name in NameBUff3 if theyre equal

;compare the input to the fourth given ID
.notZeroCompareToID4:
	mov cx, [ID4]		;move the contents of ID4 into cx for comparison
	cmp word ax, cx		;compare InputID to ID4
	jne .notZeroCompareToID5 ;jump to compare to ID4 if theyre not equal
	FoundMatch NameBuff4	 ;change the name in NameBUff4 if theyre equal

;compare the input to the fifth given ID
.notZeroCompareToID5:
        mov cx, [ID5]		;move the contents of ID5 into cx for comparison
        cmp word ax, cx		;compare InputID to ID5
        jne .noMatchesError	;User entered an invalid ID
	FoundMatch NameBuff5	;change the name in NameBUff5 if theyre equal

.noMatchesError:
        mov eax,4		;Specify sys_write call
        mov ebx,1		;Specify File Descriptor 1: Standard Output
        mov ecx,NoMatch		;Pass offset of the message
        mov edx,NoMatchLen 	;Pass the length of the message
        int 80H		   	;Make kernel call

        mov eax,4		;Specify sys_write call
        mov ebx,1       	;Specify File Descriptor 1: Standard Output
        mov ecx,newLine		;Pass offset of the message
        mov edx,newLineLen 	;Pass the length of the message
        int 80H		   	;Make kernel call
	
	ret			;Return to promptForID

;-------------------------------------------------------------------------
; printAll: print all the names and ID's
; IN: 		Nothing
; RETURNS:	Nothing
; MODIFIES: 	Nothing
; DESCRIPTION: Prints all the names and ID's by calling printTuple 5 times each with a 
;different combination of nameBuff and ID
printAll:
	printTuple NameBuff1, ID1 ;Print out NameBUff1 and ID1
	printTuple NameBuff2, ID2 ;Print out NameBUff2 and ID2
	printTuple NameBuff3, ID3 ;Print out NameBUff3 and ID3
	printTuple NameBuff4, ID4 ;Print out NameBUff4 and ID4
	printTuple NameBuff5, ID5 ;Print out NameBUff5 and ID5
	
	ret			;Return to Exit
	
_start:
	nop            		;This no-op keeps the debugger happy

	getInput NameBuff1, ID1	;Ask the user for the first id and name combo
	getInput NameBuff2, ID2	;Ask the user for the second id and name combo
	getInput NameBuff3, ID3	;Ask the user for the third id and name combo
	getInput NameBuff4, ID4	;Ask the user for the fourth id and name combo
	getInput NameBuff5, ID5	;Ask the user for the fifth id and name combo

promptForID:
	call getInputID		;Ask the user to input an ID to check against the 5 previously given ID's
	call checkInputID	;Begin the process of checking the ID
	jmp promptForID		;Keep looping until 00 is entered
	
Exit:
	call printAll		;Print out all names and ID's when the program ends
	mov eax,1		;Code for Exit Syscall
	mov ebx,0		;Return a code of zero to Linux
	int 80H			;Make kernel call to exit program