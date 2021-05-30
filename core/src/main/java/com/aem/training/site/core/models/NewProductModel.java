package com.aem.training.site.core.models;


import com.adobe.cq.export.json.ExporterConstants;
import com.aem.training.site.core.services.SimpleService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = NewProductModel.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class NewProductModel {

    protected static final String RESOURCE_TYPE = "training/components/my_product";

    @OSGiService
    private SimpleService simpleService;
    private List<Bike> bikeList = Collections.emptyList();



    @PostConstruct
    protected void init() {
        bikeList = simpleService.getBikeList();


    }


    public List<Bike> getBikeList() {
        return bikeList;
    }

}