package servlet.owner;

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
public class CreateOwnerServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("ownername");
        if (name == null || name.length() == 0) {
            HttpUtil.writeResp(resp, 2);
            return;
        }

        Owner owner = new Owner();
        owner.setName(name);
        if (!owner.insert()) {
            HttpUtil.writeResp(resp, 3);
        }
        else {
            HttpUtil.writeResp(resp, 0);
        }
    }
}
