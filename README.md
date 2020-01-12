#to run the application
1. execute one the following command
mvn clean package or mvn -DskipTests=true clean package 

2. execute 
java -Djline.WindowsTerminal.directConsole=false -Djline.terminal=jline.UnsupportedTerminal -jar target/hometask-skeleton-1.0-SNAPSHOT.jar
