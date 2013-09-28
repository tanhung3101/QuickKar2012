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

import java.io.Serializable;

/**
 * Handling Account object data.
 * 
 * @author vuongdothanhhuy
 */
public class AccountInfo implements Serializable {

    private String password;
    private String name;
    private String email;
    private String telephoneNo;
    private String otherInfo;

    public AccountInfo(String password, String name, String email, String telephoneNo, String otherInfo) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.telephoneNo = telephoneNo;
        this.otherInfo = otherInfo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String tel) {
        this.telephoneNo = tel;
    }
}
