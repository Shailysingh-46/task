package com.aem.training.site.core.servlets;

import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component(service = Servlet.class, immediate = true,
        property = { Constants.SERVICE_DESCRIPTION + "= City Servlet",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.paths=" + "/bin/cities" })

public class CityServlet extends SlingSafeMethodsServlet {
     private List<String> citiesList = new ArrayList<>();
    @Override
    protected void doGet(final SlingHttpServletRequest req,
                         final SlingHttpServletResponse resp) throws ServletException, IOException {
        List<String> citiesList = new ArrayList<>();
        String stateName = req.getParameter("state");
        if(StringUtils.isNotBlank(stateName)){
            ResourceResolver resolver = req.getResourceResolver();
            Resource parentRes = resolver.getResource("/content/stateandcity/" + stateName);
            for(Resource childRes:
                    parentRes.getChildren()){
                    citiesList.add(childRes.getName());
            }


        }
        resp.getWriter().write(new Gson().toJson(citiesList));



    }
}