package servlet.requirement;

import config.Config;
import db.DbCon;
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
public class CreateRequirementServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("requirename");
        String desc = req.getParameter("desc");
        String parentName = req.getParameter("parentname");

        //check input
        if (name == null || name.length() == 0 || desc == null || desc.length() == 0 || parentName == null || parentName.length() == 0) {
            HttpUtil.writeResp(resp, 2);
            return;
        }

        //check whether parent requirement exists
        String parentId = null;
        if (!parentName.equals("Root")) {
            try {
                Document doc = DbCon.mongodb.getCollection(Config.getRequirementCollection()).findOneAndUpdate(
                        new Document("name", parentName),
                        new Document("$set", new Document("isLeaf", false))
                );
                parentId = (String) doc.get("rid");
            }
            catch (Exception e) {
                HttpUtil.writeResp(resp, 2);
                return;
//                e.printStackTrace();
            }
            if (parentId == null) {
                HttpUtil.writeResp(resp, 2);
                return;
            }
        }
        else
            parentId = parentName;

        Requirement requirement = new Requirement();
        requirement.setName(name);
        requirement.setDesc(desc);
        requirement.setParentRid(parentId);
        if (!requirement.insert())
            HttpUtil.writeResp(resp, 3);
        else {
            HttpUtil.writeResp(resp, 0);
        }
    }
}
