/**
 * Course: Software Development: Process & Tools
 * Lecturer: Quang Tran
 * Assignment: 2-2012B
 * Assignment Title: QuickKar2012
 *
 *      RMIT International University Vietnam
 * Bachelor of Information Technology - Application
 *
 */
package QuickKarModel.facade;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Contain some methods to process data when importing data from .csv files.
 *
 * @author vuongdothanhhuy
 */
public class QuickKarMethod {

    // CONST of all vowels in Vietnamese.
    private static String[][] vowels = new String[][]{{"[áàảãạâấầẫậăắằẵặ]", "a"},
        {"[éèẻẽẹêếềểễệ]", "e"},
        {"[íìỉĩị]", "i"},
        {"[óòỏõọôốổồỗộơớờỡợ]", "o"},
        {"[úùủũụưứừữự]", "u"},
        {"[ýỳỷỹỵ]", "y"},
        {"[đ]", "d"}};
    private static DecimalFormat decimalFormat = new DecimalFormat();
    private static boolean setMinimumFraction = false;

    public static String[] removeDuplicateWords(String keywords) {

        //Using set to remove duplicate key wordss
        Set<String> set = new HashSet<String>(Arrays.asList(keywords.split("\\s")));
        String[] tag = new String[set.size()];
        set.toArray(tag);

        return tag;
    }

    //process String
    /**
     * Remove all accents and unnecessary marks in Vietnamese words.
     *
     * @param input
     * @param removeSymbolOrSpecialCharacter
     * @return
     */
    public static String stringProcess(String input, boolean removeSymbolOrSpecialCharacter) {

        //replace Vietnamese vowels
        for (int i = 0; i < vowels.length; i++) {
            input = input.replaceAll(vowels[i][0], vowels[i][1]);
        }
        if (removeSymbolOrSpecialCharacter) {
            input = input.replaceAll("[.,:/-]", "");
        }
        return input;
    }

    public static String formatDouble(double value) {
        
        if (!setMinimumFraction) {
            decimalFormat.setMinimumFractionDigits(6);
            setMinimumFraction = true;
        }
        return decimalFormat.format(value);
    }
}
