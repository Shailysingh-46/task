package com.aem.training.site.core.models;

import com.adobe.cq.export.json.ExporterConstants;
import com.aem.training.site.core.servlets.CityServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = MyFormModel.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)

public class MyFormModel {

    protected static final String RESOURCE_TYPE = "training/components/my_form";
     @Inject
     CityServlet cityServlet;

    @Inject
    SlingHttpServletRequest request;

    private List<MyFormItem> cityList = new ArrayList<>();
    @PostConstruct
    protected void init() {

        ResourceResolver resolver = request.getResourceResolver();
        Resource resource = resolver.getResource("/content/stateandcity");
        Iterable<Resource> childResources = resource.getChildren();

        MyFormItem im = new MyFormItem();

        ValueMap vm = resource.getValueMap();
        String title = vm.get("title", String.class);
        im.setTitle(title);
        cityList.add(im);
    }

    public List<MyFormItem> getCityList() {
        return cityList;
    }
}
