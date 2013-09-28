/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package QuickKarTest;

/**
 *
 * @author Colorvisa
 */
public class NewClass {

    public static void main(String[] args) {
        String[][] vowels = new String[][]{{"[áàảãạâấầẫậăắằẵặÁÀẢÃẠÂẤẦẪẬĂẮẰẴẶ]", "a"},
                                               {"[éèẻẽẹếềệÉÈẼẺẸẾỀỄỆ]", "e"},
                                               {"[íìỉĩịÍÌỈĨỊ]", "i"},
                                               {"[óòỏõọôốồỗộơớờỡợÓÒỎÕỌÔỐỒỖỘƠỚỜỠỢ]", "o"},
                                               {"[úùủũụưứừữựÚÙỦŨỤƯỨỪỮỰ]", "u"},
                                               {"[ýỳỷỹỵÝỲỶỸỴ]", "y"},
                                               {"[đĐ]", "d"}};

        for (int i = 0; i < vowels[0].length; i++) {
            System.out.println(i);
        }
    }
}
