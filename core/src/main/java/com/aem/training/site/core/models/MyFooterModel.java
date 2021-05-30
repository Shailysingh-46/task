package com.aem.training.site.core.models;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.adobe.cq.export.json.ExporterConstants;
import com.aem.training.site.core.services.SimpleService;
import org.apache.commons.lang3.StringUtils;
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

@Model(adaptables = {SlingHttpServletRequest.class,Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = MyFooterModel.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)


public class MyFooterModel {
    protected static final String RESOURCE_TYPE = "training/components/my_footer";

    @ChildResource
    private Resource social_media;


    private List<MyFooterItem> myFooterSocial= new ArrayList<>();


    @PostConstruct
    protected void init(){
        if (Objects.nonNull(social_media)){

            for (Resource res:
                    social_media.getChildren()) {

                MyFooterItem item = new MyFooterItem();
                ValueMap vm = res.getValueMap();

                item.setImage(vm.containsKey("image") ? vm.get("image", String.class) : StringUtils.EMPTY);
                item.setLink(vm.containsKey("link") ? vm.get("link", String.class) : StringUtils.EMPTY);
                item.setTitle(vm.containsKey("title") ? vm.get("title", String.class) : StringUtils.EMPTY);
                myFooterSocial.add(item);

            }
        }
    }

    public List<MyFooterItem> getMyFooterSocial() {
        return myFooterSocial;
    }
}


