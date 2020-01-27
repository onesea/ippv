; nasm -f elf64 t.asm && gcc -no-pie t.o
section .text
    global main 
    extern printf, scanf

stack_size equ 48

main:
    sub     rsp, stack_size

    lea     rdi, [rel fmt_str]
    mov     rsi, rsp 
    xor     eax, eax 
    call    scanf

    add     rsp, stack_size
    ret

fmt_str:  db "%ld",0
