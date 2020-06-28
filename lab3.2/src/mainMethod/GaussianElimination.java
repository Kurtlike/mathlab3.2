package mainMethod;

public class GaussianElimination {
    private int size;
    private double matrix[][];
    public double[] answer;
    int count = 1;
    double storageVariable;
    public GaussianElimination(int size,double matrix[][]){
        this.matrix=matrix;
        this.size=size;
        solve();
        getReverse();
    }
    private void solve(){
        for(int i=0;i<size-1;i++){
            if(matrix[i][i]!=0 && matrix[i+1][i]!=0) {

                for(int k=i;k<size-1;k++) {
                    storageVariable = matrix[k+1][i] / matrix[i][i];
                    for (int j = 0; j < size + 1; j++) {
                        matrix[k+1][j] -= storageVariable * matrix[i][j];
                    }
                }
            }
        }
    }
    private void getReverse(){
        answer=new double[size];
        answer[size-1]=matrix[size-1][size]/matrix[size-1][size];
        for(int i=size-2;i>=0;i--){
            for(int j=i+1;j<size;j++){
                answer[i]+=matrix[i][j]*answer[j];
            }
            answer[i]=(matrix[i][size]-answer[i])/matrix[i][i];
        }

    }
    public double[] getAnswer(){ return answer;}
}
