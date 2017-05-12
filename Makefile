SRC=$(shell find . -name "*.java")

default: $(SRC)
	javac -d ./build $(SRC)
	jar cvf VSLCalc.jar ./build/*
