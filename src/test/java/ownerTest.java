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
import java.util.concurrent.ExecutionException;

/**
 * Created by jingxiaogu on 5/7/16.
 */
public class ownerTest {
    private static String createReqUrl = "http://localhost:8080/requirement/create";
    private static String deleteReqUrl = "http://localhost:8080/requirement/delete";
    private static String allReqUrl = "http://localhost:8080/requirement";
    private static String addOwnerUrl = "http://localhost:8080/owner/add";
    private static String allOwnerUrl = "http://localhost:8080/owner";
    private static String createOwnerUrl = "http://localhost:8080/owner/create";
    private static String deleteOwnerUrl = "http://localhost:8080/owner/delete";
    private static String deleteOwnerFromReqUrl = "http://localhost:8080/owner/requirement/delete";
    private static String accessUrl = "http://localhost:8080/access";


    public static void createOwnerTest() throws Exception {
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("ownername", "own1"));
        HttpUtil.sendPost(createOwnerUrl, urlParameters);

        urlParameters.clear();
        urlParameters.add(new BasicNameValuePair("ownername", "own2"));
        HttpUtil.sendPost(createOwnerUrl, urlParameters);

        urlParameters.clear();
        urlParameters.add(new BasicNameValuePair("ownername", "own3"));
        HttpUtil.sendPost(createOwnerUrl, urlParameters);

        urlParameters.clear();
        urlParameters.add(new BasicNameValuePair("ownername", "own4"));
        HttpUtil.sendPost(createOwnerUrl, urlParameters);

        HttpUtil.sendGet(allOwnerUrl);
    }

    public static void deleteOwnerTest() throws Exception {
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("ownername", "own4"));
        HttpUtil.sendPost(deleteOwnerUrl, urlParameters);

        HttpUtil.sendGet(allOwnerUrl);
    }

    public static void addOwnerTest() throws Exception {
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("ownername", "own1"));
        urlParameters.add(new BasicNameValuePair("requirename", "req1"));
        HttpUtil.sendPost(addOwnerUrl, urlParameters);

        urlParameters.clear();
        urlParameters.add(new BasicNameValuePair("ownername", "own2"));
        urlParameters.add(new BasicNameValuePair("requirename", "req2"));
        HttpUtil.sendPost(addOwnerUrl, urlParameters);

        urlParameters.clear();
        urlParameters.add(new BasicNameValuePair("ownername", "own3"));
        urlParameters.add(new BasicNameValuePair("requirename", "req3"));
        HttpUtil.sendPost(addOwnerUrl, urlParameters);

        HttpUtil.sendGet(allReqUrl);
        HttpUtil.sendGet(allOwnerUrl);
    }

    public static void deleteOwnerFromReqTest() throws Exception {
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("ownername", "own3"));
        urlParameters.add(new BasicNameValuePair("requirename", "req3"));
        HttpUtil.sendPost(deleteOwnerFromReqUrl, urlParameters);

        HttpUtil.sendGet(allReqUrl);
        HttpUtil.sendGet(allOwnerUrl);
    }

    public static void accessTest() throws Exception {
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("ownername", "own1"));
        HttpUtil.sendPost(accessUrl, urlParameters);
    }

    public static void main(String[] args) throws Exception{
        System.out.println("-------Create Owner Test--------");
        createOwnerTest();

        System.out.println("-------Delete Owner Test--------");
        deleteOwnerTest();

        System.out.println("-------Add Owner Test-----------");
        addOwnerTest();

        System.out.println("-------Delete Owner From Requirement Test-----");
        deleteOwnerFromReqTest();

        System.out.println("-------Access Test-----------");
        accessTest();
    }
}
