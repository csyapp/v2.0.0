package com.zmfx.csc.service;

import com.zmfx.csc.domain.*; // for static metamodels
import com.zmfx.csc.domain.Template;
import com.zmfx.csc.repository.TemplateRepository;
import com.zmfx.csc.service.criteria.TemplateCriteria;
import com.zmfx.csc.service.dto.TemplateDTO;
import com.zmfx.csc.service.mapper.TemplateMapper;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Template} entities in the database.
 * The main input is a {@link TemplateCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link TemplateDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TemplateQueryService extends QueryService<Template> {

    private static final Logger log = LoggerFactory.getLogger(TemplateQueryService.class);

    private final TemplateRepository templateRepository;

    private final TemplateMapper templateMapper;

    public TemplateQueryService(TemplateRepository templateRepository, TemplateMapper templateMapper) {
        this.templateRepository = templateRepository;
        this.templateMapper = templateMapper;
    }

    /**
     * Return a {@link Page} of {@link TemplateDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TemplateDTO> findByCriteria(TemplateCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Template> specification = createSpecification(criteria);
        return templateRepository.findAll(specification, page).map(templateMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TemplateCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Template> specification = createSpecification(criteria);
        return templateRepository.count(specification);
    }

    /**
     * Function to convert {@link TemplateCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Template> createSpecification(TemplateCriteria criteria) {
        Specification<Template> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), Template_.libelle));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Template_.description));
            }
            if (criteria.getImageHeigth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getImageHeigth(), Template_.imageHeigth));
            }
            if (criteria.getImageWidth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getImageWidth(), Template_.imageWidth));
            }
            if (criteria.getImageX() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getImageX(), Template_.imageX));
            }
            if (criteria.getImageY() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getImageY(), Template_.imageY));
            }
            if (criteria.getMatriculeX() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMatriculeX(), Template_.matriculeX));
            }
            if (criteria.getMatriculeY() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMatriculeY(), Template_.matriculeY));
            }
            if (criteria.getMembreX() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMembreX(), Template_.membreX));
            }
            if (criteria.getMembreY() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMembreY(), Template_.membreY));
            }
            if (criteria.getNomX() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNomX(), Template_.nomX));
            }
            if (criteria.getNomY() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNomY(), Template_.nomY));
            }
            if (criteria.getPrenomX() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrenomX(), Template_.prenomX));
            }
            if (criteria.getPrenomY() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrenomY(), Template_.prenomY));
            }
            if (criteria.getQrHeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQrHeight(), Template_.qrHeight));
            }
            if (criteria.getQrWidth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQrWidth(), Template_.qrWidth));
            }
            if (criteria.getQrX() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQrX(), Template_.qrX));
            }
            if (criteria.getQrY() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getQrY(), Template_.qrY));
            }
            if (criteria.getDateDelivranceX() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateDelivranceX(), Template_.dateDelivranceX));
            }
            if (criteria.getDateDelivranceY() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateDelivranceY(), Template_.dateDelivranceY));
            }
            if (criteria.getDateExpirationX() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateExpirationX(), Template_.dateExpirationX));
            }
            if (criteria.getDateExpirationY() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateExpirationY(), Template_.dateExpirationY));
            }
            if (criteria.getSampleLargeur() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSampleLargeur(), Template_.sampleLargeur));
            }
            if (criteria.getSampleLongueur() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSampleLongueur(), Template_.sampleLongueur));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), Template_.createdBy));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), Template_.createdDate));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), Template_.lastModifiedBy));
            }
            if (criteria.getLastModifiedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedDate(), Template_.lastModifiedDate));
            }
        }
        return specification;
    }

    @Transactional(readOnly = true)
    public Page<TemplateDTO> findByCriteriaSearchKey(TemplateCriteria criteria, Pageable page, String searchKey) {
        final Specification<Template> specification = createSpecificationBySearchKey(searchKey).or(
            createSpecificationBySearchKey(searchKey)
        );
        return templateRepository.findAll(specification, page).map(templateMapper::toDto);
    }

    public Specification<Template> createSpecificationBySearchKey(String searchKey) {
        return (root, query, builder) -> {
            jakarta.persistence.criteria.Predicate lastModifiedByPredicate = builder.like(
                root.get("lastModifiedBy"),
                "%" + searchKey + "%"
            );
            jakarta.persistence.criteria.Predicate createdByPredicate = builder.like(root.get("createdBy"), "%" + searchKey + "%");
            jakarta.persistence.criteria.Predicate descriptionByPredicate = builder.like(root.get("description"), "%" + searchKey + "%");
            jakarta.persistence.criteria.Predicate libelleByPredicate = builder.like(root.get("libelle"), "%" + searchKey + "%");
            return builder.or(lastModifiedByPredicate, createdByPredicate, descriptionByPredicate, libelleByPredicate);
        };
    }
    //    public Specification<Template> createSpecificationBySearchKeyWithoutForeignKey(String searchKey) {
    //        return (root, query, criteriaBuilder) -> {
    //            jakarta.persistence.criteria.Predicate lastModifiedByPredicate = criteriaBuilder.like(root.get("lastModifiedBy"),"%" + searchKey + "%");
    //            jakarta.persistence.criteria.Predicate createdByPredicate = criteriaBuilder.like(root.get("createdBy"), "%" + searchKey + "%");
    //
    //            return criteriaBuilder.or(lastModifiedByPredicate, createdByPredicate);
    //        };
    //    }
}
