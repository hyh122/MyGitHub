package domain.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import domain.entity.MinuteSportData;
import domain.entity.OneSport;

import foundation.dataService.base.DataContext;



public class DataCollectService {
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
//	public SpeedService speedService;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
//	public HeartRateService heartRateService;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
//	private ConfigurationService ConfigurationService;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
//	private SpeedService SpeedService;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	
//	private HeartRateService HeartRateService;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
//	private MinuteSportData MinuteSportData;

	/** 
	 * <!-- begin-UML-doc -->
	 * <p>�õ�ÿ���Ӳɼ�����(����ֵList&lt;MinuteSpartData&gt;)</p>
	 * <!-- end-UML-doc -->
	 * @param startTime
	 * @param endTime
	 * @return
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private DataContext dtx;
	
	public DataCollectService() {
		dtx=new DataContext();
	}
	
	
	public List<MinuteSportData> getMinuteSportData(Date startTime, Date endTime) {
		// begin-user-code
		// TODO �Զ����ɵķ������
		return null;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <p>�õ������˶�����</p>
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
//	public List<CurrentSportData> getCurrentSportData() {
//		// begin-user-code
//		// TODO �Զ����ɵķ������
//		return null;
//		// end-user-code
//	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param count
	 * @param date
	 * @return
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private OneSport getSportByCount(Integer count, String date) {
		// begin-user-code
		// TODO �Զ����ɵķ������
		List<OneSport> OneSports=null;
		OneSport oneSport=null;
		try {
			//ȡ�������˶�����
			OneSports=dtx.queryForAll(OneSport.class, Integer.class);
			Iterator<OneSport> ite=OneSports.iterator();
			while(ite.hasNext()){
				oneSport=ite.next();
				if(oneSport.getDate().equals(date)&&oneSport.getCount()==count){
				break;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return oneSport;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param SportData
	 * @return
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private List<OneSport> getSportByDate(String date) {
		List<OneSport> allOneSports=null;
		List<OneSport> oneSports=new ArrayList<OneSport>();
		OneSport oneSport=null;
		try {
			//ȡ�������˶�����
			allOneSports=dtx.queryForAll(OneSport.class, Integer.class);
			Iterator<OneSport> ite=allOneSports.iterator();
			while(ite.hasNext()){
				oneSport=ite.next();
				if(oneSport.getDate().equals(date)){
				oneSports.add(oneSport);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return oneSports;
	}

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
	private double calCalorie(int weight, int height, double distance,
			double speed) {
		// begin-user-code
		// TODO �Զ����ɵķ������
		return 0;
		// end-user-code
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������

	}

}
