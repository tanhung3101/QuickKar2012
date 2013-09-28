/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickKarTest;

import QuickKarModel.facade.QuickKarModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nop
 */
public class AddFavoriteSong implements ActionListener {

       private QuickKarModel model;
    private Map<String, HashSet<String>> favoriteMap;
    private HashSet<String> songCodeList;
    private int selectedRow;
    private String oldSongCode;
    private DefaultTableModel tableModel;

    public AddFavoriteSong(Map<String,HashSet<String>> favoriteMap,HashSet<String> songCodeList, DefaultTableModel tableModel, int row){
         this.model = QuickKarModel.getModel();
        this.favoriteMap = favoriteMap;
        this.tableModel = tableModel;
        this.songCodeList=songCodeList;
       
    }

    public void actionPerformed(ActionEvent e) {
  
    
        Scanner re = new Scanner(System.in);
        System.out.println("Enter No:");
        String input = re.next();
        System.out.println("Enter SongCode:");
        String songCodeInput = re.next();
          

        AddFavoriteSong(input, songCodeInput);
    }
     private boolean AddFavoriteSong(String input, String songCode){

         
        //check existed phone No
        if (this.favoriteMap.containsKey(input)) {

 
            if(favoriteMap.get(input)==null)
            {
              songCodeList=new HashSet<String>();
            }else
            {
            songCodeList=favoriteMap.get(input);
            }

            //check existed favorite song in favorite List
            if (this.favoriteMap.containsValue(songCode)) {
                System.out.println(songCode + " already added");
            } else {
                System.out.println("Add Ok");
                songCodeList.add(songCode);
                this.favoriteMap.put(input,songCodeList);
                return true;
            }
        } else {
            System.out.println("Invalid PhoneNo");
        }
        return false;
    }
}

