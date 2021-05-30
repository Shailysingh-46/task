package com.aem.training.site.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.adobe.cq.export.json.ExporterConstants;
import com.aem.training.site.core.services.SimpleServiceDemo;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.apache.sling.settings.SlingSettingsService;

import java.util.*;

@Model(adaptables = {SlingHttpServletRequest.class,Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = TestModel.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)

public class TestModel {


    protected static final String RESOURCE_TYPE = "training/components/test";

    @ValueMapValue(name = "text")
    private String title;

    @OSGiService
    private SlingSettingsService settings;

    @OSGiService
    private SimpleServiceDemo simpleServiceDemo;

    @Inject
    SlingHttpServletRequest request;

    @SlingObject
    private Resource currentResource;

    @ScriptVariable
    private ValueMap pageProperties;

    private String pageTitle;
    private List<MyProduct> productsList = Collections.emptyList();


    @PostConstruct
    protected void init() {

        productsList = simpleServiceDemo.getMyProductList();
    }

    public List<MyProduct> getProductsList() {
        return productsList;

    }


    public String getTitle() {
        return title;
    }

    public String getSample() {
        return "sample content";
    }
}
