package com.transys.domain;

public class OutPut {
	
	
	//2024-11-30 추가(1~4호기 : 1, 5~7호기 : 2)
	private int serverSelect;	
	private String deviceCode;

	private int fireno;
	private String workdate;
	private String status;
	
	private boolean outPutChk1;
	private boolean outPutChk2;
	private boolean outPutChk3;
	private boolean outPutChk4;
	private boolean outPutChk5;
	private boolean outPutChk6;
	private boolean outPutChk7;
	
	
	
	public int getServerSelect() {
		return serverSelect;
	}
	public void setServerSelect(int serverSelect) {
		this.serverSelect = serverSelect;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public int getFireno() {
		return fireno;
	}
	public void setFireno(int fireno) {
		this.fireno = fireno;
	}
	public String getWorkdate() {
		return workdate;
	}
	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isOutPutChk1() {
		return outPutChk1;
	}
	public void setOutPutChk1(boolean outPutChk1) {
		this.outPutChk1 = outPutChk1;
	}
	public boolean isOutPutChk2() {
		return outPutChk2;
	}
	public void setOutPutChk2(boolean outPutChk2) {
		this.outPutChk2 = outPutChk2;
	}
	public boolean isOutPutChk3() {
		return outPutChk3;
	}
	public void setOutPutChk3(boolean outPutChk3) {
		this.outPutChk3 = outPutChk3;
	}
	public boolean isOutPutChk4() {
		return outPutChk4;
	}
	public void setOutPutChk4(boolean outPutChk4) {
		this.outPutChk4 = outPutChk4;
	}
	public boolean isOutPutChk5() {
		return outPutChk5;
	}
	public void setOutPutChk5(boolean outPutChk5) {
		this.outPutChk5 = outPutChk5;
	}
	public boolean isOutPutChk6() {
		return outPutChk6;
	}
	public void setOutPutChk6(boolean outPutChk6) {
		this.outPutChk6 = outPutChk6;
	}
	public boolean isOutPutChk7() {
		return outPutChk7;
	}
	public void setOutPutChk7(boolean outPutChk7) {
		this.outPutChk7 = outPutChk7;
	}

	
}
