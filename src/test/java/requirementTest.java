import util.HttpUtil;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import java.util.*;

/**
 * Created by jingxiaogu on 5/7/16.
 */


public class requirementTest {
    private static String createReqUrl = "http://localhost:8080/requirement/create";
    private static String deleteReqUrl = "http://localhost:8080/requirement/delete";
    private static String allReqUrl = "http://localhost:8080/requirement";
    private static String addOwnerUrl = "http://localhost:8080/owner/add";
    private static String allOwnerUrl = "http://localhost:8080/owner";
    private static String createOwnerUrl = "http://localhost:8080/owner/create";
    private static String deleteOwnerUrl = "http://localhost:8080/owner/delete";
    private static String deleteOwnerFromReqUrl = "http://localhost:8080/owner/requirement/delete";


    public static void createReqTest() throws Exception{
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("requirename", "req1"));
        urlParameters.add(new BasicNameValuePair("desc", "this is req1"));
        urlParameters.add(new BasicNameValuePair("parentname", "Root"));
        HttpUtil.sendPost(createReqUrl, urlParameters);

        urlParameters.clear();
        urlParameters.add(new BasicNameValuePair("requirename", "req2"));
        urlParameters.add(new BasicNameValuePair("desc", "this is req2"));
        urlParameters.add(new BasicNameValuePair("parentname", "req1"));
        HttpUtil.sendPost(createReqUrl, urlParameters);

        urlParameters.clear();
        urlParameters.add(new BasicNameValuePair("requirename", "req3"));
        urlParameters.add(new BasicNameValuePair("desc", "this is req3"));
        urlParameters.add(new BasicNameValuePair("parentname", "req1"));
        HttpUtil.sendPost(createReqUrl, urlParameters);

        urlParameters.clear();
        urlParameters.add(new BasicNameValuePair("requirename", "req4"));
        urlParameters.add(new BasicNameValuePair("desc", "this is req4"));
        urlParameters.add(new BasicNameValuePair("parentname", "req1"));
        HttpUtil.sendPost(createReqUrl, urlParameters);

        urlParameters.clear();
        urlParameters.add(new BasicNameValuePair("requirename", "req5"));
        urlParameters.add(new BasicNameValuePair("desc", "this is req5"));
        urlParameters.add(new BasicNameValuePair("parentname", "req2"));
        HttpUtil.sendPost(createReqUrl, urlParameters);

        urlParameters.clear();
        urlParameters.add(new BasicNameValuePair("requirename", "req6"));
        urlParameters.add(new BasicNameValuePair("desc", "this is req6"));
        urlParameters.add(new BasicNameValuePair("parentname", "req5"));
        HttpUtil.sendPost(createReqUrl, urlParameters);

        urlParameters.clear();
        urlParameters.add(new BasicNameValuePair("requirename", "req7"));
        urlParameters.add(new BasicNameValuePair("desc", "this is req7"));
        urlParameters.add(new BasicNameValuePair("parentname", "req6"));
        HttpUtil.sendPost(createReqUrl, urlParameters);

        HttpUtil.sendGet(allReqUrl);
    }

    public static void deleteReqTest() throws Exception{
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("requirename", "req7"));
        HttpUtil.sendPost(deleteReqUrl, urlParameters);
        HttpUtil.sendGet(allReqUrl);
    }


    public static void main(String[] args) throws Exception{
        System.out.println("------Requirement Create Test--------");
        createReqTest();
        System.out.println("-------Requirement Delete Test--------");
        deleteReqTest();
    }

}
