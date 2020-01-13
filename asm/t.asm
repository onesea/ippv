; nasm -f elf64 t.asm && gcc -no-pie t.o
section .text
    global main 
    extern printf, scanf

stack_size equ 40
main:
    sub     rsp, stack_size

    mov     rsi, rsp 
    lea     rdi, [rel scanf_str]
    xor     eax, eax 
    call    scanf
    cmp     rax, 1
    jne     .exit

    lea     rdi, [rel printf_str]
    mov     rsi, [rsp]
    xor     eax, eax 
    call    printf
.exit:
    add     rsp, stack_size
    ret

scanf_str:  db "%ld",0
printf_str: db "%ld",10,0
