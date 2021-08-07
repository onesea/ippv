SRCDIR=src/main/kotlin
PKG=com/my/ippv
KT=/root/kotlinc
CWD=$(shell pwd -P)
all: $(CWD)/phone.jar

OPTIONS=-jvm-target 17 -include-runtime 
#OPTIONS=-jvm-target 13 # good for runkt

$(CWD)/phone.jar:
	echo ${CWD}
	cd $(SRCDIR) && ${KT}/bin/kotlinc ${OPTIONS} $(PKG)/Main.kt $(PKG)/PhoneNo.kt -d $@

run:
	java -jar phone.jar
runj:
	java -cp phone.jar com.my.ippv.MainKt
runkt:
	${KT}/bin/kotlin -cp phone.jar com.my.ippv.MainKt

clean:
	rm -f ${SRCDIR}/${PKG}/*.class ${CWD}/phone.jar

.PHONY: run runj runkt clean
