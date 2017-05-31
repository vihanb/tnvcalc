SRC=$(shell find ./src -name "*.java")

default: $(SRC)
	javac -target 7 -source 7 -d ./build $(SRC)
	jar cvfm TNVCalc.jar Manifest.txt -C build/ . -C src/ Calculator.fxml -C src/ Calculator.css
