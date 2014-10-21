package foundation.dataService;

import java.sql.SQLException;
import java.util.List;

import domain.dataCollect.OneSport;
import domain.systemManaConfig.Friends;
import foundation.dataService.base.DataContext;

public class SystemManagerDataService {
	
		private DataContext dtx;
		
		public SystemManagerDataService() {
			dtx=new DataContext();
		}
		//��Ӻ���
		public void addFriend(Friends friend){
			try {
				dtx.add(friend, Friends.class, Integer.class);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//��id���Һ���
		public Friends getFriendsById(int id){
			Friends friend=null;
			try {
				friend=dtx.queryById(Friends.class, Integer.class, id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return friend;
		}
		
		//��ȡ���к��ѵļ�¼��
		public long gerCountOfFriends(){
			long count=0;
			try {
				count=dtx.countof(Friends.class, Integer.class);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return count;
		}
		//��ȡ���к���
		public List<Friends> getAllFriend(){
			List<Friends> friends=null;
			try {
				friends=dtx.queryForAll(Friends.class, Integer.class);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return friends;
			
		}
		
	}


