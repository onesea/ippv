all: compile
PKG=com/my/ippv
SRCDIR=src/main/java

OPTIONS=--target 13

compile:
	cd $(SRCDIR) && javac ${OPTIONS} $(PKG)/Main.java
run:
	java -cp $(SRCDIR)  com.my.ippv.Main
clean:
	rm -f ${SRCDIR}/${PKG}/*.class

.PHONY: run clean
