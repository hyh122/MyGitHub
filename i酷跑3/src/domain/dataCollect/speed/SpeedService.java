package domain.dataCollect.speed;

import java.util.ArrayList;
import java.util.List;

import com.File.TxtFileUtil;

import foundation.speed.acCollect.AcBaseService;
import foundation.speed.kalmanFilter.KalmanService;
import foundation.speed.kalmanFilter.VcTimeOut;

public class SpeedService implements VcTimeOut{
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param startTime
	 * @param endTime
	 * @return
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private KalmanService acAnalyseService;
	private ISpeedTimeOut speedTimeOut=null;
	private List<SpeedData> minuteSpeeds=new ArrayList<SpeedData>();
	public SpeedService(){
		acAnalyseService = new KalmanService();
		acAnalyseService.setI(this);
	}

	public void start(){
		AcBaseService.setI(acAnalyseService);
		AcBaseService.start();
		//��ʼ�ɼ�
	}
	
	public void stop(){
		AcBaseService.stop();
		//ֹͣ�ɼ�
	}
	public void setSpeedTimeOut(ISpeedTimeOut i){
		this.speedTimeOut = i;
	}
	//�ص��ϲ�DataCollectService��SOnTimeOut����
	public void onTimeOut(SpeedData speedData){
		TxtFileUtil.writeSpeed("SpSe - send data_v to DaSe"+"\n");
		minuteSpeeds.add(speedData);
		speedTimeOut.SOnTimeOut(speedData);
		
	}
	@Override
	public void getSpeed(SpeedData a) {
		// TODO Auto-generated method stub
		onTimeOut(a);
	}
	public List<SpeedData> getSpeedDataList(){
		List<SpeedData> speedDataList=new ArrayList<SpeedData>();
		speedDataList.addAll(minuteSpeeds);
		minuteSpeeds.clear();
		return speedDataList;
	}
}
