package ui.statisticsDisplay.viewModel;

public class FriendsListModel {

	private String name;   //显示的数据
	//private String sortLetters;  //显示数据拼音的首字母
	private String personalWord;//个性签名
	
	
	public FriendsListModel() {
		super();
	}
	public FriendsListModel(String name, String personalWord) {
		super();
		this.name = name;
		this.personalWord = personalWord;
	}
	public String getPersonalWord() {
		return personalWord;
	}
	public void setPersonalWord(String personalWord) {
		this.personalWord = personalWord;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
