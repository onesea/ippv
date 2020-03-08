PKG=com/my/ippv
CWD=$(shell pwd)
SRCDIR=src/main/java

OPTIONS=--target 13

all: ${CWD}/phone.jar

$(CWD)/phone.jar:
	cd $(SRCDIR) && javac ${OPTIONS} $(PKG)/Main.java
	cd $(SRCDIR) && jar -c --main-class=com.my.ippv.Main -f $@ ${PKG}/*.class
run:
	java -cp $(SRCDIR)  com.my.ippv.Main
run-jar:
	java -jar ${CWD}/phone.jar
clean:
	rm -f ${SRCDIR}/${PKG}/*.class ${CWD}/phone.jar

.PHONY: run clean
