CFLAGS=-std=c11 -Wall -O

TARGETS=example

all: $(TARGETS)

example: phone_no.o example.o
	$(CC) $(LDFLAGS) $(LDLIB) -o $@ $^

.PHONY: clean
clean:
	rm -rf *.o $(TARGETS)
