/**
 * Course: Software Development: Process & Tools Lecturer: Quang Tran
 * Assignment: 2-2012B Assignment Title: QuickKar2012
 *
 * RMIT International University Vietnam Bachelor of Information Technology -
 * Application
 *
 */
package QuickKarController;

import QuickKarModel.facade.QuickKarModel;
import javax.swing.ImageIcon;

public interface SongInfoInterface {
   public QuickKarModel model = QuickKarModel.getModel();
    public static final int SONGCODE = 0;
    public static final int SONGNAME = 1;
    public static final int LYRIC = 2;
    public static final int COMPOSER = 3;
    public static final int RATE = 4;
    public static final ImageIcon BACKGROUND = new ImageIcon(SongInfoInterface.class.getResource("/img/background.png"));
    public static final ImageIcon MENU_PANEL = new ImageIcon(SongInfoInterface.class.getResource("/img/menuPanel.png"));
    public static final ImageIcon ADMIN_LOGO = new ImageIcon(SongInfoInterface.class.getResource("/img/admin/adminLogo.png"));
    public static final ImageIcon ADD_BUTTON = new ImageIcon(SongInfoInterface.class.getResource("/img/button/addButton.png"));
    public static final ImageIcon DELETE_BUTTON = new ImageIcon(SongInfoInterface.class.getResource("/img/button/deleteButton.png"));
    public static final ImageIcon DISABLE_DELETE_BUTTON = new ImageIcon(SongInfoInterface.class.getResource("/img/button/disableDeleteButton.png"));
    public static final ImageIcon EDIT_BUTTON = new ImageIcon(SongInfoInterface.class.getResource("/img/button/editButton.png"));
    public static final ImageIcon IMPORT_BUTTON = new ImageIcon(SongInfoInterface.class.getResource("/img/button/importButton.png"));
    public static final ImageIcon LOGOUT_BUTTON = new ImageIcon(SongInfoInterface.class.getResource("/img/button/logoutButton.png"));
    public static final ImageIcon NEXT_PAGE = new ImageIcon(SongInfoInterface.class.getResource("/img/button/nextPage.png"));
    public static final ImageIcon PREVIOUS_PAGE = new ImageIcon(SongInfoInterface.class.getResource("/img/button/previousPage.png"));
    public static final ImageIcon SAVE_BUTTON = new ImageIcon(SongInfoInterface.class.getResource("/img/button/saveButton.png"));
    public static final ImageIcon SEARCH_BUTTON = new ImageIcon(SongInfoInterface.class.getResource("/img/button/searchButton.png"));
    public static final ImageIcon SWITCH_BUTTON = new ImageIcon(SongInfoInterface.class.getResource("/img/button/switchButton.png"));
    public static final ImageIcon EXIT_BUTTON = new ImageIcon(SongInfoInterface.class.getResource("/img/chooseUser/exitButton.png"));
    public static final ImageIcon GUEST_BUTTON = new ImageIcon(SongInfoInterface.class.getResource("/img/chooseUser/guestButton.png"));
    public static final ImageIcon STAFF_BUTTON = new ImageIcon(SongInfoInterface.class.getResource("/img/chooseUser/staffButton.png"));
    public static final ImageIcon BACKGROUND1 = new ImageIcon(SongInfoInterface.class.getResource("/img/favorite/bg1.png"));
    public static final ImageIcon LOAD1_1 = new ImageIcon(SongInfoInterface.class.getResource("/img/loading/load1_1.gif"));
    public static final ImageIcon LOADING_PANEL = new ImageIcon(SongInfoInterface.class.getResource("/img/loading/loadingPanel.png"));
    public static final ImageIcon BACK_BUTTON = new ImageIcon(SongInfoInterface.class.getResource("/img/login/backButton.png"));
    public static final ImageIcon LOCK_IMAGE = new ImageIcon(SongInfoInterface.class.getResource("/img/login/lockImg.png"));
    public static final ImageIcon LOGIN_BUTTON = new ImageIcon(SongInfoInterface.class.getResource("/img/login/loginButton.png"));
    public static final ImageIcon LOGIN_PANEL = new ImageIcon(SongInfoInterface.class.getResource("/img/login/loginPanel.png"));
    public static final ImageIcon NEXT_BUTTON = new ImageIcon(SongInfoInterface.class.getResource("/img/login/nextButton.png"));
    public static final ImageIcon PHONE_PANEL = new ImageIcon(SongInfoInterface.class.getResource("/img/login/phonePanel.png"));
    public static final ImageIcon EMPTY_STAR_ICON = new ImageIcon(SongInfoInterface.class.getResource("/img/rateStar/emptyStar.png"));
    public static final ImageIcon GREEN_STAR_ICON = new ImageIcon(SongInfoInterface.class.getResource("/img/rateStar/greenStar.png"));
    public static final ImageIcon HALF_STAR = new ImageIcon(SongInfoInterface.class.getResource("/img/rateStar/halfStar.png"));
    public static final ImageIcon ONE_THIRDS_STAR = new ImageIcon(SongInfoInterface.class.getResource("/img/rateStar/oneThirdsStar.png"));
    public static final ImageIcon TWO_THIRDS_STAR = new ImageIcon(SongInfoInterface.class.getResource("/img/rateStar/twoThirdsStar.png"));
    public static final ImageIcon STAFF_LOGO = new ImageIcon(SongInfoInterface.class.getResource("/img/staff/staffLogo.png"));
    public static final ImageIcon EXPORT_LOGO = new ImageIcon(SongInfoInterface.class.getResource("/img/button/exportButton.png"));
    public static final ImageIcon NEXT_BUTTON_VN = new ImageIcon(SongInfoInterface.class.getResource("/img/login/nextButton_VN.png"));
    public static final ImageIcon LOGIN_BUTTON_VN = new ImageIcon(SongInfoInterface.class.getResource("/img/login/loginButton_VN.png"));
    public static final ImageIcon BACK_BUTTON_VN = new ImageIcon(SongInfoInterface.class.getResource("/img/login/backButton_VN.png"));
}