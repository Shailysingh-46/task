package com.aem.training.site.core.services.impl;

import com.aem.training.site.core.models.Bike;
import com.aem.training.site.core.models.MyProduct;
import com.aem.training.site.core.services.SimpleService;
import com.aem.training.site.core.services.SimpleServiceConfiguration;
import com.aem.training.site.core.services.SimpleServiceDemo;
import com.aem.training.site.core.services.SimpleServiceDemoConfiguration;
import com.mongodb.QueryBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.*;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.aem.training.site.core.services.impl.SimpleServiceImpl.SAMPLESYSTEMUSER;

@Component(
        service = { SimpleServiceDemo.class },
        immediate = true,
        property = { "service.description=" + "A Simple service demo" })
@Designate(ocd = SimpleServiceDemoConfiguration.class)
public class SimpleServiceDemoImpl implements SimpleServiceDemo {
    private static final Logger log = LoggerFactory.getLogger(SimpleServiceDemoImpl.class);
    String path = StringUtils.EMPTY;

    @Reference
    ResourceResolverFactory resourceResolverFactory;

    @Reference
    private QueryBuilder queryBuilder;
    @Activate
    @Modified
    protected void activateOrModified(SimpleServiceDemoConfiguration config){
      path= config.service_path();
      System.out.println(path);
    }
    @Override
    public List<MyProduct> getMyProductList(){
        ResourceResolver resourceResolver = getResourceResolver(resourceResolverFactory,SAMPLESYSTEMUSER);
        List<MyProduct> productsList = new ArrayList<>();
        if (Objects.nonNull(resourceResolver)) {
            Resource parentResource = resourceResolver.getResource(path);
            if (Objects.nonNull(parentResource)){

                for (Resource res: parentResource.getChildren()) {
                    ValueMap vm=res.getValueMap();
                    MyProduct myProduct =new MyProduct();
                    myProduct.setTitle(vm.get("jcr:title",String.class));
                    myProduct.setPrice(vm.get("price", String.class));
                    myProduct.setDescription(vm.get("summary", String.class));
                    myProduct.setRating(vm.get("rating", String.class));
                    myProduct.setReviews(vm.get("reviews", String.class));

                    productsList.add(myProduct);
                }



            }
        }
        return productsList;

    }

    private ResourceResolver getResourceResolver(final ResourceResolverFactory resourceResolverFactory,
                                                 final String subService) {
        ResourceResolver resourceResolver = null;
        if (null != resourceResolverFactory && null != subService) {
            try {
                final Map<String, Object> authInfo = new HashMap<>();
                authInfo.put(ResourceResolverFactory.SUBSERVICE, subService);
                resourceResolver = resourceResolverFactory.getServiceResourceResolver(authInfo);
            } catch (final LoginException loginException) {
                log.error("exception occured");
            }
        }
        return resourceResolver;
    }



 //   @Deactivate
   // protected void deactivate(){}
}
