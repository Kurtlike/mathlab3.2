package elements;

import javafx.scene.control.TextField;

public class XField {
    private final TextField xCoord;
    private double valueOfXCoord;
    private Logger logger;
    public XField(TextField xCoord,Logger logger){
        this.xCoord=xCoord;
        this.logger=logger;
        readX();
    }
    public void readX(){
        try {
            valueOfXCoord=Double.parseDouble(xCoord.getText());
            logger.addToLogs("x="+valueOfXCoord);
        }
        catch (Exception e){
            logger.setLog("Значение X введенно не верно");
        }
    }
    public double getvalueOfXCoord(){
        return valueOfXCoord;
    }
}
