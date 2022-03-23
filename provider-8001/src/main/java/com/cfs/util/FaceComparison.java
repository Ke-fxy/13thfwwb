package com.cfs.util;

import java.awt.*;
import java.util.ArrayList;
import java.util.Base64;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;


public class FaceComparison {
	
	public static String Facecomparison(AipFace client, Image imageU, Image imageC){
		
		MatchRequest req1 = new MatchRequest(imageU,"BASE64");
	    MatchRequest req2 = new MatchRequest(imageC, imageC);
	    ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
	    requests.add(req1);
	    requests.add(req2);
	    JSONObject res = client.match(requests);
		return res.toString(2);
		
	}

}

