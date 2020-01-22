section .text
    global main
    extern printf, scanf
    extern init_tree,insert, match

    stack_size equ 48
init:
    sub     rsp, stack_size

    call    init_tree

    mov     r12, 10
.prompt_for_input:
    lea     rdi, [rel prompt_str]
    xor     eax, eax
    call    printf WRT ..plt
    ;jmp .exit_proc
    lea     rdi, [rel scanf_fmt]
    mov     rsi, rsp
    mov     rdx, rsp
    add     rdx, 8
    xor     eax, eax
    call    scanf WRT ..plt
    cmp     eax, 2
    jne     .exit_init
    mov     rdi, rsp
    mov     rsi, rsp
    add     rsi, 8
    xor     eax, eax
    call    insert

    dec     r12
    jnz     .prompt_for_input

.exit_init:
    add     rsp, stack_size
    ret

run:
    lea     rdi, [rel phone_no]
    xor     eax, eax
    call    match
    lea     rdi, [rel cc_fmt]
    call    printf WRT ..plt
    ret

main:
    call init
    call run
    ret

section .rodata
    phone_no:   db "8618011112222",0
    prompt_str:	db "Enter cc and iso code: ",0
    printf_fmt:	db "The input is: %s %s.",10,0
    scanf_fmt:	db "%s %s",0
    cc_fmt:	db 10,"%ld %ld %ld %s",10,0
