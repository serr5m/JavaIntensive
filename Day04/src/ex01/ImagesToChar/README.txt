#remove target before run the program
rm -rf target

#create a directory for output files
mkdir target

#compile our .java files
javac src/java/edu/school21/printer/app/Program.java  src/java/edu/school21/printer/logic/ImageToConsole.java  -d ./target

#copy resources files to target
cp -r src/resources target/.

#create jar
jar -cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .


#run
chmod +x target/images-to-chars-printer.jar
java -jar target/images-to-chars-printer.jar . 0







