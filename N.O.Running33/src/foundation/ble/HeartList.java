package foundation.ble;

import java.util.ArrayList;
import java.util.List;

public class HeartList {
	private List<Integer> heartRates=new ArrayList<Integer>();
	
	public List<Integer> getHeartRates() {
		List<Integer> LheartRate=new ArrayList<Integer>();
		LheartRate.addAll(heartRates);
		return LheartRate;
	}
	//�����ʼ������������������	
	public synchronized void add(Integer heartRate){
		heartRates.add(heartRate);
	}
	//������ʼ��ϵ�����
	public synchronized void clear(){
		heartRates.clear();
	}
	
	
}
