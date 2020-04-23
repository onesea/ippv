CFLAGS=-std=c2x -Wall -O -g

TARGETS=example

all: cc_list.i $(TARGETS)

example: phone_no.o example.o
	$(CC) $(LDFLAGS) $(LDLIB) -o $@ $^

cc_list.i: ../country_code.txt
	awk 'BEGIN {print "// auto generated" } \
	    { \
	        printf "    \"%s\", \"%s\", \"", $$1, $$2; \
		for (i=3; i<=NF; ++i) { \
		    printf  "%s ", $$i \
		} \
	        printf "\",\n" \
	    } \
	    END {print "// end" }' $< > $@

.PHONY: clean
clean:
	rm -rf cc_list.i *.o $(TARGETS)
