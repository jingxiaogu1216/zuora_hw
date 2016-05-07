package servlet.owner;

import config.Config;
import db.DbCon;
import db.Owner;
import db.Requirement;
import util.HttpUtil;
import org.bson.Document;
import com.mongodb.client.FindIterable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by jingxiaogu on 5/6/16.
 */
public class addOwnerServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ownerName = req.getParameter("ownername");
        String requirementName = req.getParameter("requirename");

        Requirement requirement = Requirement.findRequirementByName(requirementName);
        Owner owner = Owner.findOwnerByName(ownerName);

        if (requirement == null || owner == null) {
            HttpUtil.writeResp(resp, 3);
            return;
        }

        //update Owner and Requirement Collections
        try {
            DbCon.mongodb.getCollection(Config.getRequirementCollection()).findOneAndUpdate(
                    new Document("rid", requirement.getRid()),
                    new Document("$push", new Document("owner", owner.getId()))
            );

            DbCon.mongodb.getCollection(Config.getOwnerCollection()).findOneAndUpdate(
                    new Document("oid", owner.getId()),
                    new Document("$push", new Document("requirement", requirement.getRid()))
            );
        }
        catch (Exception e) {
            HttpUtil.writeResp(resp, 4);
            return;
        }

        HttpUtil.writeResp(resp, 0);

    }
}
