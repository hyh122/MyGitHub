package foundation.speed.kalmanFilter;
import java.text.DecimalFormat;
import java.util.Random;

public class Kalman {
	gravity x_temp; //中间�?
	gravity x_now; //此刻系统状�?
	gravity z_measure[]; //测量�?
	double kg[][]; //kalman增益矩阵
	double F[][];//状�?转移矩阵
	double H[][];//测量矩阵
	double L[][];//系统控制矩阵	
	double p_temp[][]; //中间�?
	double p_now[][]; //此刻系统协方�?
	double Q[][], R[][]; //两个协方矩阵
	double [] Vx,Vy,Vz;
	double [] ax,ay,az;
	int N;
	MartrixHelper maxHelper;//矩阵辅助�?

	public void filter(double Ax[],double Ay[],double Az[],int n,double Ax_last,double Ay_last,double Az_last) {
//		初始化系统状态量与协方差矩阵
		N = n;
		ax = Ax;
		ay = Ay;
		az = Az;
//		初始化Q,R
//		init_QR(Ax,Ay,Az);
//		初始化其
		init_all(Ax_last,Ay_last,Az_last);
//		�?��滤波
		begin();
	}
	public void init_QR(double[] Ax,double[] Ay,double[] Az){
//		初始化Q,R
		double sum_R_x,sum_R_y,sum_R_z;
		double c_R_x ,c_R_y,c_R_z;
		sum_R_x = sum_R_y = sum_R_z = 0;
		c_R_x = c_R_y = c_R_z = 0;
		for(int i = 0; i < N; i++)
		{				
				sum_R_x += Ax[i];
				sum_R_y += Ay[i];
				sum_R_z += Az[i];
		}
		sum_R_x/=N;
		sum_R_y/=N;
		sum_R_z/=N;
		for(int i = 0; i < N; i++){
			c_R_x += (Ax[i]-sum_R_x)*(Ax[i]-sum_R_x);
			c_R_y += (Ay[i]-sum_R_y)*(Ay[i]-sum_R_y);
			c_R_z += (Az[i]-sum_R_z)*(Az[i]-sum_R_z);
		}
		
		Q = new double[][]{
				{0.01,0,0},
				{0,0.01,0},
				{0,0,0.01},
		};
		R = new double[][]{
				{c_R_x/N,0,0},
				{0,c_R_y/N,0},
				{0,0,c_R_z/N},
		};
	}
	public void init_all(double Ax_last,double Ay_last,double Az_last){
		Q = new double[][]{
				{1.9755610997524473E-9,0,0},
				{0,8.292159656995527E-10,0},
				{0,0,8.503904434945443E-11},
		};
		R = new double[][]{
				{14.915427310921798,0,0},
				{0,13.056256555680818,0},
				{0,0,34.7963373291378},
		};
		Vx = new double[N];
		Vy = new double[N];
		Vz = new double[N];
		x_temp = new gravity(0,0,0,0,0,0);
		x_now  = new gravity(0,0,0,Ax_last,Ay_last,Az_last);
		p_now = new double[][]{
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,1,0,0},
				{0,0,0,0,1,0},
				{0,0,0,0,0,1}};
		p_temp = new double[][]{  	
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0}};
//		初始化卡尔曼增益矩阵
		kg = new double[][]{
				{0,0,0},
				{0,0,0},
				{0,0,0},
				{0,0,0},
				{0,0,0},
				{0,0,0}
				};
//		初始化状态转移矩�?
		F = new double[][]{  	
				{0,0,0,1,0,0},
				{0,0,0,0,1,0},
				{0,0,0,0,0,1},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0}};
//		初始化测量矩�?
		H = new double[][]{
				{0,0,0,1,0,0},
				{0,0,0,0,1,0},
				{0,0,0,0,0,1}};
//		初始化系统控制矩�?
		L = new double[][]{	
				{0,0,0},
				{0,0,0},
				{0,0,0},
				{1,0,0},
				{0,1,0},
				{0,0,1}};
