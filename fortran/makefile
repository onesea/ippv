all: ippv

ippv: main.o phone.o
	gfortran -o $@ $^

main.o: main.f08
	gfortran -c $^
phone.o: phone.f08
	gfortran -c $^
.PHONY: clean
clean:
	rm -f ippv *.o
