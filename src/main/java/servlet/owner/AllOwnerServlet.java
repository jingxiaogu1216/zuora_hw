package servlet.owner;


import com.google.gson.Gson;
import config.Config;
import db.DbCon;
import db.Requirement;
import org.omg.CORBA.TRANSACTION_MODE;
import util.HttpUtil;
import org.bson.Document;
import com.mongodb.Block;

import com.mongodb.client.FindIterable;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jingxiaogu on 5/7/16.
 */
public class AllOwnerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FindIterable<Document> iterable = DbCon.mongodb.getCollection(Config.getOwnerCollection()).find();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document){
                try {
                    resp.getWriter().write(new Gson().toJson(document) + "\n");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
