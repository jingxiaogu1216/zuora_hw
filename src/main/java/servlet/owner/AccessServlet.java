package servlet.owner;

import com.google.gson.Gson;
import db.Owner;
import db.Requirement;
import util.HttpUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * Created by jingxiaogu on 5/6/16.
 */
public class AccessServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ownerName = req.getParameter("ownername");
        Owner owner = Owner.findOwnerByName(ownerName);

        if (owner == null) {
            HttpUtil.writeResp(resp, 3);
            return;
        }

        List<String> reqList = owner.getRequirement();
        Queue<String> queue = new LinkedList<>();
        HashSet<String> set = new HashSet<>();

        for (String rid : reqList) {
            queue.add(rid);
            set.add(rid);
            Requirement requirement = Requirement.findRequirementById(rid);
            if (requirement == null)
                continue;
            resp.getWriter().write(new Gson().toJson(requirement) + "\n");
        }


        while (!queue.isEmpty()) {
            String rid = queue.poll();
            List<Requirement> requirementList = Requirement.findRequirementByParentId(rid);
            for (Requirement r : requirementList) {
                if (!set.contains(r.getRid())) {
                    set.add(rid);
                    resp.getWriter().write(new Gson().toJson(r) + "\n");
                    queue.add(r.getRid());
                }
            }
        }


    }
}
