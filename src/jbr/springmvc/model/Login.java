package jbr.springmvc.model;

public class Login {

  private String username;
  private String password;
  private String msg;

  public String getUsername() {
  return username;
  }

  public void setUsername(String username) {
  this.username = username;
  }

  public String getPassword() {
  return password;
  }

  public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}

public void setPassword(String password) {
  this.password = password;
  }

}