package foundation.webservice;

import java.util.HashMap;

import com.File.TxtFileUtil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnetNet {
	public static boolean isConnect(Context context) {
		// ��ȡ�ֻ��������ӹ�����󣨰�����wi-fi,net�����ӵĹ���
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				// ��ȡ�������ӹ���Ķ���
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					// �жϵ�ǰ�����Ƿ��Ѿ�����
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
		}
		return false;
	}
	
//	public void reConnectNet(String nameSpace,String url){
//		webFlag = ConnetNet.isConnect(getActivity());
//		webService = new WebServiceUtils(nameSpace,
//				MemoWebPara.Model_URL, this);
//		HashMap<String, Object> args = new HashMap<String, Object>();
//		String loginFlag = null;
//		try {
//			loginFlag = TxtFileUtil.readTxtFile(TxtFileUtil.loginFlag);//��ȡ�û�email
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if (loginFlag != null) {
//			String[] str = loginFlag.split(",");
//			args.put("email", str[1].toString().trim());
//			webService.callWebService("getCurrentModel", args, String.class);
//		}	
//	}
}