//		初始化矩阵辅助类
		maxHelper = new MartrixHelper();
	}
	public void begin() {

		for(int i = 0; i < N; i++) { //i�?�?��

//			1.预估计x
//			X(k|k-1) = F•X(k-1|k-1)
			x_temp.set(calculate_x_temp(F,x_now));
//			2.计算预估计协方差矩阵
//			P(k|k-1)^= F(k|k-1)×P(k-1|k-1)×F(k|k-1)'+L(k|k-1)×Q(k)×L(k|k-1)'
			p_temp = calculate_p_temp2(F,p_now,L,Q);	
//			3.1计算卡尔曼增益矩�?
//			Kg(k)= P(k|k-1) H�?/ (H P(k|k-1) H�? R)	
			kg = calculate_kg(p_temp,H,R);
//			4.计算�?���?
//			Z(k)=H X(k)+V(k)
//			X(k|k)= X(k|k-1)+Kg(k) ( Z(k)-HX(k|k-1))
			x_now.set(calculate_x2(x_temp,H,ax[i],ay[i],az[i]));
			Vx[i] = x_now.x[0];
			Vy[i] = x_now.x[1];
			Vz[i] = x_now.x[2];
//			5.计算更新后估计协防差矩阵
//			P(k|k)=[I-Kg(k)H(k)']P(k|k-1)×[I-Kg(k)×H(k)]'+K(k)×R(k)×K(k)' 
			p_now = calculate_p3(kg,H,p_temp);
		}
	}
	public gravity calculate_x_temp(double F[][],gravity last){
//		1.预估计x
//		X(k|k-1) = F*X(k-1|k-1)+B*U(k)
		gravity temp_for_x = new gravity(0,0,0,0,0,0);
		gravity temp_for_x2 = new gravity(0,0,0,0,0,0);
		
//		F*X
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 6; jj++)
				temp_for_x.x[ii] += F[ii][jj]*last.x[jj];
		
//		F*X+B*U(k)
		for(int ii = 0; ii < 3; ii++)
			temp_for_x2.x[ii] = temp_for_x.x[ii];
		for(int ii = 3; ii < 6; ii++)
				temp_for_x2.x[ii] = temp_for_x.x[ii] + last.x[ii];

		return temp_for_x2;
	}
	
	public double [][] calculate_p_temp1(double F[][],double p_last[][],double L[][],double Q[][]){	
//		2.计算预估计协方差矩阵
//		P(k|k-1)^= F(k|k-1)×P(k-1|k-1)×F(k|k-1)'+L(k|k-1)×Q(k)×L(k|k-1)'
		double [][] temp_for_p = maxHelper.inti_P();
		double [][] temp_for_p2 = maxHelper.inti_P();
		
//		F×P
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 6; jj++)
				for(int kk = 0; kk < 6; kk++)
					temp_for_p[ii][jj] +=  F[ii][kk]*p_last[kk][jj];
		
//		F×P×F'
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 6; jj++)
				for(int kk = 0; kk < 6; kk++)
					temp_for_p2[ii][jj] +=  temp_for_p[ii][kk]*F[jj][kk];
		
//		temp_for_p = 0
		maxHelper.clean_temp_P(temp_for_p);
		
//		L×Q
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 3; jj++)
				for(int kk = 0; kk < 3; kk++)
					temp_for_p[ii][jj] += L[ii][kk]*Q[kk][jj];
		
//		L×Q×L'	
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 6; jj++)
				for(int kk = 0; kk < 3; kk++)
					temp_for_p2[ii][jj] += temp_for_p[ii][kk]*L[jj][kk];

//		temp_for_p = 0
		maxHelper.clean_temp_P(temp_for_p);

//		F×P×F'+L×Q×L'
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 6; jj++)
				temp_for_p[ii][jj] +=  temp_for_p2[ii][jj];

		return temp_for_p;
	}
	public double [][] calculate_p_temp2(double F[][],double p_last[][],double L[][],double Q[][]){	
//		2.计算预估计协方差矩阵
//		P(k|k-1)^= F(k|k-1)×P(k-1|k-1)×F(k|k-1)'+L(k|k-1)×Q(k)×L(k|k-1)'
		double [][] temp_for_p = maxHelper.inti_P();
		double [][] temp_for_p2 = maxHelper.inti_P();
		
//		L×Q
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 3; jj++)
				for(int kk = 0; kk < 3; kk++)
					temp_for_p[ii][jj] += L[ii][kk]*Q[kk][jj];
		
