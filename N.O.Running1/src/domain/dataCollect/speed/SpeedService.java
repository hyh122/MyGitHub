package domain.dataCollect.speed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import ui.statisticsDisplay.Activity.StartrunningActivity;

import domain.dataCollect.DataCollectService;

public class SpeedService  {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param startTime
	 * @param endTime
	 * @return
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	
	private ISpeedTimeOut speedTimeOut=null;
	
//	public List<Double> getSpeed(Date startTime, Date endTime) {
//		// begin-user-code
//		// TODO �Զ����ɵķ������
//		List<Double> speedList=new ArrayList<Double>();
//		
//		if(startTime.equals(endTime)){
//			speedList.add((new Random().nextInt(10) + 1)*1.0);
//		}
//		else{
//			speedList.add(5*1.0);
//			speedList.add(5*1.0);
//			speedList.add(6*1.0);
//			speedList.add(7*1.0);
//			speedList.add(6.8*1.0);
//		}
//		return speedList;
//		// end-user-code
//	}

	public void setSpeedTimeOut(ISpeedTimeOut speedTimeOut) {
		this.speedTimeOut = speedTimeOut;
	}

	public void start(){

		//��ʼ�ɼ�
	}
	
	public void stop(){
		
		//ֹͣ�ɼ�
	}
	
	//�ص��ϲ�DataCollectService��SOnTimeOut����
	public void onTimeOut(SpeedData speedData){
		speedTimeOut.SOnTimeOut(speedData);
	}

}
