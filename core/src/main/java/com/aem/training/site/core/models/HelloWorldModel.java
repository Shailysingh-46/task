/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.aem.training.site.core.models;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.adobe.cq.export.json.ExporterConstants;
import com.aem.training.site.core.services.SimpleService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.apache.sling.settings.SlingSettingsService;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import java.util.*;

@Model(adaptables = {SlingHttpServletRequest.class,Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = HelloWorldModel.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)

public class HelloWorldModel {

    protected static final String RESOURCE_TYPE = "training/components/helloworld";

    @ValueMapValue(name="text")
    private String title;

    @OSGiService
    private SlingSettingsService settings;

    @OSGiService
    private SimpleService simpleService;

    @Inject
    SlingHttpServletRequest request;

    @SlingObject
    private Resource currentResource;

    @ScriptVariable
    private ValueMap pageProperties;

    private String pageTitle;

    private List<Bike> bikeList = Collections.emptyList();

    @PostConstruct
    protected void init() {

        ResourceResolver resolver = request.getResourceResolver();
        Resource resource = resolver.getResource("/var/commerce/products/we-retail/eq/biking");
        Iterable<Resource> childResources = resource.getChildren();

        ValueMap vm = resource.getValueMap();
        String price = vm.get("price",String.class);




        bikeList = simpleService.getBikeList();
//        String path = currentResource.getPath();
//        String name = currentResource.getName();
//        ValueMap vm = pageProperties;

//        Iterable<Resource> childrenRes = currentResource.getChildren();

//        for (Resource res: childrenRes) {
//            String resName = res.getName();
//            String resPath = res.getPath();
//        }
//        ResourceResolver resolver = request.getResourceResolver();
//        Resource res = resolver.getResource("/content/training/us/en");
//        PageManager pm = resolver.adaptTo(PageManager.class);
//        Page page = pm.getContainingPage(res);
//        ValueMap vm =  page.getProperties();
//        String pageTitle = vm.get("pageTitle",String.class);
//        title = title.toUpperCase();
//        Set<String> runMode = settings.getRunModes();
//        System.out.println("run mode");
    }

    public List<Bike> getBikeList() {
        return bikeList;
    }

    public String getTitle() {
        return title;
    }

    public String getSample(){
        return "sample content";
    }
}
