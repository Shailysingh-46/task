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

@Model(adaptables = {SlingHttpServletRequest.class,Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = MyHeaderModel.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)

public class MyHeaderModel {
    protected static final String RESOURCE_TYPE = "training/components/my_header";

    @ChildResource
    private Resource nav;


    private List<MyHeaderItem> myHeaderNav= new ArrayList<>();


    @PostConstruct
    protected void init(){
        if (Objects.nonNull(nav)){

            for (Resource res:
                    nav.getChildren()) {

                MyHeaderItem item = new MyHeaderItem();
                ValueMap vm = res.getValueMap();

              // item.setLogo(vm.containsKey("logo") ? vm.get("logo", String.class) : StringUtils.EMPTY);
                item.setTitle(vm.containsKey("title") ? vm.get("title", String.class) : StringUtils.EMPTY);

                myHeaderNav.add(item);

            }
        }
    }

    public List<MyHeaderItem> getMyHeaderNav() {
        return myHeaderNav;
    }
}
