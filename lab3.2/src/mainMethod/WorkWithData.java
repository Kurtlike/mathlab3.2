package mainMethod;

import elements.Logger;

public class WorkWithData {
    private Logger logger;
    private String numberOfFunction;
    private double xCoord;
    private String message="";
    public WorkWithData(Logger logger,String numberOfFunction,double xCoord) {
        this.logger = logger;
        this.numberOfFunction=numberOfFunction;
        this.xCoord=xCoord;
        logger.addToLogs("Ответ:"+prepare());
        logger.addToLogs(message);
    }
    Spline spline;
    private double[][] sourceFunction;
    private double[][] newFunction;
    private double[][] dots;
    private String prepare(){
        spline=new Spline(numberOfFunction,xCoord);
        sourceFunction=spline.getSourceFunction();
        newFunction=spline.getNewFunction();
        dots=spline.getDots();
        return spline.getAnswer();
    }
    public double[][]getSourceFunction(){
        return sourceFunction;
    }
    public double[][]getNewFunction(){
        return newFunction;
    }
    public double[][]getDots(){ return dots; }
}
