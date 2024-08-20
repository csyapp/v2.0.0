package com.zmfx.csc.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class TemplateCriteriaTest {

    @Test
    void newTemplateCriteriaHasAllFiltersNullTest() {
        var templateCriteria = new TemplateCriteria();
        assertThat(templateCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void templateCriteriaFluentMethodsCreatesFiltersTest() {
        var templateCriteria = new TemplateCriteria();

        setAllFilters(templateCriteria);

        assertThat(templateCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void templateCriteriaCopyCreatesNullFilterTest() {
        var templateCriteria = new TemplateCriteria();
        var copy = templateCriteria.copy();

        assertThat(templateCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(templateCriteria)
        );
    }

    @Test
    void templateCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var templateCriteria = new TemplateCriteria();
        setAllFilters(templateCriteria);

        var copy = templateCriteria.copy();

        assertThat(templateCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(templateCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var templateCriteria = new TemplateCriteria();

        assertThat(templateCriteria).hasToString("TemplateCriteria{}");
    }

    private static void setAllFilters(TemplateCriteria templateCriteria) {
        templateCriteria.libelle();
        templateCriteria.description();
        templateCriteria.imageHeigth();
        templateCriteria.imageWidth();
        templateCriteria.imageX();
        templateCriteria.imageY();
        templateCriteria.matriculeX();
        templateCriteria.matriculeY();
        templateCriteria.membreX();
        templateCriteria.membreY();
        templateCriteria.nomX();
        templateCriteria.nomY();
        templateCriteria.prenomX();
        templateCriteria.prenomY();
        templateCriteria.qrHeight();
        templateCriteria.qrWidth();
        templateCriteria.qrX();
        templateCriteria.qrY();
        templateCriteria.dateDelivranceX();
        templateCriteria.dateDelivranceY();
        templateCriteria.dateExpirationX();
        templateCriteria.dateExpirationY();
        templateCriteria.sampleLargeur();
        templateCriteria.sampleLongueur();
        templateCriteria.createdBy();
        templateCriteria.createdDate();
        templateCriteria.lastModifiedBy();
        templateCriteria.lastModifiedDate();
        templateCriteria.distinct();
    }

    private static Condition<TemplateCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getLibelle()) &&
                condition.apply(criteria.getDescription()) &&
                condition.apply(criteria.getImageHeigth()) &&
                condition.apply(criteria.getImageWidth()) &&
                condition.apply(criteria.getImageX()) &&
                condition.apply(criteria.getImageY()) &&
                condition.apply(criteria.getMatriculeX()) &&
                condition.apply(criteria.getMatriculeY()) &&
                condition.apply(criteria.getMembreX()) &&
                condition.apply(criteria.getMembreY()) &&
                condition.apply(criteria.getNomX()) &&
                condition.apply(criteria.getNomY()) &&
                condition.apply(criteria.getPrenomX()) &&
                condition.apply(criteria.getPrenomY()) &&
                condition.apply(criteria.getQrHeight()) &&
                condition.apply(criteria.getQrWidth()) &&
                condition.apply(criteria.getQrX()) &&
                condition.apply(criteria.getQrY()) &&
                condition.apply(criteria.getDateDelivranceX()) &&
                condition.apply(criteria.getDateDelivranceY()) &&
                condition.apply(criteria.getDateExpirationX()) &&
                condition.apply(criteria.getDateExpirationY()) &&
                condition.apply(criteria.getSampleLargeur()) &&
                condition.apply(criteria.getSampleLongueur()) &&
                condition.apply(criteria.getCreatedBy()) &&
                condition.apply(criteria.getCreatedDate()) &&
                condition.apply(criteria.getLastModifiedBy()) &&
                condition.apply(criteria.getLastModifiedDate()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<TemplateCriteria> copyFiltersAre(TemplateCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getLibelle(), copy.getLibelle()) &&
                condition.apply(criteria.getDescription(), copy.getDescription()) &&
                condition.apply(criteria.getImageHeigth(), copy.getImageHeigth()) &&
                condition.apply(criteria.getImageWidth(), copy.getImageWidth()) &&
                condition.apply(criteria.getImageX(), copy.getImageX()) &&
                condition.apply(criteria.getImageY(), copy.getImageY()) &&
                condition.apply(criteria.getMatriculeX(), copy.getMatriculeX()) &&
                condition.apply(criteria.getMatriculeY(), copy.getMatriculeY()) &&
                condition.apply(criteria.getMembreX(), copy.getMembreX()) &&
                condition.apply(criteria.getMembreY(), copy.getMembreY()) &&
                condition.apply(criteria.getNomX(), copy.getNomX()) &&
                condition.apply(criteria.getNomY(), copy.getNomY()) &&
                condition.apply(criteria.getPrenomX(), copy.getPrenomX()) &&
                condition.apply(criteria.getPrenomY(), copy.getPrenomY()) &&
                condition.apply(criteria.getQrHeight(), copy.getQrHeight()) &&
                condition.apply(criteria.getQrWidth(), copy.getQrWidth()) &&
                condition.apply(criteria.getQrX(), copy.getQrX()) &&
                condition.apply(criteria.getQrY(), copy.getQrY()) &&
                condition.apply(criteria.getDateDelivranceX(), copy.getDateDelivranceX()) &&
                condition.apply(criteria.getDateDelivranceY(), copy.getDateDelivranceY()) &&
                condition.apply(criteria.getDateExpirationX(), copy.getDateExpirationX()) &&
                condition.apply(criteria.getDateExpirationY(), copy.getDateExpirationY()) &&
                condition.apply(criteria.getSampleLargeur(), copy.getSampleLargeur()) &&
                condition.apply(criteria.getSampleLongueur(), copy.getSampleLongueur()) &&
                condition.apply(criteria.getCreatedBy(), copy.getCreatedBy()) &&
                condition.apply(criteria.getCreatedDate(), copy.getCreatedDate()) &&
                condition.apply(criteria.getLastModifiedBy(), copy.getLastModifiedBy()) &&
                condition.apply(criteria.getLastModifiedDate(), copy.getLastModifiedDate()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
