package domain.dataCollect;

public class CurrentSportData {
	/** 
	 * <!-- begin-UML-doc -->
	 * <p>�ۼƿ�·��</p>
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private double totalCalorie;
	/** 
	 * <!-- begin-UML-doc -->
	 * <p>����</p>
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private double distance;
	/** 
	 * <!-- begin-UML-doc -->
	 * <p>ʵʱ����</p>
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Integer currentHeartRate;
	/** 
	 * <!-- begin-UML-doc -->
	 * <p>�ۼ�ʱ��</p>
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String totalTime;
	/** 
	 * <!-- begin-UML-doc -->
	 * <p>ʵʱ�ٶ�</p>
	 * <!-- end-UML-doc -->
	 * @generated "UML �� Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private double currentSpeed;
	
	public CurrentSportData(){
		
	}
	//�������
	public void ClearCurrentSportData(CurrentSportData currentSportData){
		
	}
	
	//��������
	public CurrentSportData CopyCurrentSportData(CurrentSportData currentSportData){
		CurrentSportData c=new CurrentSportData();
		
		//��������
		c.setCurrentHeartRate(currentSportData.getCurrentHeartRate());
		c.setCurrentSpeed(currentSportData.getCurrentSpeed());
		c.setDistance(currentSportData.getDistance());
		c.setTotalCalorie(currentSportData.getTotalCalorie());
		
		return c;
	}
	
	public double getTotalCalorie() {
		return totalCalorie;
	}
	public void setTotalCalorie(double totalCalorie) {
		this.totalCalorie = totalCalorie;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public Integer getCurrentHeartRate() {
		return currentHeartRate;
	}
	public void setCurrentHeartRate(Integer currentHeartRate) {
		this.currentHeartRate = currentHeartRate;
	}
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime; 
	}
	public double getCurrentSpeed() {
		return currentSpeed;
	}
	public void setCurrentSpeed(double currentSpeed) {
		this.currentSpeed = currentSpeed;
	}




}
