; nasm -f elf64 t.asm && gcc -no-pie t.o
section .text
    global main 
    extern printf, scanf

frame_size equ 40
main:
    sub     rsp,  frame_size

    mov     rsi, rsp 
    lea     rdi, [rel scanf_str]
    xor     eax, eax 
    call    scanf
    cmp     rax, 1
    jne     .exit

    lea     rdi, [rel format_str]
    mov     rsi, [rsp]
    xor     eax, eax 
    call    printf
.exit:
    add     rsp,  frame_size
    ret

scanf_str: db "%ld",0
format_str: db "%ld",10,0
