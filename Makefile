SRC=$(shell find ./src -name "*.java")

default: $(SRC)
	javac -d ./build $(SRC)
	#jar cvfm TNVCalc.jar Manifest.txt -C build/ .
	jar cvfm TNVCalc.jar Manifest.txt -C build/ . -C src/ Calculator.fxml
