package Railway;

public class Account {
	
	private String email;
	private String password;
	private String pid;
	
	public Account(String email, String password, String pid) {
		this.email = email;
		this.password = password;
		this.pid = pid;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPid() {
		return this.pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
}
