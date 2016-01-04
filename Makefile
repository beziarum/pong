all:
	javac $(shell find . -name \*.java)

run: all
	java pong.Main

test: all
	java pong.Main_test

clean:
	rm $(shell find . -name \*.class)
