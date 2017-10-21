package org.cypmaster.tags;

import org.cypmaster.dto.UserInputDTO;
import org.cypmaster.services.UserService;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class RecordTag extends SimpleTagSupport {


    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void doTag() throws JspException, IOException {

        UserService userService = UserService.getInstance();
        UserInputDTO userInputDTO = userService.getUserInputByKey(key);

        JspWriter out = getJspContext().getOut();
        out.print("<h2> Key:" + userInputDTO.getName() + "</h2>");
        out.print("<h2> Value:" + userInputDTO.getValue() + "</h2>");
        out.print("<h2> Category:" + userInputDTO.getCategory() + "</h2>");

    }
}