//		L×Q×L'	
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 6; jj++)
				for(int kk = 0; kk < 3; kk++)
					temp_for_p2[ii][jj] += temp_for_p[ii][kk]*L[jj][kk];

//		temp_for_p = 0
		maxHelper.clean_temp_P(temp_for_p);

//		F×P×F'+L×Q×L'
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 6; jj++)
				temp_for_p[ii][jj] =  temp_for_p2[ii][jj] + p_last[ii][jj];

		return temp_for_p;
	}

	public double[][] calculate_kg(double [][] p_temp,double [][] H,double [][]R){
//		3.计算卡尔曼增益矩�?
//		Kg(k)= P(k|k-1) H�?/ (H P(k|k-1) H�? R)	
		double [][] temp_for_p = maxHelper.inti_P();
		double [][] temp_for_p2 = maxHelper.inti_P();
		double [][] temp_for_p3 = maxHelper.inti_P();
		double [][] temp_for_QR = maxHelper.inti_QR();
		double [][] temp_for_kg = maxHelper.inti_KG();
//		P*H�?
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 3; jj++)
				for(int kk = 0; kk < 6; kk++)
					temp_for_p[ii][jj] += p_temp[ii][kk]*H[jj][kk];

//		H*P
		for(int ii = 0; ii < 3; ii++)
			for(int jj = 0; jj < 6; jj++)
				for(int kk = 0; kk < 6; kk++)
					temp_for_p2[ii][jj] += H[ii][kk]*p_temp[kk][jj];

//		H*P*H�?
		for(int ii = 0; ii < 3; ii++)
			for(int jj = 0; jj < 3; jj++)
				for(int kk = 0; kk < 6; kk++)
					temp_for_p3[ii][jj] += temp_for_p2[ii][kk]*H[jj][kk];

//		H*P*H�?R
		for(int ii = 0; ii < 3; ii++)
			for(int jj = 0; jj < 3; jj++)
				temp_for_QR[ii][jj] = temp_for_p3[ii][jj] + R[ii][jj];
		
//		求�?矩阵
		R = maxHelper.getN(temp_for_QR);

//		temp_for_p2 = 0
		maxHelper.clean_temp_P(temp_for_p2);

//		P*H�?/ (H*P*H�?R)	
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 3; jj++)
				for(int kk = 0; kk < 3; kk++)
					temp_for_p2[ii][jj] += temp_for_p[ii][kk]*R[kk][jj];
		
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 3; jj++)
					temp_for_kg[ii][jj] = temp_for_p2[ii][jj];
		
		return temp_for_kg;
	}

	public gravity calculate_x(gravity x_temp,gravity z_measure,double[][] H){
//		4.计算�?���?
//		Z(k)=H X(k)+V(k)
		gravity temp_for_z = new gravity(0,0,0,0,0,0);
		for(int ii = 0; ii < 3; ii++)
			for(int jj = 0; jj < 6; jj++)
				temp_for_z.x[ii] += H[ii][jj]*z_measure.x[jj];

//		X(k|k)= X(k|k-1)+Kg(k) ( Z(k)-HX(k|k-1))
		gravity temp_for_x = new gravity(0,0,0,0,0,0);
		gravity temp_for_x2 = new gravity(0,0,0,0,0,0);
		
//		H*X
		for(int ii = 0; ii < 3; ii++)
			for(int jj = 0; jj < 6; jj++)
				temp_for_x.x[ii] +=  H[ii][jj]*x_temp.x[jj];
		
//		Z(k)-HX(k|k-1)
		for(int ii = 0; ii < 3; ii++)
				temp_for_x2.x[ii] = temp_for_z.x[ii] - temp_for_x.x[ii];


//		temp_for_x = 0;
		temp_for_x.set(0,0,0,0,0,0);
		
////		kg*(Z-H*X)
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 3; jj++)
				temp_for_x.x[ii] += kg[ii][jj]*temp_for_x2.x[jj];

