java: TwitterBase.java
	javac TwitterBase.java -classpath .:./twitter4j-core-4.0.4.jar:./twitter4j-async-4.0.4.jar:./twitter4j-stream-4.0.4.jar:./twitter4j-media-support-4.0.4.jar:./twitter4j-examples-4.0.4.jar
	
push: TwitterBase.java
	git add .
	echo "Enter commit message: "; \
	read msg;\
	echo "$$msg";\
	git commit -m "$$msg";\
	git push

test: CanadaTRC.java TwitterBase.java
	export CLASSPATH=.:./twitter4j-core-4.0.4.jar:./twitter4j-stream-4.0.4.jar
	javac CanadaTRC.java -classpath .:./twitter4j-core-4.0.4.jar:./twitter4j-async-4.0.4.jar:./twitter4j-stream-4.0.4.jar:./twitter4j-media-support-4.0.4.jar:./twitter4j-examples-4.0.4.jar
	#java CanadaTRC -classpath .:./twitter4j-core-4.0.4.jar:./twitter4j-async-4.0.4.jar:./twitter4j-stream-4.0.4.jar:./twitter4j-media-support-4.0.4.jar:./twitter4j-examples-4.0.4.jar
	java CanadaTRC -classpath .:./twitter4j-core-4.0.4.jar:./twitter4j-stream-4.0.4.jar
	

run: CanadaTRC.java TwitterBase.java
	java CanadaTRC -classpath .:./twitter4j-core-4.0.4.jar:./twitter4j-async-4.0.4.jar:./twitter4j-stream-4.0.4.jar:./twitter4j-media-support-4.0.4.jar:./twitter4j-examples-4.0.4.jar
