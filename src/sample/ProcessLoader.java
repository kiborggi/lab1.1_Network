package sample;

import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ProcessLoader extends Thread {
    private  Process process;
    private String str;
    private TextArea txt;
    private String message = "";
 public ProcessLoader(String str,TextArea txt){
     this.str = str;
     this.txt = txt;
 }
    public void setTextField(String str)
    {
        try {
            process = Runtime.getRuntime().exec(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(process.getInputStream(),"CP866"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String line = "";
        while (true && !interrupted()) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
                message += line + "\n";
            System.out.println(line);
                txt.setText(message);

        }
    }


    public void run(){
        setTextField(str);
    }



}
