	.file	"hw2_search.c"
	.section	.rodata.str1.1,"aMS",@progbits,1
.LC0:
	.string	"Fork Failed. :( "
.LC1:
	.string	"Found %d at index %d!\n"
	.text
	.p2align 4,,15
	.globl	binarySearch
	.type	binarySearch, @function
binarySearch:
.LFB50:
	.cfi_startproc
	pushq	%r14
	.cfi_def_cfa_offset 16
	.cfi_offset 14, -16
	movq	%rdx, %r14
	pushq	%r13
	.cfi_def_cfa_offset 24
	.cfi_offset 13, -24
	movl	%ecx, %r13d
	pushq	%r12
	.cfi_def_cfa_offset 32
	.cfi_offset 12, -32
	movl	%edi, %r12d
	pushq	%rbp
	.cfi_def_cfa_offset 40
	.cfi_offset 6, -40
	movl	%esi, %ebp
	pushq	%rbx
	.cfi_def_cfa_offset 48
	.cfi_offset 3, -48
	.p2align 4,,10
	.p2align 3
.L2:
	leal	0(%rbp,%r12), %eax
	movl	%eax, %ebx
	shrl	$31, %ebx
	addl	%eax, %ebx
	sarl	%ebx
	cmpl	%r12d, %ebp
	je	.L3
	call	fork
	testl	%eax, %eax
	js	.L10
	.p2align 4,,6
	je	.L7
	leal	1(%rbx), %r12d
	.p2align 4,,5
	jmp	.L2
	.p2align 4,,10
	.p2align 3
.L7:
	movl	%ebx, %ebp
	jmp	.L2
	.p2align 4,,10
	.p2align 3
.L3:
	movslq	%ebp, %rax
	cmpl	%r13d, (%r14,%rax,4)
	je	.L11
	popq	%rbx
	.cfi_remember_state
	.cfi_def_cfa_offset 40
	popq	%rbp
	.cfi_def_cfa_offset 32
	popq	%r12
	.cfi_def_cfa_offset 24
	popq	%r13
	.cfi_def_cfa_offset 16
	popq	%r14
	.cfi_def_cfa_offset 8
	ret
	.p2align 4,,10
	.p2align 3
.L10:
	.cfi_restore_state
	popq	%rbx
	.cfi_remember_state
	.cfi_def_cfa_offset 40
	popq	%rbp
	.cfi_def_cfa_offset 32
	popq	%r12
	.cfi_def_cfa_offset 24
	popq	%r13
	.cfi_def_cfa_offset 16
	popq	%r14
	.cfi_def_cfa_offset 8
	movl	$.LC0, %edi
	jmp	puts
.L11:
	.cfi_restore_state
	popq	%rbx
	.cfi_def_cfa_offset 40
	movl	%ebp, %ecx
	movl	%r13d, %edx
	movl	$.LC1, %esi
	popq	%rbp
	.cfi_def_cfa_offset 32
	popq	%r12
	.cfi_def_cfa_offset 24
	popq	%r13
	.cfi_def_cfa_offset 16
	popq	%r14
	.cfi_def_cfa_offset 8
	movl	$1, %edi
	xorl	%eax, %eax
	jmp	__printf_chk
	.cfi_endproc
.LFE50:
	.size	binarySearch, .-binarySearch
	.section	.rodata.str1.8,"aMS",@progbits,1
	.align 8
.LC2:
	.string	"Usage: hw2_search <numberToSearchFor> "
	.section	.text.startup,"ax",@progbits
	.p2align 4,,15
	.globl	main
	.type	main, @function
main:
.LFB51:
	.cfi_startproc
	subq	$72, %rsp
	.cfi_def_cfa_offset 80
	cmpl	$2, %edi
	movl	$0, (%rsp)
	movl	$1, 4(%rsp)
	movl	$2, 8(%rsp)
	movl	$3, 12(%rsp)
	movl	$4, 16(%rsp)
	movl	$5, 20(%rsp)
	movl	$6, 24(%rsp)
	movl	$7, 28(%rsp)
	movl	$8, 32(%rsp)
	movl	$9, 36(%rsp)
	movl	$10, 40(%rsp)
	movl	$11, 44(%rsp)
	movl	$12, 48(%rsp)
	movl	$13, 52(%rsp)
	movl	$14, 56(%rsp)
	movl	$15, 60(%rsp)
	jne	.L15
	movq	8(%rsi), %rdi
	movl	$10, %edx
	xorl	%esi, %esi
	call	strtol
	movq	%rsp, %rdx
	movl	%eax, %ecx
	movl	$13, %esi
	xorl	%edi, %edi
	call	binarySearch
	addq	$72, %rsp
	.cfi_remember_state
	.cfi_def_cfa_offset 8
	ret
.L15:
	.cfi_restore_state
	movl	$.LC2, %edi
	call	puts
	movl	$1, %edi
	call	exit
	.cfi_endproc
.LFE51:
	.size	main, .-main
	.ident	"GCC: (Ubuntu 4.8.4-2ubuntu1~14.04) 4.8.4"
	.section	.note.GNU-stack,"",@progbits
