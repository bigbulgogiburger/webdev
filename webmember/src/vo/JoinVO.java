package vo;

public class JoinVO {
	private String id;
	private String pw;
	private String name;
	private String idMsg;
	private String pwMsg;
	

	private String nameMsg;
	
	public JoinVO() {
		
	}

	public JoinVO(String id, String pw, String name) {
		this.id=id;
		this.pw=pw;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getIdMsg() {
		return idMsg;
	}

	public void setIdMsg(String idMsg) {
		this.idMsg = idMsg;
	}

	public String getPwMsg() {
		return pwMsg;
	}

	public void setPwMsg(String pwMsg) {
		this.pwMsg = pwMsg;
	}

	public String getNameMsg() {
		return nameMsg;
	}

	public void setNameMsg(String nameMsg) {
		this.nameMsg = nameMsg;
	}
	
}
