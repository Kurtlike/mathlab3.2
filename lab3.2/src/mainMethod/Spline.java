package mainMethod;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Spline {
    private final double xCoord;
    private double answer;
    private double leftBord;
    private double rightBord;
    private double[][] sourceFunction;
    private double[][] newFunction;
    private double[][] dots;
    private double[][] coefMatrix;
    String message="";
    public Spline(String numberOfFunction,double xCoord){
        this.xCoord=xCoord;
        switch (numberOfFunction) {
            case "firstFunction" -> prepareFunction(1);
            case "secondFunction" -> prepareFunction(2);
            case "thirdFunction" -> prepareFunction(3);
        }
    }
    private void prepareFunction(int fNumber){
        leftBord=getBorders(fNumber,xCoord)[0];
        rightBord=getBorders(fNumber,xCoord)[1];
        createSetStartingDots(fNumber);
        createSetDots(fNumber);
        answer=xCoord;
        prepareDataForGauss(fNumber);
        createSetNewDots();
    }
    private void prepareDataForGauss(int fNumber){
        int n=18;
        double[][] matrix=new double[n][n+1];
        for(int i=0;i<n;i++){
            for(int j=0;j<n+1;j++){
                if(i==j) matrix[i][j]=2;
                else if(j==i-1) matrix[i][j]=getFK(i);
                else if(j==i+1) matrix[i][j]=getSK(i);
                else if(j==n) matrix[i][j]=getSU(i);
                else matrix[i][j]=0;
                //System.out.print(matrix[i][j]+" ");
            }
           // System.out.println();
        }
        GaussianElimination gaussianElimination=new GaussianElimination(18,matrix);
        //for(double k:gaussianElimination.getAnswer()) System.out.println(k);
        double[] cCoef=gaussianElimination.getAnswer();
        coefMatrix=new double[n+1][4];
        for(int i=0;i<n+1;i++){
            if(i==18) coefMatrix[18][2]=0;
            else coefMatrix[i][2]=cCoef[i];
            if(i==0) coefMatrix[0][3]=cCoef[0]/(dots[1][0]-dots[0][0]);
            else coefMatrix[i][3]=(coefMatrix[i][2]-coefMatrix[i-1][2])/(dots[i+1][0]-dots[i][0]);
            if(i==0) coefMatrix[0][1]=cCoef[0]*(dots[1][0]-dots[0][0])/3+getSU(0);
            else coefMatrix[i][1]=coefMatrix[i][2]*(dots[i+1][0]-dots[i][0])/3+coefMatrix[i-1][2]*(dots[i+1][0]-dots[i][0])/6+getSU(i);
            coefMatrix[i][0]=getFunction(fNumber,dots[i][0]);
        }
        for (double[] k:coefMatrix){
            System.out.println("a="+k[0]+" b="+k[1]+" c="+k[2]+" d="+k[3]);
        }
    }
    private void createSetStartingDots(int fNumber){
        sourceFunction=new double[(int)((rightBord-leftBord)/0.01)][2];
        double dot=leftBord;
        for(int i=0;i<(int)((rightBord-leftBord)/0.01);i++){
            sourceFunction[i][0]=dot;
            sourceFunction[i][1]=getFunction(fNumber,dot);
            dot+=0.01;
        }
    }
    private void createSetNewDots(){
        newFunction=new double[(int)((rightBord-leftBord)/0.01)][2];
        double dot=leftBord;

        for(int i=0;i<(int)((rightBord-leftBord)/0.01);i++){
            dot+=0.01;
            newFunction[i][0]=dot;
            for(int k=0;k<19;k++){
                if (dot > dots[k][0] && dot < dots[k + 1][0]) {
                    newFunction[i][1] = coefMatrix[k][0] + coefMatrix[k][1] * (dot-dots[k][0]) + coefMatrix[k][2] * Math.pow((dot-dots[k][0]), 2) + coefMatrix[k][3] * Math.pow((dot-dots[k][0]), 3);
                    if(xCoord<dot+0.01&&xCoord>dot-0.01) answer=newFunction[i][1];
                    break;
                }
            }
        }
        /*
        for (double[] k:newFunction){
            System.out.println("x="+k[0]+" y="+k[1]);
        }
        */
    }
    private void createSetDots(int fNumber){
        double[] dotX= new double[20];
        dots=new double[20][2];
        double dot=leftBord;
        for(int i=0;i<4;i++){
            dotX[i]=dot;
            dot+=2;
        }
        for(int i=4;i<16;i++){
            dotX[i]=dot;
            dot+=0.5;
        }
        for(int i=16;i<20;i++){
            dotX[i]=dot;
            dot+=2;
        }
        for(int i=0;i<20;i++){
            dots[i][0]=dotX[i];
            dots[i][1]=getFunction(fNumber,dotX[i]);
        }
    }
    private double getFunction(int numberF,double value){
        return switch (numberF) {
            case 1 -> 1.5*value-1;
            case 2 -> Math.log(2*value)-1;
            case 3 -> 2*Math.pow(value,3)-3*Math.pow(value,2)+6;
            default -> 0;
        };
    }
    private double[] getBorders(int numberF,double X){
        return switch (numberF) {
            case 1 -> new double[] {X-10,X+10};
            case 2 -> X<10?new double[] {0.1,2*X+10}:new double[] {X-10,X+10};
            case 3 -> new double[] {X-10,X+10};
            default -> new double[] {};
        };
    }
    private double getFK(int i){
        i++;
        return (dots[i][0]-dots[i-1][0])/(dots[i+1][0]-dots[i-1][0]);
    }
    private double getSK(int i){
        i++;
        return (dots[i+1][0]-dots[i][0])/(dots[i+1][0]-dots[i-1][0]);
    }
    private double getFU(int i){
        return (dots[i][1]-dots[i-1][1])/(dots[i][0]-dots[i-1][0]);
    }
    private double getSU(int i){
        i++;
        if(i>18) i=18;
        return (getFU(i+1)-getFU(i))/(dots[i+1][0]-dots[i-1][0]);
    }
    public String getMessage(){return message;}
    public String getAnswer(){
        return "y="+ answer+"\n";
    }
    public double[][]getSourceFunction(){
        return sourceFunction;
    }
    public double[][]getNewFunction(){
        return newFunction;
    }
    public double[][]getDots(){ return dots; }
}
