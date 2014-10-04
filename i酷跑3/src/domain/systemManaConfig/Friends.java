package domain.systemManaConfig;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName="T_Friends")
public class Friends {
	
	@DatabaseField(generatedId=true)
	private int id;
	@DatabaseField(canBeNull=false)
	private String friendName;
	@DatabaseField(canBeNull=true)
	private String anotherName;
	@DatabaseField(canBeNull=true)
	private String personalWord;
	
	public Friends() {
		super();
	}

	

	


	public Friends(int id, String friendName, String anotherName,
			String personalWord) {
		super();
		this.id = id;
		this.friendName = friendName;
		this.anotherName = anotherName;
		this.personalWord = personalWord;
	}






	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public String getPersonalWord() {
		return personalWord;
	}






	public void setPersonalWord(String personalWord) {
		this.personalWord = personalWord;
	}






	public String getFriendName() {
		return friendName;
	}



	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}



	public String getAnotherName() {
		return anotherName;
	}

	public void setAnotherName(String anotherName) {
		this.anotherName = anotherName;
	}
	
	

	
	
	
	
	

}
