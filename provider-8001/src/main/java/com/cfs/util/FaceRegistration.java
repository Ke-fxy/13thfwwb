package com.cfs.util;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;


public class FaceRegistration {
	
	public static JSONObject Faceregistrtion(AipFace client,String groupId,String userId,String image,String imageType){
		 // 传入可选参数调用接口
	    HashMap<String, String> options = new HashMap<String, String>();
	    options.put("user_info", "user's info");
	    options.put("quality_control", "NORMAL");
	    options.put("liveness_control", "LOW");
		options.put("action_type", "REPLACE");
	    // 人脸注册
	    JSONObject res = client.addUser(image, imageType, groupId, userId, options);
	    
		return res;
	}
	
}