////		X+kg*(Z-H*X)
		for(int ii = 0; ii < 6; ii++)
			temp_for_x2.x[ii] = x_temp.x[ii] + temp_for_x.x[ii];
		return temp_for_x2;
	}
	public gravity calculate_x2(gravity x_temp,double[][] H,double ax,double ay,double az){

//		X(k|k)= X(k|k-1)+Kg(k) ( Z(k)-HX(k|k-1))
		gravity temp_for_x = new gravity(0,0,0,0,0,0);
		gravity temp_for_x2 = new gravity(0,0,0,0,0,0);
		
//		H*X
		for(int ii = 0; ii < 3; ii++)
			for(int jj = 0; jj < 6; jj++)
				temp_for_x.x[ii] +=  H[ii][jj]*x_temp.x[jj];
		
//		Z(k)-HX(k|k-1)

		temp_for_x2.x[0] = ax - temp_for_x.x[0];
		temp_for_x2.x[1] = ay - temp_for_x.x[1];
		temp_for_x2.x[2] = az - temp_for_x.x[2];


//		temp_for_x = 0;
		temp_for_x.set(0,0,0,0,0,0);
		
////		kg*(Z-H*X)
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 3; jj++)
				temp_for_x.x[ii] += kg[ii][jj]*temp_for_x2.x[jj];

////		X+kg*(Z-H*X)
		for(int ii = 0; ii < 6; ii++)
			temp_for_x2.x[ii] = x_temp.x[ii] + temp_for_x.x[ii];
		return temp_for_x2;
	}
	public double[][] calculate_p1(double[][] kg,double[][] H,double[][] p_temp){
//		5.计算更新后估计协防差矩阵
//		P(k|k)=[I-Kg(k)H(k)']P(k|k-1)
		double temp_for_p[][] = maxHelper.inti_P();
		double temp_for_p2[][] = maxHelper.inti_P();

//		Kg(k)H
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 6; jj++)
				for(int kk = 0; kk < 3; kk++)
					temp_for_p[ii][jj] += kg[ii][kk]*H[kk][jj];

//		I-Kg(k)H

		for(int ii = 0; ii < 6; ii++)
				temp_for_p2[ii][ii] = 1-temp_for_p[ii][ii];

//		temp_for_p = 0
		maxHelper.clean_temp_P(temp_for_p);
		
//		(I-Kg(k)H)*P(k|k-1)
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 6; jj++)
				for(int kk = 0; kk < 6; kk++)
					temp_for_p[ii][jj] += temp_for_p2[ii][kk]*p_temp[kk][jj];

		return 	temp_for_p;
	}
	public double[][] calculate_p2(double[][] kg,double[][] H,double[][] p_temp){
//		5.计算更新后估计协防差矩阵
//		P(k|k)=[I-Kg(k)H(k)']P(k|k-1)×[I-Kg(k)×H(k)]'
		double temp_for_p[][] = maxHelper.inti_P();
		double temp_for_p2[][] = maxHelper.inti_P();
		double temp_for_p3[][] = maxHelper.inti_P();
//		Kg(k)H
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 6; jj++)
				for(int kk = 0; kk < 3; kk++)
					temp_for_p[ii][jj] += kg[ii][kk]*H[kk][jj];

//		I-Kg(k)H

		for(int ii = 0; ii < 6; ii++)
				temp_for_p2[ii][ii] = 1-temp_for_p[ii][ii];

//		temp_for_p = 0
		maxHelper.clean_temp_P(temp_for_p);
		
//		(I-Kg(k)H)*P(k|k-1)
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 6; jj++)
				for(int kk = 0; kk < 6; kk++)
					temp_for_p[ii][jj] += temp_for_p2[ii][kk]*p_temp[kk][jj];
		

