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
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public static final String Date_NAME = "date";

	public static final String COUNT_NAME = "count";
	
	@DatabaseField(generatedId=true)
	private int id;
	
	@DatabaseField(canBeNull=false,columnName=Date_NAME)
	private String date;
	
	@DatabaseField(canBeNull=false)
	private Date startTime;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@DatabaseField(canBeNull=false)
	private Date endTime;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@DatabaseField(canBeNull=false,columnName=COUNT_NAME)
	private Integer count;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
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
		List<MinuteSportData> LminuteSportDatas) {
		
		ForeignCollection<MinuteSportData> minuteSportDatas=new ForeignCollection<MinuteSportData>() {
			
			
			@Override
			public boolean add(MinuteSportData object) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean addAll(
					Collection<? extends MinuteSportData> collection) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void clear() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean contains(Object object) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean containsAll(Collection<?> collection) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Iterator<MinuteSportData> iterator() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean remove(Object object) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean removeAll(Collection<?> collection) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean retainAll(Collection<?> collection) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public int size() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Object[] toArray() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public <T> T[] toArray(T[] array) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public CloseableIterator<MinuteSportData> closeableIterator() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void closeLastIterator() throws SQLException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public CloseableWrappedIterable<MinuteSportData> getWrappedIterable() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isEager() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public CloseableIterator<MinuteSportData> iteratorThrow()
					throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int refresh(MinuteSportData arg0) throws SQLException {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int refreshAll() throws SQLException {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int refreshCollection() throws SQLException {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int update(MinuteSportData arg0) throws SQLException {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int updateAll() throws SQLException {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		
		MinuteSportDatas = minuteSportDatas;
	}
	
	
	
	
}
