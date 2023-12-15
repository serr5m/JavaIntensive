#remove and create dir
rm -rf target rm -rf lib
mkdir target
mkdir lib

#download lib
curl https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar -o lib/jcommander-1.82.jar
curl https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.2/JCDP-4.0.2.jar -o lib/JCDP-4.0.2.jar

#extract files
cd target
jar xf ../lib/jcommander-1.82.jar
jar xf ../lib/JCDP-4.0.2.jar

#copy sources filea
cd ..
cp -r src/resources target/.


#compile
pwd
javac -classpath lib/jcommander-1.82.jar:lib/JCDP-4.0.2.jar src/java/edu/school21/printer/app/Program.java src/java/edu/school21/printer/logic/ImageToConsole.java src/java/edu/school21/printer/logic/Parser.java -d ./target

#create jar
jar -cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .

#run
java -jar target/images-to-chars-printer.jar --white=RED --black=GREEN