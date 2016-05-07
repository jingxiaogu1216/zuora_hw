package servlet.requirement;

import config.Config;
import db.DbCon;
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
public class DeleteRequirementServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("requirename");

        Requirement requirement = Requirement.findRequirementByName(name);

        if (requirement == null) {
            HttpUtil.writeResp(resp, 3);
            return;
        }

        String rid = requirement.getRid();
        List<String> ownerList = requirement.getOwner();


        //Only leaf requirement can be deleted
        if (!requirement.getIsLeaf() || !Requirement.deleteByName(name)) {
            HttpUtil.writeResp(resp, 3);
            return;
        }


        //update owner collection
        for (String oid: ownerList) {
            try {
                DbCon.mongodb.getCollection(Config.getOwnerCollection()).findOneAndUpdate(
                        new Document("oid", oid),
                        new Document("$pull", new Document("requirement", rid))
                );
            }
            catch (Exception e) {
                HttpUtil.writeResp(resp, 2);
                return;
            }
        }
    }
}
