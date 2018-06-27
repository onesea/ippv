CFLAGS=-std=c99 -Wall -O

TARGETS=example

all: $(TARGETS)

example: phonenr.o example.o
	$(CC) $(LDFLAGS) $(LDLIB) -o $@ $^

.PHONY: clean
clean:
	rm -rf *.o $(TARGETS)
