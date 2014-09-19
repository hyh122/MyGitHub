package domain.dataCollect.base;


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

import domain.systemManaConfig.User;

@DatabaseTable(tableName="T_OneSport")
public class OneSport {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public static final String Date_NAME = "date";
	public static final String COUNT_NAME= "count";
	
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
	@DatabaseField(canBeNull=false,columnName=COUNT_NAME)
	private Integer count;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@ForeignCollectionField(eager=false)
	ForeignCollection<MinuteSportData> MinuteSportDatas;
	
	private List<MinuteSportData> lMinuteSportDatas;
	public List<MinuteSportData> getlMinuteSportDatas() {
		return lMinuteSportDatas;
	}
	public void setlMinuteSportDatas(List<MinuteSportData> lMinuteSportDatas) {
		this.lMinuteSportDatas = lMinuteSportDatas;
	}
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public void setMinuteSportDatas(ForeignCollection<MinuteSportData> minuteSportDatas) {
		ForeignCollection<MinuteSportData> x=new ForeignCollection<MinuteSportData>() {
			
			@Override
			public boolean add(MinuteSportData arg0) {
				// TODO �Զ����ɵķ������
				return false;
			}

			@Override
			public boolean addAll(Collection<? extends MinuteSportData> arg0) {
				// TODO �Զ����ɵķ������
				return false;
			}

			@Override
			public void clear() {
				// TODO �Զ����ɵķ������
				
			}

			@Override
			public boolean contains(Object arg0) {
				// TODO �Զ����ɵķ������
				return false;
			}

			@Override
			public boolean containsAll(Collection<?> arg0) {
				// TODO �Զ����ɵķ������
				return false;
			}

			@Override
			public boolean isEmpty() {
				// TODO �Զ����ɵķ������
				return false;
			}

			@Override
			public Iterator<MinuteSportData> iterator() {
				// TODO �Զ����ɵķ������
				return null;
			}

			@Override
			public boolean remove(Object arg0) {
				// TODO �Զ����ɵķ������
				return false;
			}

			@Override
			public boolean removeAll(Collection<?> arg0) {
				// TODO �Զ����ɵķ������
				return false;
			}

			@Override
			public boolean retainAll(Collection<?> arg0) {
				// TODO �Զ����ɵķ������
				return false;
			}

			@Override
			public int size() {
				// TODO �Զ����ɵķ������
				return 0;
			}

			@Override
			public Object[] toArray() {
				// TODO �Զ����ɵķ������
				return null;
			}

			@Override
			public <T> T[] toArray(T[] arg0) {
				// TODO �Զ����ɵķ������
				return null;
			}

			@Override
			public CloseableIterator<MinuteSportData> closeableIterator() {
				// TODO �Զ����ɵķ������
				return null;
			}

			@Override
			public void closeLastIterator() throws SQLException {
				// TODO �Զ����ɵķ������
				
			}

			@Override
			public CloseableWrappedIterable<MinuteSportData> getWrappedIterable() {
				// TODO �Զ����ɵķ������
				return null;
			}

			@Override
			public boolean isEager() {
				// TODO �Զ����ɵķ������
				return false;
			}

			@Override
			public CloseableIterator<MinuteSportData> iteratorThrow()
					throws SQLException {
				// TODO �Զ����ɵķ������
				return null;
			}

			@Override
			public int refresh(MinuteSportData arg0) throws SQLException {
				// TODO �Զ����ɵķ������
				return 0;
			}

			@Override
			public int refreshAll() throws SQLException {
				// TODO �Զ����ɵķ������
				return 0;
			}

			@Override
			public int refreshCollection() throws SQLException {
				// TODO �Զ����ɵķ������
				return 0;
			}

			@Override
			public int update(MinuteSportData arg0) throws SQLException {
				// TODO �Զ����ɵķ������
				return 0;
			}

			@Override
			public int updateAll() throws SQLException {
				// TODO �Զ����ɵķ������
				return 0;
			}
			
		};
		MinuteSportDatas = minuteSportDatas;
	}
	
	
	
}
