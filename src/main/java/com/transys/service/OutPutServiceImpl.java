package com.transys.service;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transys.controller.MainController;
import com.transys.dao.OutPutDao;
import com.transys.domain.OutPut;
import com.transys.util.OpcDataMap;

@Service
public class OutPutServiceImpl implements OutPutService{

	@Autowired
	private OutPutDao outPutDao;
	
	private static final Logger logger = LoggerFactory.getLogger(OutPutServiceImpl.class);

	@Override
	public void outPut(int devicecode) {
		OutPut paramOutPut = new OutPut();
		paramOutPut.setFireno(devicecode);
		//파라미터로 받은 설비로 status값 조회
		StringBuffer desc = new StringBuffer();
		OutPut outPut = outPutDao.getOutPutDeviceStatus(paramOutPut);
			if(outPut.getWorkdate() != null) {
				//status값 0이라면
				if(outPut.isOutPutChk5()) {
					//OUTPUT_TAB에 INSERT					
					MainController.outPutChk5 = true;
					outPutDao.setOutPutSend(outPut);
					desc.append("5호기 출고요청 완료");				
				}
				
				if(outPut.isOutPutChk6()) {
					//OUTPUT_TAB에 INSERT					
					MainController.outPutChk6 = true;
					outPutDao.setOutPutSend(outPut);
					desc.append("6호기 출고요청 완료");				
				}
				
				if(outPut.isOutPutChk7()) {
					//OUTPUT_TAB에 INSERT					
					MainController.outPutChk7 = true;
					outPutDao.setOutPutSend(outPut);
					desc.append("7호기 출고요청 완료");
				}
				
				
				logger.info("OUTPUT(57호기) : {}",desc.toString());					
			}
	}

	//침탄 5~7호기
	@Override
	public void outPutTimer() throws InterruptedException, ExecutionException {
		//설비별 출고요청 가능신호
		String hogi5 = "false";
		String hogi6 = "false";
		String hogi7 = "false";

		//설비별 출고제품 체크
		String hogi5Prd = "false";
		String hogi6Prd = "false";
		String hogi7Prd = "false";
		
		//창고 출고가능 요구신호
		int outContinue = 0;
		
		//각 설비별 출고요청가능 신호 받기
		OpcDataMap opcData = new OpcDataMap();
		//창고출고가능요구 1이면
		Map<String, Object> hogi5Map = opcData.getOpcData("Transys.OUTPUT.CM02.HOGI5");
		Map<String, Object> hogi6Map = opcData.getOpcData("Transys.OUTPUT.CM02.HOGI6");
		Map<String, Object> hogi7Map = opcData.getOpcData("Transys.OUTPUT.CM02.HOGI7");
		Thread.sleep(300);
		
		hogi5 = hogi5Map.get("value").toString();
		hogi6 = hogi6Map.get("value").toString();
		hogi7 = hogi7Map.get("value").toString();

		//
		Map<String, Object> hogi5PrdMap = opcData.getOpcData("Transys.OUTPUT.CM02.HOGI5_PRD");
		Map<String, Object> hogi6PrdMap = opcData.getOpcData("Transys.OUTPUT.CM02.HOGI6_PRD");
		Map<String, Object> hogi7PrdMap = opcData.getOpcData("Transys.OUTPUT.CM02.HOGI7_PRD");
		Thread.sleep(300);
		
		hogi5Prd = hogi5PrdMap.get("value").toString();
		hogi6Prd = hogi6PrdMap.get("value").toString();
		hogi7Prd = hogi7PrdMap.get("value").toString();
		
		Map<String, Object> outContinueMap = opcData.getOpcData("Transys.PLCWRITE.CM02.DEVICECODE");
		
		outContinue = Integer.parseInt(outContinueMap.get("value").toString());
		
		//출고요청신호 확인시 1이면
		
		//1호기
		if("true".equals(hogi5)) {
			//화물 위치체크
			if("false".equals(hogi5Prd)) {
				//PLCWRITE의 설비값이 0일때
				if(outContinue == 0) {
					if(!MainController.outPutChk5) {
						outPut(5);
					}
				}
			}
		}
		
		//2호기
		if("true".equals(hogi6)) {
			//화물 위치체크
			if("false".equals(hogi6Prd)) {
				//PLCWRITE의 설비값이 0일때
				if(outContinue == 0) {
					if(!MainController.outPutChk6) {
						outPut(6);
					}
				}
			}
		}
		
		//3호기
		if("true".equals(hogi7)) {
			//화물 위치체크
			if("false".equals(hogi7Prd)){
				//PLCWRITE의 설비값이 0일때
				if(outContinue == 0) {
					if(!MainController.outPutChk7) {
						outPut(7);
					}
				}
			}
		}
	}
}