//		(I-Kg(k)H)*P(k|k-1)×[I-Kg(k)×H(k)]'
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 6; jj++)
				for(int kk = 0; kk < 6; kk++)
					temp_for_p3[ii][jj] += temp_for_p[ii][kk]*temp_for_p2[jj][kk];

		return 	temp_for_p3;
	}
	public double[][] calculate_p3(double[][] kg,double[][] H,double[][] p_temp){
//		5.计算更新后估计协防差矩阵
//		P(k|k)=[I-Kg(k)H(k)']P(k|k-1)×[I-Kg(k)×H(k)]'+K(k)×R(k)×K(k)' 
		double temp_for_p[][] = maxHelper.inti_P();
		double temp_for_p2[][] = maxHelper.inti_P();
		double temp_for_p3[][] = maxHelper.inti_P();
//		Kg(k)H
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 6; jj++)
				for(int kk = 0; kk < 3; kk++)
					temp_for_p[ii][jj] += kg[ii][kk]*H[kk][jj];

//		I-Kg(k)H

		for(int ii = 0; ii < 6; ii++)
				temp_for_p2[ii][ii] = 1-temp_for_p[ii][ii];

//		temp_for_p = 0
		maxHelper.clean_temp_P(temp_for_p);
		
//		(I-Kg(k)H)*P(k|k-1)
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 6; jj++)
				for(int kk = 0; kk < 6; kk++)
					temp_for_p[ii][jj] += temp_for_p2[ii][kk]*p_temp[kk][jj];
		
//		(I-Kg(k)H)*P(k|k-1)×[I-Kg(k)×H(k)]'
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 6; jj++)
				for(int kk = 0; kk < 6; kk++)
					temp_for_p3[ii][jj] += temp_for_p[ii][kk]*temp_for_p2[jj][kk];

//		temp_for_p = temp_for_p2 = 0
		maxHelper.clean_temp_P(temp_for_p);
		maxHelper.clean_temp_P(temp_for_p2);
		
//		K(k)×R(k)
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 3; jj++)
				for(int kk = 0; kk < 3; kk++)
					temp_for_p[ii][jj] += kg[ii][kk]*R[kk][jj];
	
//		K(k)×R(k)×K(k)' 
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 6; jj++)
				for(int kk = 0; kk < 3; kk++)
					temp_for_p2[ii][jj] += temp_for_p[ii][kk]*kg[jj][kk];

//		temp_for_p = 0
		maxHelper.clean_temp_P(temp_for_p);
		
//		P(k|k)=[I-Kg(k)H(k)']P(k|k-1)×[I-Kg(k)×H(k)]'+K(k)×R(k)×K(k)' 
		for(int ii = 0; ii < 6; ii++)
			for(int jj = 0; jj < 6; jj++)
				temp_for_p[ii][jj] += temp_for_p3[ii][jj] + temp_for_p2[ii][jj];

		return 	temp_for_p;
	}
	public double getVx(){
		double v = 0;
		for(int i = 0; i < N; i++)
			v+=Vx[i];
		v/=N;
		return v;
	}
	public double getVy(){
		double v = 0;
		for(int i = 0; i < N; i++)
			v+=Vy[i];
		v/=N;
		return v;
	}
	public double getVz(){
		double v = 0;
		for(int i = 0; i < N; i++)
			v+=Vz[i];
		v/=N;
		return v;
	}
	public class gravity {
		public double x[];
		gravity(double a,double b,double c,double d,double e,double f)
		{
//			Vx,Vy,Vz,Ax,Ay,Az
			x = new double[6];
			x[0] = a;x[1] = b;x[2] = c;x[3] = d;x[4] = e;x[5] = f;
		}
		public void set(double a,double b,double c,double d,double e,double f)
		{x[0] = a;x[1] = b;x[2] = c;x[3] = d;x[4] = e;x[5] = f;}
		public void set(gravity temp)
		{x[0] = temp.x[0];
		x[1] = temp.x[1];
		x[2] = temp.x[2];
		x[3] = temp.x[3];
		x[4] = temp.x[4];
		x[5] = temp.x[5];}
	}
}