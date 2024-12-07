package com.transys.service;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transys.dao.TrackingDao;
import com.transys.domain.Tracking;
import com.transys.util.OpcDataMap;
import com.transys.util.UtilClass;

@Service
public class TrackingServiceImpl5 implements TrackingService5{
	
	@Autowired
	private TrackingDao trackingDao;
	
	private static final Logger logger = LoggerFactory.getLogger(TrackingServiceImpl5.class);
	
	
	public void trackingDataSet(int curLocation, String setDataDir) 
			throws InterruptedException, ExecutionException {
		OpcDataMap opcDataMap = new OpcDataMap();
		
		Map<String, JSONArray> dataMap = opcDataMap.getOpcDataListMap2(setDataDir);
				
		JSONArray rowsArray = dataMap.get("dataList");			
		
		Tracking tracking = new Tracking();
		StringBuffer desc = new StringBuffer();		
		for(int i=0; i<rowsArray.size(); i++) {
			JSONObject rowObj = (JSONObject) rowsArray.get(i);
			
			String devicecode = "";
			String tagName = rowObj.get("tagName").toString();
			String value = rowObj.get("value").toString();
			
			String pumBun = "";

			if("DEVICE".equals(tagName)) {
				devicecode = value;
			}
			
			if("PUMBUN".equals(tagName)) {
				//PUMBUN_01 : 품번 01 ~ 99 (4자리 포맷으로 변경해야됨)
				pumBun = String.format("%04d",Integer.parseInt(value));
				tracking.setPumbun(pumBun);
			}
			
			if("PRD_CHK".equals(tagName)) {
				//PRD_01 : 제품감지, PRD_CHK_01 : 제품감지시 1
				//PRD_01 : 1 -> PRD_CHK01 1변경됨(오토닉스 경보에서 지정) 
				//PRD_CHK_01이 1일때 동작			
				tracking.setCurLocation(curLocation);
				
				//PRD_CHK01이 1이면서(제품이 위치하며)
				if(curLocation < 12) {
					if("1".equals(value)) {
						//품번이 0이 아니면
						if(!"0000".equals(pumBun)) {
							if(!"".equals(devicecode)) {
								tracking.setDevicecode(devicecode);	
	
								desc.append("DEVICECODE : "+tracking.getDevicecode()+"// ");
								desc.append("PUMBUN : "+tracking.getPumbun()+"// ");
								desc.append("CURLOCATION : "+tracking.getCurLocation()+"// ");
								desc.append("VALUE : "+value);
								
								
								logger.info("TRACKING(57호기) : {}",desc.toString());						
								//트래킹 실행
								trackingDao.ccf1Tracking01(tracking);
								//지연시간 0.3초
								Thread.sleep(300);
								
								//트래킹처리 후 PRD_CHK_01 0으로 변경
								opcDataMap.setOpcData(setDataDir+".PRD_CHK", 0);
							}
						}
					}
				}else {
					//품번이 0이 아니면
					if(!"0000".equals(pumBun)) {
						if(!"".equals(devicecode)) {
							tracking.setDevicecode(devicecode);	

							desc.append("DEVICECODE : "+tracking.getDevicecode()+"// ");
							desc.append("PUMBUN : "+tracking.getPumbun()+"// ");
							desc.append("CURLOCATION : "+tracking.getCurLocation()+"// ");
							desc.append("VALUE : "+value);
							
							
							logger.info("TRACKING(57호기) : {}",desc.toString());						
							//트래킹 실행
							trackingDao.ccf1Tracking01(tracking);
							//지연시간 0.3초
							Thread.sleep(300);
							
						}
					}					
				}
			}
		}
	}

	//[10]세정장입 : NO.1 세정기 DIPS조 처리품
	@Override
	public void cm1Tracking10_1() throws InterruptedException, ExecutionException {
		
		String setDataDir = "Transys.TRACKING.CM02.C10_1";
				
		trackingDataSet(10,setDataDir);
	}


	//[10]세정장입 : NO.2 세정기 DIPS조 처리품
	@Override
	public void cm1Tracking10_2() throws InterruptedException, ExecutionException {
		
		String setDataDir = "Transys.TRACKING.CM02.C10_2";
				
		trackingDataSet(10,setDataDir);
	}


	//[11]소려로 장입 : 소려로 로내 (1)처리품
	@Override
	public void cm1Tracking11_1() throws InterruptedException, ExecutionException {
		
		String setDataDir = "Transys.TRACKING.CM02.C11_1";
				
		trackingDataSet(11,setDataDir);
	}


	//[11]소려로 장입 : 소려로 로내 (2)처리품
	@Override
	public void cm1Tracking11_2() throws InterruptedException, ExecutionException {
		
		String setDataDir = "Transys.TRACKING.CM02.C11_2";
				
		trackingDataSet(11,setDataDir);	
	}



	@Override
	public void cm1Tracking12() throws InterruptedException, ExecutionException {
		
		String setDataDir = "Transys.TRACKING.CM02.C12";
				
		trackingDataSet(12,setDataDir);	
	}	
}
