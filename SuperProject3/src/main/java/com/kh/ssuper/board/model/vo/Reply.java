package com.kh.ssuper.board.model.vo;

public class Reply {
	
	private int replyNo;
	private String replyContent;
	private int refBno;
	private String replyWriter;
	private String createDate;
	private String status;
	public Reply() {
		super();
	}
	public Reply(int replyNo, String replyContent, int refBno, String replyWriter, String createDate, String status) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.refBno = refBno;
		this.replyWriter = replyWriter;
		this.createDate = createDate;
		this.status = status;
	}
	public int getReplyNo() {
		return replyNo;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public int getRefBno() {
		return refBno;
	}
	public String getReplyWriter() {
		return replyWriter;
	}
	public String getCreateDate() {
		return createDate;
	}
	public String getStatus() {
		return status;
	}
	
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public void setRefBno(int refBno) {
		this.refBno = refBno;
	}
	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", refBno=" + refBno + ", replyWriter="
				+ replyWriter + ", createDate=" + createDate + ", status=" + status + "]";
	}
	
	
	

}
