CFLAGS=-std=c11 -Wall -O -g

TARGETS=example1 example2

all: $(TARGETS)

example1: phone_no.o example1.o
	$(CC) $(LDFLAGS) $(LDLIB) -o $@ $^

example2: phone_no.o example2.o
	$(CC) $(LDFLAGS) $(LDLIB) -o $@ $^

.PHONY: clean
clean:
	rm -rf *.o $(TARGETS)
