struc node;, -88 ; base addr: -88
	.value:	resq	1
	.iso:	resq	1
	.child:	resq	10
endstruc

section .rodata
str_fmt:	db "inserted: %s %s.",10,0  ; 10 is LF

section .data
iso_str:	db "USA",0

section .bss
tree:
	istruc node
		at node.value, resq 1
		at node.iso,   resq 1
		at node.child, resq 10
	iend

section .text
extern printf,malloc
global insert,match,init_tree

init_tree:
	; todo: simplify 
	mov 	qword [rel tree   ],0 ; value & iso
	mov 	qword [rel tree+ 8],0
	mov 	qword [rel tree+16],0
	mov 	qword [rel tree+24],0
	mov 	qword [rel tree+32],0
	mov 	qword [rel tree+40],0
	mov 	qword [rel tree+48],0
	mov 	qword [rel tree+56],0
	mov 	qword [rel tree+64],0
	mov 	qword [rel tree+72],0
	mov 	qword [rel tree+80],0
	mov 	qword [rel tree+88],0
	ret

insert:
	mov	rdx, rsi
	mov	rsi, rdi
	lea	rdi, [rel str_fmt]
	xor	eax, eax
	call	printf WRT ..plt
	ret

# return: rsi: cc, rdx: ld, rcx: ac, r8: iso note: suitable for passing to printf directly
match:
	mov	rsi, 2
	mov	rdx, 5
	mov	rcx, 9
	mov	r8, iso_str
	ret
