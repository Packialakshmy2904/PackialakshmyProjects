package jbr.springmvc.model;

import java.util.List;

public class QuesAns {

	private List<Query> ques;
	private List<Answer> ans;
	private String uname;
	
	public List<Query> getQues() {
		return ques;
	}
	public void setQues(List<Query> ques) {
		this.ques = ques;
	}
	public List<Answer> getAns() {
		return ans;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public void setAns(List<Answer> ans) {
		this.ans = ans;
	}
	
	
}
