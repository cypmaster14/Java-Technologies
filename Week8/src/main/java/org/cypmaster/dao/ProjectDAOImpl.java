package org.cypmaster.dao;

import org.cypmaster.entities.Project;
import org.cypmaster.utils.RangeFilter;
import org.cypmaster.utils.ValueFilter;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Ciprian at 12/9/2017
 */
@Stateless
public class ProjectDAOImpl implements ProjectDAO {

    @PersistenceContext(unitName = "Week7")
    private EntityManager entityManager;

    private final static String PROJECT_FILTER_NAME = "FILTER_BY_NAME";
    private final static String PROJECT_FILTER_DESCRIPTION = "FILTER_BY_DESCRIPTION";
    private final static String PROJECT_FILTER_CAPACITY = "FILTER_BY_CAPACITY";


    @Override
    public List<Project> search(Map<String, ValueFilter> valueFilters, Map<String, RangeFilter> rangeFiltersSelected) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Project> query = builder.createQuery(Project.class);
        Root<Project> projectRoot = query.from(Project.class);
        query.select(projectRoot);
        List<Predicate> predicates = new ArrayList<>();

        if (valueFilters.containsKey(PROJECT_FILTER_NAME)) {
            String name = valueFilters.get(PROJECT_FILTER_NAME).getValue();
            predicates.add(getPredicateForFilteringByName(builder, projectRoot, name));
        }

        if (valueFilters.containsKey(PROJECT_FILTER_DESCRIPTION)) {
            String name = valueFilters.get(PROJECT_FILTER_DESCRIPTION).getValue();
            predicates.add(getPredicateForFilteringByDescription(builder, projectRoot, name));
        }

        if (rangeFiltersSelected.containsKey(PROJECT_FILTER_CAPACITY)) {
            int rangeBegin = rangeFiltersSelected.get(PROJECT_FILTER_CAPACITY).getBegin();
            int rangeEnd = rangeFiltersSelected.get(PROJECT_FILTER_CAPACITY).getEnd();
            predicates.add(getPredicateForFilteringByCapacityRange(builder, projectRoot, rangeBegin, rangeEnd));
        }

        if (predicates.size() > 0) {
            query.where(predicates.toArray(new Predicate[0]));
        }

        List<Project> projects = entityManager.createQuery(query).getResultList();
        return projects;
    }

    private Predicate getPredicateForFilteringByName(CriteriaBuilder builder, Root<Project> projectRoot, String name) {
        String nameLikePattern = String.format("%%%s%%", name.trim().toLowerCase());//to escape % use %%
        Expression<String> filterByNameKeyExpression = projectRoot.get("name").as(String.class);
        filterByNameKeyExpression = builder.lower(filterByNameKeyExpression);
        Predicate predicate = builder.like(filterByNameKeyExpression, nameLikePattern);
        return predicate;
    }

    private Predicate getPredicateForFilteringByDescription(CriteriaBuilder builder, Root<Project> projectRoot, String description) {
        String descriptionLikePattern = String.format("%%%s%%", description.trim().toLowerCase());//to escape % use %%
        Expression<String> filterByDescriptionKeyExpression = projectRoot.get("description").as(String.class);
        filterByDescriptionKeyExpression = builder.lower(filterByDescriptionKeyExpression);
        Predicate predicate = builder.like(filterByDescriptionKeyExpression, descriptionLikePattern);
        return predicate;
    }

    private Predicate getPredicateForFilteringByCapacityRange(CriteriaBuilder builder, Root<Project> projectRoot, int rangeBegin, int rangeEnd) {
        Expression<Integer> filterByCapacityKeyExpression = projectRoot.get("capacity").as(Integer.class);
        Predicate rangePredicate = builder.between(filterByCapacityKeyExpression, rangeBegin, rangeEnd);
        return rangePredicate;
    }

    @Override
    public Integer findNumberOfProjects() {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        query.select(builder.count(query.from(Project.class)));
        return entityManager.createQuery(query).getSingleResult().intValue();
    }


    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}