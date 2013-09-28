/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickKarTest;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Colorvisa
 */
public class Search {

    public static void main(String[] args) {

        String keywords = "Con duong \"den truong\" ta di roi \"ve dung khong\" em oi";
        System.out.println("Keywords: " + keywords);

        //contains the double quote
        List<String> exactKeyWordsList = new ArrayList<String>();
        String tempKeyWords = "";
        int nextPosition = 0;

        while (nextPosition != -1) {
            int first = keywords.indexOf("\"", nextPosition);
            int last = keywords.indexOf("\"", first + 1);
            if (nextPosition < first) {
                tempKeyWords += keywords.substring(nextPosition, first).trim() + " ";
            }
            if (first != - 1 && last != -1) {
                String exactWords = keywords.substring(first + 1, last).trim();
                if (!exactWords.isEmpty()) {
                    exactKeyWordsList.add(exactWords);
                }
                nextPosition = last + 1;
            } else {
                if (first == -1) {
                    tempKeyWords += keywords.substring(nextPosition).trim();
                } else {
                    tempKeyWords += keywords.substring(first + 1).trim();
                }
                nextPosition = -1;
            }
        }
        System.out.println("TempKeyWords: " + tempKeyWords.trim());
        for (int i = 0; i < exactKeyWordsList.size(); i++) {
            System.out.println("Words: " + exactKeyWordsList.get(i));
        }
    }
}
