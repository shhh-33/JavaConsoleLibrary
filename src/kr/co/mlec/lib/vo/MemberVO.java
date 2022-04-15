package kr.co.mlec.lib.dao.src.kr.co.mlec.lib.vo;

public class MemberVO {

	private String id;
	private String password;
	private String name;
	private String sex;
	private String phone;
	public MemberVO() {
		super();
	}
	public MemberVO(String id, String password, String name, String sex, String phone) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.phone = phone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", sex=" + sex + ", phone=" + phone
				+ "]";
	}
	
}
