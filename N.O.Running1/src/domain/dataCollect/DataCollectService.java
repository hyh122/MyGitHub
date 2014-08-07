package domain.dataCollect;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.File.TxtFileUtil;

import domain.dataCollect.heart.HeartRateData;
import domain.dataCollect.heart.HeartRateService;
import domain.dataCollect.heart.IHeartRateTimeOut;

import domain.dataCollect.speed.ISpeedTimeOut;
import domain.dataCollect.speed.SpeedData;
import domain.dataCollect.speed.SpeedService;

import domain.systemManaConfig.*;
import foundation.dataService.DataCollectDataService;
import foundation.dataService.util.DateService;

public class DataCollectService implements IHeartRateTimeOut,ISpeedTimeOut{
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private ConfigurationService configurationService;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private SpeedService speedService;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	 //文件
	private File f=new File("/sdcard/HeartRates.txt"/**文件路径名**/);
	private HeartRateService heartRateService;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	//private MinuteSportData minuteSportData;

	public OneSport oneSport;
	/** 
	 * <!-- begin-UML-doc -->
	 * <p>得到每分钟采集数据(返回值List&lt;MinuteSpartData&gt;)</p>
	 * <!-- end-UML-doc -->
	 * @param startTime
	 * @param endTime
	 * @return
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private DataCollectDataService dataCollectDataService;
	
	private CurrentSportData currentSportData=new CurrentSportData();
	private double tempDistance=0.0;//临时用于计算总距离
	private double tempTotalCalorie=0.0;//临时用于计算总卡路里
	
	private IDataCollectTimeOut dataCollectTimeOut=null;//UI监听
	
	public void setDataCollectTimeOut(IDataCollectTimeOut dataCollectTimeOut) {
		this.dataCollectTimeOut = dataCollectTimeOut;
	}

	public DataCollectService() {
		//实例化各服务
		dataCollectDataService=new DataCollectDataService();
		heartRateService=new HeartRateService();
		speedService=new SpeedService();
		heartRateService.setListener(this);//设置heartRateService监听者为DataCollectService
		speedService.setSpeedTimeOut(this);//设置speedService监听者为DataCollectService
	}

	public List<MinuteSportData> getMinuteSportData(Date startTime, Date endTime) {
		// begin-user-code
		// TODO 自动生成的方法存根
		return null;
		// end-user-code
	}
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param count
	 * @param date
	 * @return
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private List<OneSport> getSportByCount(Integer count, Date date) {
		// begin-user-code
		// TODO 自动生成的方法存根
		return null;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param SportData
	 * @return
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private List<OneSport> getSportByDate(String SportDate) {
		// begin-user-code
		// TODO 自动生成的方法存根
		return null;
		// end-user-code
	}
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public boolean start(Date startTime) {
		// begin-user-code
		// TODO 自动生成的方法存根

//			if(heartRateService.judgeBlueToothOpen()){
				
				//实例化OneSport
				oneSport=new OneSport();
				//设置开始时间
				oneSport.setStartTime(startTime);
				
				//去数据库比对开始时间
				//int maxNum=dataCollectDataService.getMaxSportNum(startTime);
				//oneSport.setCount(maxNum+1);
				oneSport.setCount(0);//临时测试使用，真是需测试上两行代码
				
				//开启心率采集服务
			//	heartRateService.start();
				//开启速度采集服务
				speedService.start();
		
				return true;
//			}
//			else{
//				return false;
//			}
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void stop(Date endTime) {
		// begin-user-code
		// TODO 自动生成的方法存根
		
		//设置结束时间
		oneSport.setEndTime(endTime);
		//停止心率采集服务
		//heartRateService.stop();
		//停止速度采集服务
		speedService.stop();
	
		//从速度服务获取速度链表
//			List<Double> tempSpeedList = speedService.getSpeed(oneSport.getStartTime(), oneSport.getEndTime());
//			//从心率服务获取心率链表
//			List<Integer> tempHeartRateList = heartRateService.getHeartRate(oneSport.getStartTime(), oneSport.getEndTime());
//			//每分钟数据链表
//			List<MinuteSportData> minuteSportDataList = new ArrayList<MinuteSportData>();
//
//			for(int i=0;i<tempSpeedList.size();i++){
//				MinuteSportData minuteSportData=new MinuteSportData();
//				
//				//装配到minuteSportData
//				minuteSportData.setHeartRate(tempHeartRateList.get(i));//装配心率
//				minuteSportData.setSpeed(tempSpeedList.get(i));//装配速度
//				minuteSportData.setNumber(i);//装配采集次数
////				minuteSportData.setCollectTime(heartRateService.getCollectTime().get(i));//装配采集时间
//				minuteSportData.setOneSport(oneSport);
//			
//				//添加到每分钟数据链表
//				minuteSportDataList.add(minuteSportData);
//			}
//			//一次性保存到数据库
//			dataCollectDataService.saveSportData(oneSport,minuteSportDataList);
		
			// end-user-code
	}
	/** 
	 * <!-- begin-UML-doc -->
	 * <p>得到本次运动数据</p>
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
//	public void getCurrentSportData(Date startTime,Date endTime) {
//		// begin-user-code
//		// TODO 自动生成的方法存根
//		
//		//实例化currentSportData
//		currentSportData=new CurrentSportData();
//
//		//回调UI层的onTimeOut
//
//		dataCollectTimeOut.DOnTimeOut(currentSportData);
//		// end-user-code
//	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param weight
	 * @param height
	 * @param distance
	 * @param speed
	 * @return
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private double calCalorie(double weight, double speed,double preCalorie) {
		// begin-user-code
		// TODO 自动生成的方法存根
		
		return (preCalorie+speed*weight/400);
		
		// end-user-code
	}

	//实现速度服务层的onTimeOut方法
	@Override
	public void SOnTimeOut(SpeedData speedData) {
		currentSportData=new CurrentSportData();
		
		currentSportData.setCurrentSpeed(speedData.getSpeed());//装配当前速度
		tempDistance+=speedData.getSpeed()*60;//总距离
		currentSportData.setDistance(tempDistance);//假设送上来速度单位为m/s，且为平均速度
		//计算卡路里
		oneSport.setUser(new User(60));//测试使用，体重60KG
		currentSportData.setTotalCalorie(
				calCalorie(oneSport.getUser().getWeight(),
				currentSportData.getCurrentSpeed(),
				tempTotalCalorie));//设置卡路里值
		
		//如果getCurrentHeartRate!=-1说明装配心率成功,谁先做完谁调上层的onTimeOut
		if(currentSportData.getCurrentHeartRate()!=-1){
			
			
			//dataCollectTimeOut.DOnTimeOut(currentSportData.CopyCurrentSportData(currentSportData));//回调上层服务
			
			//清空currentSportData中数据
			currentSportData.ClearCurrentSportData(currentSportData);
		}
	}

	//实现心率服务层的onTimeOut方法
	@Override
	public void HOnTimeOut(HeartRateData heartRateData) {
		
		currentSportData.setCurrentHeartRate(heartRateData.getHeartRate());//装配当前心率
		TxtFileUtil.appendToFile("HOnTimeOut:"+currentSportData.getCurrentHeartRate()+"\r\n", f);
		this.dataCollectTimeOut.DOnTimeOut(currentSportData);
//		//谁先做完谁调上层的onTimeOut
//		if(currentSportData.getTotalCalorie()!=-1){
//			synchronized(this){
//			dataCollectTimeOut.DOnTimeOut(currentSportData.CopyCurrentSportData(currentSportData));//拷贝数据回调给上层服务
//			}
//			//清空currentSportData中数据
//			currentSportData.ClearCurrentSportData(currentSportData);
//		}
		
	}

	
}
