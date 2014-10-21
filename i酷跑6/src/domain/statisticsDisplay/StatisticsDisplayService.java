package domain.statisticsDisplay;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import domain.dataCollect.MinuteSportData;
import domain.dataCollect.OneSport;
import foundation.dataService.base.DataContext;

public class StatisticsDisplayService{
	private DataContext dtx;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private MinuteSportData SportData;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private List<ModelPara> ModelPara;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private List<SchemePara> SchemePara;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Auxiliary Auxiliary;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void startRun() {
		// begin-user-code
		// TODO �Զ����ɵķ������

		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void stopRun() {
		// begin-user-code
		// TODO �Զ����ɵķ������

		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public String getSportScheme() {
		// begin-user-code
		// TODO �Զ����ɵķ������
		return "10:00";
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void getSportModel() {
		// begin-user-code
		// TODO �Զ����ɵķ������

		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private void judgeBlueTooth() {
		// begin-user-code
		// TODO �Զ����ɵķ������

		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private void judgeGPS() {
		// begin-user-code
		// TODO �Զ����ɵķ������

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
	private OneSport getSportByCount(Integer count, String date) {
		List<OneSport> oneSports = null;
		
 		try {
 			/*
 			 * �����ѯ������
 			 */
			QueryBuilder<OneSport, Integer>queryBuilder =dtx.getQueryBuilder(OneSport.class, Integer.class);
			//��ѯ����Ϊdate,��count�ε�OneSport
			queryBuilder.where().eq(OneSport.Date_NAME,date).and().eq(OneSport.COUNT_NAME,count);
			
			// prepare our sql statement
			PreparedQuery<OneSport> query = queryBuilder.prepare();
			oneSports=dtx.query(OneSport.class,Integer.class,query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
 		return oneSports.get(0);
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param SportData
	 * @return
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private List<OneSport> getSportByDate(String SportDate) {
		
		List<OneSport> oneSports = null;
 		try {
 			/*
 			 * �����ѯ������
 			 */
			QueryBuilder<OneSport, Integer>queryBuilder =dtx.getDao(OneSport.class, Integer.class).queryBuilder();
			//��ѯ����Ϊdate������OneSport
			queryBuilder.where().eq(OneSport.Date_NAME,SportDate);
			// prepare our sql statement
			PreparedQuery<OneSport> query = queryBuilder.prepare();
			oneSports=dtx.query(OneSport.class,Integer.class,query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
 		return oneSports;
	}

}
