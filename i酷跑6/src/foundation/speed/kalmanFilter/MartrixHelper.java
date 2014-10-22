package foundation.speed.kalmanFilter;

import java.text.DecimalFormat;

public class MartrixHelper  {
	public  double[][] getA_T(double[][] A) {  
        int h = A.length;  
        int v = A[0].length;  
        // ������A�к����෴��ת�þ���  
        double[][] A_T = new double[v][h];  
        // ����Aȡ��ת�þ���A_T  
        for (int i = 0; i < v; i++) {  
            for (int j = 0; j < h; j++) {  
                A_T[j][i] = A[i][j];  
            }  
        }  
        return A_T;  
    }
	
	public  double[][] getN(double[][] data) {  
        // �����������ʽ��ģ|data|  
        double A = getHL(data);  
        // ����һ���������������  
        double[][] newData = new double[data.length][data.length];  
  
        for (int i = 0; i < data.length; i++) {  
            for (int j = 0; j < data.length; j++) {  
                double num;  
                if ((i + j) % 2 == 0) {  
                    num = getHL(getDY(data, i + 1, j + 1));  
                } else {  
                    num = -getHL(getDY(data, i + 1, j + 1));  
                }  
  
                newData[i][j] = num / A;  
            }  
        }  

        newData = getA_T(newData);
        return newData;
    }
    
   
    public  double getHL(double[][] data) {  
    	  
        // ��ֹ����  
        if (data.length == 2) {  
            return data[0][0] * data[1][1] - data[0][1] * data[1][0];  
        }  
  
        double total = 0;  
        // ����data �õ�����ʽ������������  
        int num = data.length;  
        // ����һ����СΪnum �������Ŷ�Ӧ��չ������Ԫ����ĵ�ֵ  
        double[] nums = new double[num];  
  
        for (int i = 0; i < num; i++) {  
            if (i % 2 == 0) {  
                nums[i] = data[0][i] * getHL(getDY(data, 1, i + 1));  
            } else {  
                nums[i] = -data[0][i] * getHL(getDY(data, 1, i + 1));  
            }  
        }  
        for (int i = 0; i < num; i++) {  
            total += nums[i];  
        }  
        return total;  
    }   
  
    public  double[][] getDY(double[][] data, int h, int v) {  
        int H = data.length;  
        int V = data[0].length;  
        double[][] newData = new double[H - 1][V - 1];  
  
        for (int i = 0; i < newData.length; i++) {  
  
            if (i < h - 1) {  
                for (int j = 0; j < newData[i].length; j++) {  
                    if (j < v - 1) {  
                        newData[i][j] = data[i][j];  
                    } else {  
                        newData[i][j] = data[i][j + 1];  
                    }  
                }  
            } else {  
                for (int j = 0; j < newData[i].length; j++) {  
                    if (j < v - 1) {  
                        newData[i][j] = data[i + 1][j];  
                    } else {  
                        newData[i][j] = data[i + 1][j + 1];  
                    }  
                }  
  
            }  
        }  
        return newData;  
    }   
	public double [][] inti_P(){
		double [][] temp = new double[][]{  	
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0}};
		return temp;
	}
	public double [][] inti_QR(){
		double [][] temp = new double[][]{  	
				{0,0,0},
				{0,0,0},
				{0,0,0}
				};
		return temp;
	}
	public double [][] inti_KG(){
		double [][] temp = new double[][]{  	
				{0,0,0},
				{0,0,0},
				{0,0,0},
				{0,0,0},
				{0,0,0},
				{0,0,0}
				};
		return temp;
	}
	public void clean_temp_P(double [][] temp){
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 6; jj++)
				temp[ii][jj] = 0;
	}
}
