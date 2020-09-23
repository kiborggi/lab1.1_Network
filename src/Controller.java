

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.ProcessLoader;

import java.sql.SQLOutput;
import java.util.ArrayList;


public class Controller {

    ArrayList<Thread> threads = new ArrayList<Thread>();
    @FXML
    private Button btn;
    @FXML
    private TextArea txt;
    @FXML
    private TextArea PingField;
    @FXML
    private TextArea ipConfigArg;
    @FXML
    private ScrollBar scroll;
    @FXML
    private Button stop;
    @FXML
    private CheckBox pingT;
    @FXML
    private CheckBox pingF;
    @FXML
    private CheckBox pingA;
    @FXML
    private CheckBox ipconfigAll;
    @FXML
    private CheckBox ipconfigRenew;
    @FXML
    private CheckBox ipconfigRelese;
    @FXML
    private TextArea ipConfinArg;
    @FXML
    private TextArea tracertArg;
    @FXML
    private TextArea tracerNumber;
    @FXML
    private CheckBox tracert6;
    @FXML
    private CheckBox tracertD;
    @FXML
    private CheckBox tracert4;
    @FXML
    private CheckBox tracertH;
    @FXML
    private PasswordField getmacPss;
    @FXML
    private TextArea getmacip;
    @FXML
    private TextArea user;
    @FXML
    private CheckBox getmacS;
    @FXML
    private TextArea arpIp;
    @FXML
    private TextArea arpMac;
    @FXML
    private CheckBox arpS;
    @FXML
    private CheckBox arpA;


    private String args = "";
    Boolean inter;
    private ProcessLoader processLoader;

    @FXML
    private void clickPing(ActionEvent event) {
        args = "";
        if (pingA.isSelected()) {
            args += " -a ";
        }
        if (pingT.isSelected()) {
            args += " -t ";
        }
        if (pingT.isSelected()) {
            args += " -f ";
        }
        processLoader = new ProcessLoader("ping " + PingField.getText() + args, txt);
        threads.add(processLoader);
        processLoader.start();
    }

    @FXML
    private void stop(ActionEvent event) {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    @FXML
    private void clickIpconfig(ActionEvent event) {
        args = "";
        if (ipconfigAll.isSelected()) {
            args += " -all ";
        }
        if (ipconfigRenew.isSelected()) {
            args += " -renew " + ipConfinArg.getText();
        }
        if (ipconfigRelese.isSelected()) {
            args += " -release  " + ipConfinArg.getText();
        }
        processLoader = new ProcessLoader("ipconfig " + args, txt);
        threads.add(processLoader);
        processLoader.start();
    }

    @FXML
    private void clickTracert(ActionEvent event) {
        args = "";
        if (tracertD.isSelected()) {
            args += " -d ";
        }
        if (tracertH.isSelected()) {
            args += " -h " + tracerNumber.getText();
        }
        if (tracert4.isSelected()) {
            args += " -4 ";
        }
        if (tracert6.isSelected()) {
            args += " -6 ";
        }
        processLoader = new ProcessLoader("tracert " + args + " " + tracertArg.getText(), txt);
        threads.add(processLoader);
        processLoader.start();
    }

    @FXML
    private void clickGetmac(ActionEvent event) {
        args = "";
        if (getmacS.isSelected()) {
            args +=   " /s " + getmacip.getText() + " /u " + user.getText() + " /p " + getmacPss.getText();


        }
        processLoader = new ProcessLoader("getmac " + args, txt);
        threads.add(processLoader);
        processLoader.start();

    }
    @FXML
    private void clickArp(ActionEvent event) {
        args = "";
        if (arpS.isSelected()) {
            args +=   " -s " + arpIp.getText() + " " + arpMac.getText();

        }
        else if (arpA.isSelected()){
            args +=   " -a " + arpIp.getText()  ;
        }
        processLoader = new ProcessLoader("arp " + args, txt);
        threads.add(processLoader);
        processLoader.start();

    }
}
