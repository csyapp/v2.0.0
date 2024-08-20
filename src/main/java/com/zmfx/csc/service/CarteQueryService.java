package com.zmfx.csc.service;

import com.zmfx.csc.domain.*; // for static metamodels
import com.zmfx.csc.domain.Carte;
import com.zmfx.csc.repository.CarteRepository;
import com.zmfx.csc.service.criteria.CarteCriteria;
import com.zmfx.csc.service.dto.CarteDTO;
import com.zmfx.csc.service.mapper.CarteMapper;
import jakarta.persistence.criteria.JoinType;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Carte} entities in the database.
 * The main input is a {@link CarteCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link CarteDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CarteQueryService extends QueryService<Carte> {

    private static final Logger log = LoggerFactory.getLogger(CarteQueryService.class);

    private final CarteRepository carteRepository;

    private final PDFService pdfService;

    private final CarteMapper carteMapper;

    private final MailService mailService;

    public CarteQueryService(CarteRepository carteRepository, PDFService pdfService, CarteMapper carteMapper, MailService mailService) {
        this.carteRepository = carteRepository;
        this.pdfService = pdfService;
        this.carteMapper = carteMapper;
        this.mailService = mailService;
    }

    /**
     * Return a {@link Page} of {@link CarteDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CarteDTO> findByCriteria(CarteCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Carte> specification = createSpecification(criteria);
        return carteRepository.findAll(specification, page).map(carteMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CarteCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Carte> specification = createSpecification(criteria);
        return carteRepository.count(specification);
    }

    /**
     * Function to convert {@link CarteCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Carte> createSpecification(CarteCriteria criteria) {
        Specification<Carte> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getMatricule() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMatricule(), Carte_.matricule));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Carte_.name));
            }
            if (criteria.getSurname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSurname(), Carte_.surname));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Carte_.email));
            }
            if (criteria.getVilleResidence() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVilleResidence(), Carte_.villeResidence));
            }
            if (criteria.getBirthdate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBirthdate(), Carte_.birthdate));
            }
            if (criteria.getTelephone1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelephone1(), Carte_.telephone1));
            }
            if (criteria.getTelephone2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelephone2(), Carte_.telephone2));
            }
            if (criteria.getIsImprime() != null) {
                specification = specification.and(buildSpecification(criteria.getIsImprime(), Carte_.isImprime));
            }
            if (criteria.getPictureExtension() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPictureExtension(), Carte_.pictureExtension));
            }
            if (criteria.getDateDelivrance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateDelivrance(), Carte_.dateDelivrance));
            }
            if (criteria.getDateExpiration() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateExpiration(), Carte_.dateExpiration));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), Carte_.createdBy));
            }
            if (criteria.getCreatedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), Carte_.createdDate));
            }
            if (criteria.getLastModifiedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastModifiedBy(), Carte_.lastModifiedBy));
            }
            if (criteria.getLastModifiedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastModifiedDate(), Carte_.lastModifiedDate));
            }
            if (criteria.getTemplateId() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getTemplateId(), root -> root.join(Carte_.template, JoinType.LEFT).get(Template_.libelle))
                );
            }
        }
        return specification;
    }

    //@Transactional(readOnly = true)
    public File getPdfArray(String matricule) {
        log.debug("Find Carte eager Loading by criteria : {}", matricule);
        Carte carte = carteRepository.findByMatricule(matricule);
        Float fontSize = 8f;
        log.debug(">>>>>>>>>>>>>>>>> entering Pdf Generation");
        return pdfService.combinePDFPagesToByteArray(fontSize, carte);
    }

    public void getNotifiedCard(CarteDTO carteDTO) {
        log.debug(">>>>>>>>>>>>>>>>> Envoie du mail au supporter");
        mailService.sendCardPrintedMail(carteDTO);
    }

    //@Transactional(readOnly = true)
    public Page<CarteDTO> findByCriteriaSearchKey(Pageable page, String searchKey) {
        final Specification<Carte> specification = createSpecificationBySearchKey(searchKey);
        return carteRepository.findAll(specification, page).map(carteMapper::toDto);
    }

    public Specification<Carte> createSpecificationBySearchKey(String searchKey) {
        return (root, query, criteriaBuilder) -> {
            jakarta.persistence.criteria.Predicate namePredicate = criteriaBuilder.like(root.get("name"), "%" + searchKey + "%");
            jakarta.persistence.criteria.Predicate emailPredicate = criteriaBuilder.like(root.get("email"), "%" + searchKey + "%");
            jakarta.persistence.criteria.Predicate matriculePredicate = criteriaBuilder.like(root.get("matricule"), "%" + searchKey + "%");
            jakarta.persistence.criteria.Predicate surnamePredicate = criteriaBuilder.like(root.get("surname"), "%" + searchKey + "%");
            jakarta.persistence.criteria.Predicate createdByPredicate = criteriaBuilder.like(root.get("createdBy"), "%" + searchKey + "%");
            jakarta.persistence.criteria.Predicate lastModifiedByPredicate = criteriaBuilder.like(
                root.get("lastModifiedBy"),
                "%" + searchKey + "%"
            );
            jakarta.persistence.criteria.Predicate villeResidencePredicate = criteriaBuilder.like(
                root.get("villeResidence"),
                "%" + searchKey + "%"
            );
            jakarta.persistence.criteria.Predicate telephone1Predicate = criteriaBuilder.like(
                root.get("telephone1"),
                "%" + searchKey + "%"
            );
            jakarta.persistence.criteria.Predicate telephone2Predicate = criteriaBuilder.like(
                root.get("telephone2"),
                "%" + searchKey + "%"
            );
            jakarta.persistence.criteria.Predicate pictureExtensionPredicate = criteriaBuilder.like(
                root.get("pictureExtension"),
                "%" + searchKey + "%"
            );

            if (
                namePredicate != null &&
                emailPredicate != null &&
                matriculePredicate != null &&
                surnamePredicate != null &&
                villeResidencePredicate != null &&
                telephone1Predicate != null &&
                telephone2Predicate != null &&
                pictureExtensionPredicate != null &&
                createdByPredicate != null &&
                lastModifiedByPredicate != null
            ) {
                return criteriaBuilder.or(
                    namePredicate,
                    emailPredicate,
                    matriculePredicate,
                    surnamePredicate,
                    villeResidencePredicate,
                    telephone1Predicate,
                    telephone2Predicate,
                    pictureExtensionPredicate,
                    createdByPredicate,
                    lastModifiedByPredicate
                );
            } else if (namePredicate != null) {
                return namePredicate;
            } else if (emailPredicate != null) {
                return emailPredicate;
            } else if (matriculePredicate != null) {
                return matriculePredicate;
            } else if (surnamePredicate != null) {
                return surnamePredicate;
            } else if (villeResidencePredicate != null) {
                return villeResidencePredicate;
            } else if (telephone1Predicate != null) {
                return telephone1Predicate;
            } else if (telephone2Predicate != null) {
                return telephone2Predicate;
            } else if (pictureExtensionPredicate != null) {
                return pictureExtensionPredicate;
            } else if (createdByPredicate != null) {
                return createdByPredicate;
            } else {
                return lastModifiedByPredicate;
            }
        };
    }
}
