package ui.statisticsDisplay.viewModel;

public class FriendsListModel {

	private String name;   //��ʾ������
	//private String sortLetters;  //��ʾ����ƴ��������ĸ
	private String personalWord;//����ǩ��
	
	
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
