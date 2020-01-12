all: ippv

ippv: main.o alg.o
	$(CC) -o $@ $^

%.o: %.asm
	nasm -f elf64 $< -w+all
clean:
	rm -f *.o ippv
