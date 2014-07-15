package foundation.dataService.addData;

import java.sql.SQLException;

import domain.entity.MinuteSportData;
import domain.entity.OneSport;
import foundation.dataService.base.DataContext;

public class addDataService {
private DataContext dtx;
	
	public addDataService() {
		dtx=new DataContext();
	}
	//添加分钟运动数据
	public void addMinuteSportData(MinuteSportData minuteSportData){
		try {
			dtx.add(minuteSportData, MinuteSportData.class, Integer.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//添加一次运动数据
	public void addOneSportData(OneSport oneSport){
			try {
				dtx.add(oneSport, OneSport.class, Integer.class);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
}
