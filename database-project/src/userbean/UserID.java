package userbean;

public class UserID
{
	private String staffNo;
//	private String pwd;
	private String name;
	private String position;
	
	
	public UserID(){
		
	}
	public UserID(String s,String n,String p){
		staffNo=s;
		name=n;
		position=p;
	}
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
//	public String getPwd() {
//		return pwd;
//	}
//	public void setPwd(String pwd) {
//		this.pwd = pwd;
//	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}