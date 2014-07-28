package domain.entity;


import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.CloseableWrappedIterable;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="T_OneSport")
public class OneSport {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public static final String Date_NAME = "date";
	
	@DatabaseField(generatedId=true)
	private int id;
	
	@DatabaseField(canBeNull=false,columnName=Date_NAME)
	private String date;
	
	@DatabaseField(canBeNull=false)
	private Date startTime;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@DatabaseField(canBeNull=false)
	private Date endTime;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@DatabaseField(canBeNull=false)
	private Integer count;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@ForeignCollectionField(eager=false)
	ForeignCollection<MinuteSportData> MinuteSportDatas;
	
	private List<MinuteSportData> LMinuteSportData;
	
	
	public List<MinuteSportData> getLMinuteSportData() {
		return LMinuteSportData;
	}
	public void setLMinuteSportData(List<MinuteSportData> lMinuteSportData) {
		LMinuteSportData = lMinuteSportData;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public ForeignCollection<MinuteSportData> getMinuteSportDatas() {
		return MinuteSportDatas;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setMinuteSportDatas(
			ForeignCollection<MinuteSportData> minuteSportDatas) {
		MinuteSportDatas = minuteSportDatas;
	}
	
	
	
	
}
