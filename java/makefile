PKG=com/my/ippv
CWD=$(shell pwd)
SRCDIR=src/main/java

OPTIONS=--source 15 --target 15

JDK=/opt/jdk-15
JAR=${JDK}/bin/jar
JAVA=${JDK}/bin/java
JAVAC=${JDK}/bin/javac

all: ${CWD}/phone.jar

$(CWD)/phone.jar:
	cd $(SRCDIR) && ${JAVAC} ${OPTIONS} $(PKG)/Main.java
	cd $(SRCDIR) && ${JAR} -c --main-class=com.my.ippv.Main -f $@ ${PKG}/*.class
run:
	${JAVA} -cp $(SRCDIR)  com.my.ippv.Main
runj:
	${JAVA} -jar ${CWD}/phone.jar
clean:
	rm -f ${SRCDIR}/${PKG}/*.class ${CWD}/phone.jar

.PHONY: run clean
