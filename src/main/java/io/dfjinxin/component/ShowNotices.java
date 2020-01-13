package io.dfjinxin.component;

import freemarker.template.Configuration;
import freemarker.template.TemplateMethodModelEx;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class ShowNotices implements TemplateMethodModelEx {

    private HttpServletRequest request;

    public ShowNotices(Configuration config, HttpServletRequest req) {
        config.setSharedVariable("ShowNotices", this);
        this.request = req;
    }

    @Override
    public Object exec(List args) {
        String msg = null;
        String type = null;
        String html = "";

        msg = (String) request.getAttribute("_bk_message");
        if (!StringUtils.isEmpty(msg)) {
            type = (String) request.getAttribute("_bk_message_type");
            if (StringUtils.isEmpty(type))
                type = "update";

            //html = "<div id=\"ap_notices\" class=\"ap-template-notice alert alert-dismissible alert-" + type + "\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>" + msg + "</div>";
            html = "<div class=\"alert alert-" + type + " alert-dismissible\">\n" +
                    "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\"></button>\n" +
                    "<h5><i class=\"icon fas fa-info\"></i> " + msg + "</h5>\n" +
                    "</div>";
        }

        return html;
    }

}