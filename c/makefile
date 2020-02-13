CFLAGS=-std=c18 -Wall -O -g

TARGETS=example

all: cc_list.i $(TARGETS)

example: phone_no.o example.o
	$(CC) $(LDFLAGS) $(LDLIB) -o $@ $^

cc_list.i: ../country_code.txt
	awk 'BEGIN {print "// auto generated" } { print "    \"" $$1  "\", \""  $$2 "\"," } END {print "// end" }' $< > $@

.PHONY: clean
clean:
	rm -rf cc_list.i *.o $(TARGETS)
