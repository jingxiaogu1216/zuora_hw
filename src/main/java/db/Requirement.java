package db;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.FindIterable;
import config.Config;
import util.Util;
import org.bson.Document;
import com.mongodb.Block;


/**
 * Created by jingxiaogu on 5/6/16.
 */
public class Requirement {
    private String rid;
    private String name;
    private String desc;
    private String parentRid;
    private boolean isLeaf = true;
    private List<String> owner = new ArrayList<>();

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getParentRid() {
        return parentRid;
    }

    public void setParentRid(String parentRid) {
        this.parentRid = parentRid;
    }

    public boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(boolean flag) {
        this.isLeaf = flag;
    }

    public List<String> getOwner() {
        return owner;
    }

    public void setOwner(List<String> owner) {
        this.owner = owner;
    }

    public boolean insert() {
        rid = Util.uuid();
        Document doc = new Document();
        doc.append("rid", rid)
                .append("name", name)
                .append("desc", desc)
                .append("parentRid", parentRid)
                .append("isLeaf", true)
                .append("owner", owner);

        try {
            DbCon.mongodb.getCollection(Config.getRequirementCollection()).insertOne(doc);
        }
        catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Requirement findRequirementByName(String name) {
        FindIterable<Document> iter = DbCon.mongodb.getCollection(Config.getRequirementCollection()).find(new Document("name", name));
        Requirement requirement = new Requirement();
        if (iter.iterator().hasNext()) {
            Document doc = iter.iterator().next();
            requirement.setRid((String) doc.get("rid"));
            requirement.setName((String) doc.get("name"));
            requirement.setDesc((String) doc.get("desc"));
            requirement.setParentRid((String) doc.get("parentRid"));
            requirement.setIsLeaf((Boolean) doc.get("isLeaf"));
            requirement.setOwner((List<String>) doc.get("owner"));
            return requirement;
        }
        return null;
    }


    public static List<Requirement> findRequirementByParentId(String parentRid) {
        FindIterable<Document> iter = DbCon.mongodb.getCollection(Config.getRequirementCollection()).find(new Document("parentRid", parentRid));
        List<Requirement> requirements = new ArrayList<>();
        iter.forEach(new Block<Document>() {
            @Override
            public void apply(final Document doc) {
                Requirement requirement = new Requirement();
                requirement.setRid((String) doc.get("rid"));
                requirement.setName((String) doc.get("name"));
                requirement.setDesc((String) doc.get("desc"));
                requirement.setParentRid((String) doc.get("parentRid"));
                requirement.setIsLeaf((Boolean) doc.get("isLeaf"));
                requirement.setOwner((List<String>) doc.get("owner"));
                requirements.add(requirement);
            }
        });
        return requirements;
    }

    public static Requirement findRequirementById(String rid) {
        FindIterable<Document> iter = DbCon.mongodb.getCollection(Config.getRequirementCollection()).find(new Document("rid", rid));
        Requirement requirement = new Requirement();
        if (iter.iterator().hasNext()) {
            Document doc = iter.iterator().next();
            requirement.setRid((String) doc.get("rid"));
            requirement.setName((String) doc.get("name"));
            requirement.setDesc((String) doc.get("desc"));
            requirement.setParentRid((String) doc.get("parentRid"));
            requirement.setIsLeaf((Boolean) doc.get("isLeaf"));
            requirement.setOwner((List<String>) doc.get("owner"));
            return requirement;
        }
        return null;
    }


    public static boolean deleteByName(String name) {
        try {
            DbCon.mongodb.getCollection(Config.getRequirementCollection()).findOneAndDelete(new Document("name", name));
        }
        catch (Exception e) {
            return false;
//            e.printStackTrace();
        }
        return true;
    }

}
