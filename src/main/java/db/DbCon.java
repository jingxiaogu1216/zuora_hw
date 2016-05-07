package db;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientURI;
import config.Config;


/**
 * Created by jingxiaogu on 5/6/16.
 */

public class DbCon{
    public static MongoClient mongoclient = null;
    public static MongoDatabase mongodb = null;

    static {
        try {
            mongoclient = new MongoClient(new MongoClientURI(Config.getClientUrl()));
            mongodb = mongoclient.getDatabase(Config.getDatabase());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception{
    }
}
