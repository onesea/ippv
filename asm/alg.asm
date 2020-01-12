section .sdfsdfrodata
str_fmt:	db "inserted: %s %s.",10,0  ; 10 is LF

struc node;, -88 ; base addr: -88
	.value:	resd	1
	.iso:	resb	4
	.child:	resq	10
endstruc

tree:
	istruc node
		at node.value, dd 0
		at node.iso,   db 0,0,0,0
		at node.child, dq 0,0,0,0,0,0,0,0,0,0
	iend

section .bss
tree_uninit:
	istruc node
		at node.value, resd 1
		at node.iso,   resb 4
		at node.child, resq 10
	iend

section .text
global insert,match
extern printf,malloc

insert:
	mov rdx,rsi
	mov rsi,rdi
	mov rdi, str_fmt
	xor eax, eax
	call printf WRT ..plt
	ret

match:
	ret
