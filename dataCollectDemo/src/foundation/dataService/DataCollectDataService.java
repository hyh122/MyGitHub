package foundation.dataService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.App.MyApp;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.query.In;
import com.j256.ormlite.support.ConnectionSource;

import domain.entity.MinuteSportData;
import domain.entity.OneSport;
import foundation.dataService.base.DataContext;

public class DataCollectDataService {
private DataContext dtx;
	
	public DataCollectDataService() {
		dtx=new DataContext();
	}
	//��ӷ����˶�����
	public void addMinuteSportData(MinuteSportData minuteSportData){
		try {
			dtx.add(minuteSportData, MinuteSportData.class, Integer.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//���һ���˶�����
	public void addOneSportData(OneSport oneSport){
			try {
				dtx.add(oneSport, OneSport.class, Integer.class);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	//ȡ�����������˶�����
	public int getMaxSportNum(String sportDay){

		int maxNum = 0;
		try {
		
			List<String[]> max=dtx.queryBySql(OneSport.class, Integer.class, "select max(count) from T_OneSport where date='"+sportDay+"'");
			maxNum=Integer.parseInt(max.get(0)[0]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxNum;
		
	}
}
