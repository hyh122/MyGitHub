package domain.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import domain.entity.MinuteSportData;
import domain.entity.OneSport;

import foundation.dataService.base.DataContext;



public class DataCollectService {
	
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
//	public SpeedService speedService;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
//	public HeartRateService heartRateService;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
//	private ConfigurationService ConfigurationService;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
//	private SpeedService SpeedService;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	
//	private HeartRateService HeartRateService;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
//	private MinuteSportData MinuteSportData;

	/** 
	 * <!-- begin-UML-doc -->
	 * <p>得到每分钟采集数据(返回值List&lt;MinuteSpartData&gt;)</p>
	 * <!-- end-UML-doc -->
	 * @param startTime
	 * @param endTime
	 * @return
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private DataContext dtx;
	
	public DataCollectService() {
		dtx=new DataContext();
	}
	
	
	public List<MinuteSportData> getMinuteSportData(Date startTime, Date endTime) {
		// begin-user-code
		// TODO 自动生成的方法存根
		return null;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <p>得到本次运动数据</p>
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
//	public List<CurrentSportData> getCurrentSportData() {
//		// begin-user-code
//		// TODO 自动生成的方法存根
//		return null;
//		// end-user-code
//	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param count
	 * @param date
	 * @return
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private OneSport getSportByCount(Integer count, String date) {
		// begin-user-code
		// TODO 自动生成的方法存根
		List<OneSport> OneSports=null;
		OneSport oneSport=null;
		try {
			//取出所有运动数据
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
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<OneSport> getSportByDate(String date) {

		
				List<OneSport> oneSports = null;
		 		try {
		 			/*
		 			 * 构造查询生成器
		 			 */
					QueryBuilder<OneSport, Integer>queryBuilder =dtx.getDao(OneSport.class, Integer.class).queryBuilder();
					//查询日期为date的所有OneSport
					queryBuilder.where().eq(OneSport.Date_NAME,date);
					// prepare our sql statement
					PreparedQuery<OneSport> query = queryBuilder.prepare();
					oneSports=dtx.query(OneSport.class,Integer.class,query);
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
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private double calCalorie(int weight, int height, double distance,
			double speed) {
		// begin-user-code
		// TODO 自动生成的方法存根
		return 0;
		// end-user-code
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
