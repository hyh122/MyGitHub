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
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private ConfigurationService configurationService;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private SpeedService speedService;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	 //�ļ�
	private File f=new File("/sdcard/HeartRates.txt"/**�ļ�·����**/);
	private HeartRateService heartRateService;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	//private MinuteSportData minuteSportData;

	public OneSport oneSport;
	/** 
	 * <!-- begin-UML-doc -->
	 * <p>�õ�ÿ���Ӳɼ�����(����ֵList&lt;MinuteSpartData&gt;)</p>
	 * <!-- end-UML-doc -->
	 * @param startTime
	 * @param endTime
	 * @return
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private DataCollectDataService dataCollectDataService;
	
	private CurrentSportData currentSportData=new CurrentSportData();
	private double tempDistance=0.0;//��ʱ���ڼ����ܾ���
	private double tempTotalCalorie=0.0;//��ʱ���ڼ����ܿ�·��
	
	private IDataCollectTimeOut dataCollectTimeOut=null;//UI����
	
	public void setDataCollectTimeOut(IDataCollectTimeOut dataCollectTimeOut) {
		this.dataCollectTimeOut = dataCollectTimeOut;
	}

	public DataCollectService() {
		//ʵ����������
		dataCollectDataService=new DataCollectDataService();
		heartRateService=new HeartRateService();
		speedService=new SpeedService();
		heartRateService.setListener(this);//����heartRateService������ΪDataCollectService
		speedService.setSpeedTimeOut(this);//����speedService������ΪDataCollectService
	}

	public List<MinuteSportData> getMinuteSportData(Date startTime, Date endTime) {
		// begin-user-code
		// TODO �Զ����ɵķ������
		return null;
		// end-user-code
	}
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param count
	 * @param date
	 * @return
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private List<OneSport> getSportByCount(Integer count, Date date) {
		// begin-user-code
		// TODO �Զ����ɵķ������
		return null;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param SportData
	 * @return
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private List<OneSport> getSportByDate(String SportDate) {
		// begin-user-code
		// TODO �Զ����ɵķ������
		return null;
		// end-user-code
	}
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public boolean start(Date startTime) {
		// begin-user-code
		// TODO �Զ����ɵķ������

//			if(heartRateService.judgeBlueToothOpen()){
				
				//ʵ����OneSport
				oneSport=new OneSport();
				//���ÿ�ʼʱ��
				oneSport.setStartTime(startTime);
				
				//ȥ���ݿ�ȶԿ�ʼʱ��
				//int maxNum=dataCollectDataService.getMaxSportNum(startTime);
				//oneSport.setCount(maxNum+1);
				oneSport.setCount(0);//��ʱ����ʹ�ã���������������д���
				
				//�������ʲɼ�����
			//	heartRateService.start();
				//�����ٶȲɼ�����
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
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void stop(Date endTime) {
		// begin-user-code
		// TODO �Զ����ɵķ������
		
		//���ý���ʱ��
		oneSport.setEndTime(endTime);
		//ֹͣ���ʲɼ�����
		//heartRateService.stop();
		//ֹͣ�ٶȲɼ�����
		speedService.stop();
	
		//���ٶȷ����ȡ�ٶ�����
//			List<Double> tempSpeedList = speedService.getSpeed(oneSport.getStartTime(), oneSport.getEndTime());
//			//�����ʷ����ȡ��������
//			List<Integer> tempHeartRateList = heartRateService.getHeartRate(oneSport.getStartTime(), oneSport.getEndTime());
//			//ÿ������������
//			List<MinuteSportData> minuteSportDataList = new ArrayList<MinuteSportData>();
//
//			for(int i=0;i<tempSpeedList.size();i++){
//				MinuteSportData minuteSportData=new MinuteSportData();
//				
//				//װ�䵽minuteSportData
//				minuteSportData.setHeartRate(tempHeartRateList.get(i));//װ������
//				minuteSportData.setSpeed(tempSpeedList.get(i));//װ���ٶ�
//				minuteSportData.setNumber(i);//װ��ɼ�����
////				minuteSportData.setCollectTime(heartRateService.getCollectTime().get(i));//װ��ɼ�ʱ��
//				minuteSportData.setOneSport(oneSport);
//			
//				//��ӵ�ÿ������������
//				minuteSportDataList.add(minuteSportData);
//			}
//			//һ���Ա��浽���ݿ�
//			dataCollectDataService.saveSportData(oneSport,minuteSportDataList);
		
			// end-user-code
	}
	/** 
	 * <!-- begin-UML-doc -->
	 * <p>�õ������˶�����</p>
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
//	public void getCurrentSportData(Date startTime,Date endTime) {
//		// begin-user-code
//		// TODO �Զ����ɵķ������
//		
//		//ʵ����currentSportData
//		currentSportData=new CurrentSportData();
//
//		//�ص�UI���onTimeOut
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
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private double calCalorie(double weight, double speed,double preCalorie) {
		// begin-user-code
		// TODO �Զ����ɵķ������
		
		return (preCalorie+speed*weight/400);
		
		// end-user-code
	}

	//ʵ���ٶȷ�����onTimeOut����
	@Override
	public void SOnTimeOut(SpeedData speedData) {
		currentSportData=new CurrentSportData();
		
		currentSportData.setCurrentSpeed(speedData.getSpeed());//װ�䵱ǰ�ٶ�
		tempDistance+=speedData.getSpeed()*60;//�ܾ���
		currentSportData.setDistance(tempDistance);//�����������ٶȵ�λΪm/s����Ϊƽ���ٶ�
		//���㿨·��
		oneSport.setUser(new User(60));//����ʹ�ã�����60KG
		currentSportData.setTotalCalorie(
				calCalorie(oneSport.getUser().getWeight(),
				currentSportData.getCurrentSpeed(),
				tempTotalCalorie));//���ÿ�·��ֵ
		
		//���getCurrentHeartRate!=-1˵��װ�����ʳɹ�,˭������˭���ϲ��onTimeOut
		if(currentSportData.getCurrentHeartRate()!=-1){
			
			
			//dataCollectTimeOut.DOnTimeOut(currentSportData.CopyCurrentSportData(currentSportData));//�ص��ϲ����
			
			//���currentSportData������
			currentSportData.ClearCurrentSportData(currentSportData);
		}
	}

	//ʵ�����ʷ�����onTimeOut����
	@Override
	public void HOnTimeOut(HeartRateData heartRateData) {
		
		currentSportData.setCurrentHeartRate(heartRateData.getHeartRate());//װ�䵱ǰ����
		TxtFileUtil.appendToFile("HOnTimeOut:"+currentSportData.getCurrentHeartRate()+"\r\n", f);
		this.dataCollectTimeOut.DOnTimeOut(currentSportData);
//		//˭������˭���ϲ��onTimeOut
//		if(currentSportData.getTotalCalorie()!=-1){
//			synchronized(this){
//			dataCollectTimeOut.DOnTimeOut(currentSportData.CopyCurrentSportData(currentSportData));//�������ݻص����ϲ����
//			}
//			//���currentSportData������
//			currentSportData.ClearCurrentSportData(currentSportData);
//		}
		
	}

	
}
