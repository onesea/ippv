CFLAGS=-std=c18 -Wall -O -g

TARGETS=example

all: $(TARGETS)

example: phone_no.o example.o
	$(CC) $(LDFLAGS) $(LDLIB) -o $@ $^

.PHONY: clean
clean:
	rm -rf *.o $(TARGETS)
