#remove target before run the program
rm -rf target

#create a directory for output files
mkdir target

#compile our .java files
javac -d target src/java/edu/school21/printer/logic/ImageToConsole.java  src/java/edu/school21/printer/app/Program.java

#run the program
java -classpath target src.java.edu.school21.printer.app.Program . 0 /Users/sherlynt/Java_Bootcamp.Day04-1/src/ex00/ImagesToChar/src/it.bmp










