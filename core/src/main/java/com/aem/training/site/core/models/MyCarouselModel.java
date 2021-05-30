package com.aem.training.site.core.models;

import com.adobe.cq.export.json.ExporterConstants;
import com.aem.training.site.core.services.impl.SimpleServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Model(adaptables = {SlingHttpServletRequest.class,Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = MyCarouselModel.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)

public class MyCarouselModel {

    protected static final String RESOURCE_TYPE = "training/components/my_carousel";

    @ChildResource
    private Resource slide;

    private List<MyCarouselItem> myCarouselSlides= new ArrayList<>();


    @PostConstruct
    protected void init(){
        if (Objects.nonNull(slide)){

            for (Resource res:
                    slide.getChildren()) {

                MyCarouselItem item = new MyCarouselItem();
                ValueMap vm = res.getValueMap();

                item.setImage(vm.containsKey("image") ? vm.get("image", String.class) : StringUtils.EMPTY);
                item.setHeadTitle(vm.containsKey("headtitle") ? vm.get("headtitle", String.class) : StringUtils.EMPTY);
                item.setPara(vm.containsKey("para") ? vm.get("para", String.class) : StringUtils.EMPTY);

                myCarouselSlides.add(item);

            }
        }
    }

    public List<MyCarouselItem> getMyCarouselSlides() {
        return myCarouselSlides;
    }
}
