SRC=$(shell find ./src -name "*.java")

default: $(SRC)
	javac -d ./build $(SRC)
	jar cvfm TNVCalc.jar Manifest.txt -C build/ .
