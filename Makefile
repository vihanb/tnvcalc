SRC=$(shell find ./src -name "*.java")

default: $(SRC)
	javac -target 1.7 -d ./build $(SRC)
	jar cvf VSLCalc.jar ./build/*
