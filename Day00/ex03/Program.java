package Day00.ex03;

import java.util.Scanner;

public class Program {

    static final int MAX_WEEKS = 18;
    static final int MIN_DAYS = 5;
    static final int MAX_GRADE = 9;


    private static boolean readGrade(Scanner scanner) {
        int minGrade = 0;
        while(scanner.hasNextInt()) {
            int tmpGrade = scanner.nextInt();
            if(tmpGrade > MAX_GRADE) {
                return false;
            }

        }
        return true;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long storageOfGrades = 0;
        while (true) {
            String string = scanner.next();
            if (string.equals("Week")) {
                int weekNumber = scanner.nextInt();
                if (weekNumber <= MAX_WEEKS) {  //good

                    int minGrade = 0;
                    while (scanner.hasNextInt())  {
                        int tmpGrade = scanner.nextInt();
                        if (tmpGrade >= MAX_GRADE) {
                            break;
                        }
                        if (minGrade == 0) {
                            minGrade = tmpGrade;
                        }
                        if (minGrade < tmpGrade) {
                            minGrade = tmpGrade;
                        }

//                        if(storageOfGrades == 0){
//                            storageOfGrades = tmpGrade;
//                        } else {
//                            storageOfGrades *= 10;
//                            storageOfGrades += tmpGrade;
//                        }
                    }
                }
            } else {
                System.out.println("govno");
                break;
            }

            System.out.println(storageOfGrades);

        }
    }
}
