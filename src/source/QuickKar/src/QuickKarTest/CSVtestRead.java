package QuickKarTest;

import java.io.File;
import java.util.Scanner;

public class CSVtestRead {

    private static final int SONGCODE = 0;
    private static final int SONGNAME = 1;
    private static final int RUBISH = 2;
    private static final int LYRIC = 3;
    private static final int COMPOSER_1 = 4;
    private static final int COMPOSER_2 = 5;

    public static void main(String[] args) {
//        readCSV(new File("test/ListVol43.csv"));
        readCSV(new File("test/ListVol1-42_split_1.csv"));
    }

    // CODE, "KHADF",KJsjkdhaf
    /*
     * CODE,        0
     * KHADF        1
     * ,KJsjkdhaf   2
     */

    // CODE, "HUEO KSE","asf, asfa, SAfa",KejfKeef
    /*
     * CODE,            0
     * HUEO KSE         1
     * ,                2
     * asf, asfa, SAfa  3
     * ,KejfKeef        4
     *
     */
    // CODE, "HUEO KSE", "asf, asfa, SAfa","KejfKeef, dsKHKS D"
    /*
     * CODE,                0
     * HUEO KSE             1
     * ,                    2
     * asf, asfa, SAfa      3
     * ,                    4
     * KejfKeef, dsKHKS D   5
     *
     */
    private static void readCSV(File file) {

        try {
            int line = 0;
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String t = scan.nextLine();
                String[] itemsProperties = t.split("\"");

//                itemsProperties[SONGCODE] = itemsProperties[SONGCODE].substring(0, 5);
//                itemsProperties[COMPOSER] = itemsProperties[COMPOSER].substring(1);

//                System.out.println(itemsProperties[SONGCODE].contains(","));
//                System.out.println(itemsProperties[COMPOSER].contains(","));

                if (itemsProperties.length == 6) {
                    itemsProperties[SONGCODE] = itemsProperties[SONGCODE].substring(0, 5);
                    //
                    System.out.println("###" + line);
                    System.out.println(itemsProperties[SONGCODE]);
                    System.out.println(itemsProperties[SONGNAME]);
                    System.out.println(itemsProperties[LYRIC]);
                    System.out.println(itemsProperties[COMPOSER_2]);
                } else if (itemsProperties.length == 5) {
                    itemsProperties[SONGCODE] = itemsProperties[SONGCODE].substring(0, 5);
                    itemsProperties[COMPOSER_1] = itemsProperties[COMPOSER_1].substring(1);
                    //
                    System.out.println("###" + line);
                    System.out.println(itemsProperties[SONGCODE]);
                    System.out.println(itemsProperties[SONGNAME]);
                    System.out.println(itemsProperties[LYRIC]);
                    System.out.println(itemsProperties[COMPOSER_1]);
                }
//                    else {
//                    itemsProperties[SONGCODE] = itemsProperties[SONGCODE].substring(0, 5);
//                    itemsProperties[RUBISH] = itemsProperties[RUBISH].substring(1);
//                    //
//                    System.out.println("###" + line);
//                    System.out.println(itemsProperties[SONGCODE]);
//                    System.out.println(itemsProperties[SONGNAME]);
//                    System.out.println(itemsProperties[RUBISH]);
//                }

//                System.out.println("###" + line);
//                System.out.println(itemsProperties[SONGCODE]);
//                System.out.println(itemsProperties[SONGNAME]);
//                System.out.println(itemsProperties[LYRIC]);
//                System.out.println(itemsProperties[COMPOSER]);
//                    if ((itemsProperties[LYRIC]=="")||(itemsProperties[COMPOSER])=="") {
//                        System.out.println(itemsProperties[SONGCODE]);
//                    }
                System.out.println();
                System.out.println();

                line++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
