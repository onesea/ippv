all: compile
PKG=com/my/ippv
SRCDIR=src/main/java
compile:
	cd $(SRCDIR) && javac $(PKG)/Main.java
	cd $(SRCDIR) && javac $(PKG)/Result.java
	cd $(SRCDIR) && javac $(PKG)/PhoneNo.java
run:
	java -cp $(SRCDIR)  com.my.ippv.Main
