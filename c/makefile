CFLAGS=-std=c11 -Wall -O -g

TARGETS=example leading_digits

all: $(TARGETS)

example: phone_no.o example.o
	$(CC) $(LDFLAGS) $(LDLIB) -o $@ $^

leading_digits: phone_no.o leading_digits.o
	$(CC) $(LDFLAGS) $(LDLIB) -o $@ $^

.PHONY: clean
clean:
	rm -rf *.o $(TARGETS)
