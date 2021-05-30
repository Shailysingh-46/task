package com.aem.training.site.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class,defaultInjectionStrategy =  DefaultInjectionStrategy.OPTIONAL)
public class Hello2Model {

    @ValueMapValue
    private String title;

    @PostConstruct
    protected void init() {
        title = title.toUpperCase();
    }

    public String getTitle() {
        return title;
    }
}
