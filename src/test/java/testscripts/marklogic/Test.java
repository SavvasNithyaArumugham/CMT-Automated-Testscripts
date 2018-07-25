package testscripts.marklogic;

import java.util.HashMap;

import net.minidev.json.JSONArray;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.pearson.automation.utils.APIUtil;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		APIUtil api = new APIUtil();
		HashMap<String, String> header = new HashMap<String, String>();
		String scheme = "https";
		String hostAuth = "identity-internal-test.pearson.com/auth/json/pearson/authenticate";
		String host = "staging.data.pearson.com/work/dc73515a-fcee-4cd1-a368-3ba1b565cb9a?db=qa2";
		String path = "";
		String entity = "";
		String tokenId = "";
		
		header.put("Content-Type", "application/json");
		header.put("X-OpenAM-Username", "MDS_TEST");
		header.put("X-OpenAM-Password", "Pearson987");
		String loginResponse = api.post(scheme, hostAuth, path, entity, header);
		if(loginResponse != null){
			ReadContext rc = JsonPath.parse(loginResponse);
			String keyTokenId = "$.tokenId";
			tokenId = rc.read(keyTokenId);
			System.out.println("TOKEN ID : " + tokenId);
		}
		
		header.put("x-apikey", "5x8gLqCCfkOfgPkFd9YNotcAykeldvVd");
		header.put("X-PearsonSSOSession", tokenId);
		header.put("Accept", "application/ld+json");
		header.put("Authorization", "Basic Ymx1ZWJlcnJ5OmVAQkhSTUF2M2V5S2xiT1VjS0tAWl56Q0ZhMDRtYw==");
		String response = api.get(scheme, host, path, header);
		
		if(response != null){
			ReadContext rc = JsonPath.parse(response);
			String keyDateCreated = "$.dateCreated";
			String dateCreated = rc.read(keyDateCreated);
			System.out.println("DATE CREATED : " + dateCreated);
			
			String keydateModified = "$.dateModified";
			String dateModified = rc.read(keydateModified);
			System.out.println("DATE MODIFIED : " + dateModified);
			
			String keyId = "$.id";
			String id = rc.read(keyId);
			System.out.println("ID : " + id);
			
			String keyType = "$.type[*]";
			JSONArray type = rc.read(keyType);
			System.out.println("TYPE : " + type.toString());
		}
	}

}
