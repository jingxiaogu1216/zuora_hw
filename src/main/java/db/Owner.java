package db;

import com.mongodb.client.FindIterable;
import config.Config;
import org.bson.Document;
import util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jingxiaogu on 5/6/16.
 */
public class Owner {
    private String oid;
    private String name;
    private List<String> requirement = new ArrayList<>();

    public String getId() {
        return oid;
    }

    public void setId(String oid) {
        this.oid = oid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRequirement() {
        return requirement;
    }

    public void setRequirement(List<String> requirement) {
        this.requirement = requirement;
    }

    public boolean insert() {
        oid = Util.uuid();
        Document doc = new Document();
        doc.append("oid", oid)
                .append("name", name)
                .append("requirement", requirement);
        try {
            DbCon.mongodb.getCollection("owner").insertOne(doc);
        }
        catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Owner findOwnerByName(String name) {
        FindIterable<Document> iter = DbCon.mongodb.getCollection(Config.getOwnerCollection()).find(new Document("name", name));
        Owner owner = new Owner();
        if (iter.iterator().hasNext()) {
            Document doc = iter.iterator().next();
            owner.setName((String) doc.get("name"));
            owner.setId((String) doc.get("oid"));
            owner.setRequirement((List<String>) doc.get("requirement"));
            return owner;
        }
        return null;
    }

    public static boolean deleteByName(String name) {
        try {
            DbCon.mongodb.getCollection(Config.getOwnerCollection()).findOneAndDelete(new Document("name", name));
        }
        catch (Exception e) {
            return false;
//            e.printStackTrace();
        }
        return true;
    }
}
