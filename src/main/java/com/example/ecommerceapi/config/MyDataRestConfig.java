package com.example.ecommerceapi.config;

import com.example.ecommerceapi.entity.Country;
import com.example.ecommerceapi.entity.Product;
import com.example.ecommerceapi.entity.ProductCategory;
import com.example.ecommerceapi.entity.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MyDataRestConfig implements RepositoryRestConfigurer {

    EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.POST};

        disableHttpMethods(config.getExposureConfiguration()
                .forDomainType(Product.class), theUnsupportedActions);
        disableHttpMethods(config.getExposureConfiguration()
                .forDomainType(ProductCategory.class), theUnsupportedActions);
        disableHttpMethods(config.getExposureConfiguration()
                .forDomainType(Country.class), theUnsupportedActions);
        disableHttpMethods(config.getExposureConfiguration()
                .forDomainType(State.class), theUnsupportedActions);

        exposedIds(config);

    }

    private static void disableHttpMethods(ExposureConfigurer config, HttpMethod[] theUnsupportedActions) {
        //disable HTTP Method Product Category for above unsupported actions
        config
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

    private void exposedIds(RepositoryRestConfiguration config) {
        //expose entity ids

        //-get a list of all entity classes from the entity manager
        Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();

        // create an array of the entity types
        List<Class<?>> entityClass = new ArrayList<>();

        //get the entity types for the entities
        for (EntityType<?> entityType: entityTypes){
            entityClass.add(entityType.getJavaType());
        }

        Class<?>[] domainType = entityClass.toArray(new Class<?>[0]);
        config.exposeIdsFor(domainType);
    }

}
