package servlet.owner;

import config.Config;
import db.DbCon;
import db.Owner;
import db.Requirement;
import util.HttpUtil;
import org.bson.Document;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by jingxiaogu on 5/6/16.
 */
public class DeleteOwnerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("ownername");
        Owner owner = Owner.findOwnerByName(name);
        if (owner == null) {
            HttpUtil.writeResp(resp, 3);
            return;
        }

        String oid = owner.getId();

        List<String> reqList = owner.getRequirement();
        if (!Owner.deleteByName(name)) {
            HttpUtil.writeResp(resp, 3);
            return;
        }

        //update requirement collection
        for (String rid: reqList) {
            try {
                DbCon.mongodb.getCollection(Config.getRequirementCollection()).findOneAndUpdate(
                        new Document("rid", rid),
                        new Document("$pull", new Document("owner", oid))
                );
            }
            catch (Exception e) {
                HttpUtil.writeResp(resp, 2);
                return;
            }
        }


    }
}
