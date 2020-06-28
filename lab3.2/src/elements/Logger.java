package elements;

import javafx.scene.control.TextArea;

public class Logger {
    TextArea logArea;
    String logs="";
    public Logger(TextArea logArea){
        this.logArea=logArea;
        this.logArea.setEditable(false);
    }
    public void addToLogs(String log){
        logs+=log+"\n";
    }
    public void setLogs(){
        logs+="------------------------------------------"+"\n";
        logArea.setText(logs);
    }
    public void setLog(String logs){
        this.logs+="------------------------------------------"+"\n";
        logArea.setText(logs);
    }
    public void clearAll(){
        logs="";
    }
}
