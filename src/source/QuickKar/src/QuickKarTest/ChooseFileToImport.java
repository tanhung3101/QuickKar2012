package QuickKarTest;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author vuongdothanhhuy
 */
public class ChooseFileToImport {

    public static File fileBrowse(JPanel p) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "CSV File Only", "csv");
        chooser.setFileFilter(filter);
        chooser.setApproveButtonText("Import It");
        chooser.setApproveButtonToolTipText("Let's import this into the program!");
        chooser.setDialogTitle("Choose a .csv file to import...");
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(false);

        // when a file is choosen, it return an error code.
        int returnVal = chooser.showOpenDialog(p);
//        if (returnVal == JFileChooser.APPROVE_OPTION) {
//            System.out.println("You chose to open this file: "
//                    + chooser.getSelectedFile().getName());
//        }
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        } else {
            return null;
        }
    }

}
