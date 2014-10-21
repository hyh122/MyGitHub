package domain.systemManaConfig;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import domain.dataCollect.OneSport;
@DatabaseTable(tableName="T_Friends")
public class Friends {
	
	public static final String EMAIL = "email";
	public static final String ONEANDMOREUSER = "oneAndMoreUser";
	@DatabaseField(generatedId = true)
	private int id;
	
	@DatabaseField(canBeNull = false,columnName = EMAIL)
	private String email;
	
	@DatabaseField(canBeNull = false)
	private String nickName;//�û���
	//��ͷ����ֽ���
	@DatabaseField(columnDefinition = "LONGBLOB not null",
			dataType = DataType.BYTE_ARRAY)
	private byte[] protrait;
	//��ע��
	@DatabaseField(canBeNull=true)
	private String anotherName;
	
	@DatabaseField(canBeNull=true)
	private String personalWord;
	
	@DatabaseField(canBeNull = false, foreign = true,columnName =ONEANDMOREUSER)
	private User oneAndMoreUser;//��Ӧ�ĸ��û��ĺ���

	@DatabaseField(canBeNull = false, foreign = true)
	private User oneAndOneUser;//���û���ĸ��˺Ŷ�Ӧ

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getProtrait() {
		return protrait;
	}

	public void setProtrait(byte[] protrait) {
		this.protrait = protrait;
	}

	public String getAnotherName() {
		return anotherName;
	}

	public void setAnotherName(String anotherName) {
		this.anotherName = anotherName;
	}

	public String getPersonalWord() {
		return personalWord;
	}

	public void setPersonalWord(String personalWord) {
		this.personalWord = personalWord;
	}

	public User getOneAndMoreUser() {
		return oneAndMoreUser;
	}

	public void setOneAndMoreUser(User oneAndMoreUser) {
		this.oneAndMoreUser = oneAndMoreUser;
	}

	public User getOneAndOneUser() {
		return oneAndOneUser;
	}

	public void setOneAndOneUser(User oneAndOneUser) {
		this.oneAndOneUser = oneAndOneUser;
	}
	
	
	
	

}
