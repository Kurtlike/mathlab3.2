package sample;
import elements.Boxes;
import elements.Chart;
import elements.Logger;
import elements.XField;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import mainMethod.WorkWithData;

public class Controller {

    @FXML
    private ChoiceBox<String> functionChoose;

    @FXML
    private TextField xCoord;

    @FXML
    private Button performButton;

    @FXML
    private TextArea logArea;

    @FXML
    private LineChart<Number, Number> graph;

    @FXML
    private NumberAxis x;

    @FXML
    private NumberAxis y;
    @FXML
    void initialize(){
        Logger logger=new Logger(logArea);
        Boxes boxes=new Boxes(functionChoose);

        Chart chart=new Chart(graph);
        performButton.setOnAction(actionEvent -> {
            XField xField=new XField(xCoord,logger);
            WorkWithData workWithData=new WorkWithData(logger,boxes.getSelectedFunction(),xField.getvalueOfXCoord());
            logger.setLogs();
            chart.setChart(workWithData.getSourceFunction(),workWithData.getNewFunction(),workWithData.getDots());
        });


    }
}
