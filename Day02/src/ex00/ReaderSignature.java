package ex00;

import java.io.*;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReaderSignature {
    String fileSeparator = File.separator;
    private final String pathAllSignatures = "src" + fileSeparator + "ex00" + fileSeparator + "signatures.txt";
    private final String pathResultFile = "src" + fileSeparator + "ex00" + fileSeparator + "result.txt";
    //    private String pathToTheFile;
    private Map<String, String> signatureMap;

    void findSignatures() throws IOException {
        createMap();

        while (true) {
            try {
                String signatureFile = readFileSignature();
                try (FileWriter fileWriter = new FileWriter(pathResultFile, true)) {
                    String resultAnalysisSignature = "UNDEFINED";
                    for (Map.Entry<String, String> entry : signatureMap.entrySet()) {
                        if (signatureFile.contains(entry.getValue())) {
                            resultAnalysisSignature = "PROCESSED";
                            fileWriter.write(entry.getKey());
                            fileWriter.write("\n");
                            break;
                        }
                    }
                    System.out.println(resultAnalysisSignature);
                }
            } catch (Exception ex) {
                System.out.println("File not found");
            }
        }
    }

    String readFileSignature() throws IOException {
        String pathFile = readPath();
        File file = new File(pathFile);
        int lengthSignatures = 8;
        String hexBytes;

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] signaturesBytes = new byte[lengthSignatures];
            fileInputStream.read(signaturesBytes);
            hexBytes = bytesToHexString(signaturesBytes);
        }
        return hexBytes.toUpperCase();
    }


    public static String bytesToHexString(byte[] byteArray) {
        BigInteger bigInt = new BigInteger(1, byteArray);
        return bigInt.toString(16);
    }

    String readPath() {
        Scanner scanner = new Scanner(System.in);
        String pathToTheFile = scanner.nextLine();
        if (pathToTheFile.equals("42")) {
            System.out.print("Exit");
            System.exit(-1);
        }
        return pathToTheFile;
    }

    void createMap() throws IOException {
        signatureMap = new HashMap<>();
        FileInputStream fileInputStream = new FileInputStream(pathAllSignatures);
        Scanner scanner = new Scanner(fileInputStream);
        while (scanner.hasNextLine()) {
            String tmp = scanner.nextLine();
            String[] arr = tmp.split(",");
            signatureMap.put(arr[0], arr[1].replaceAll(" ", ""));
        }
    }
}
