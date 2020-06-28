package elements;

import javafx.scene.control.ChoiceBox;

import java.util.HashMap;

public class Boxes {
    private final ChoiceBox<String> functionChoose;
    private HashMap<String,String> functions= new HashMap<>();
    private String selectedFunction;
    public Boxes (ChoiceBox<String> functionChoose){
        this.functionChoose=functionChoose;
        setChoiceBox();
        setSelectedFunction();
    }
    private void setChoiceBox(){
        functions.put("f(x)=1.5x-1","firstFunction");
        functions.put("f(x)=ln(2x)-1","secondFunction");
        functions.put("f(x)=2x^3-3x^2+6","thirdFunction");
        functionChoose.getItems().addAll(functions.keySet());
    }
    public void setSelectedFunction(){
        functionChoose.setOnAction(actionEvent -> selectedFunction=functions.get(functionChoose.getValue()));
    }
    public String getSelectedFunction(){
        return selectedFunction;
    }
}
