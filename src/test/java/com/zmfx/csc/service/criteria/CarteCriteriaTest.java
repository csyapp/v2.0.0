package com.zmfx.csc.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class CarteCriteriaTest {

    @Test
    void newCarteCriteriaHasAllFiltersNullTest() {
        var carteCriteria = new CarteCriteria();
        assertThat(carteCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void carteCriteriaFluentMethodsCreatesFiltersTest() {
        var carteCriteria = new CarteCriteria();

        setAllFilters(carteCriteria);

        assertThat(carteCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void carteCriteriaCopyCreatesNullFilterTest() {
        var carteCriteria = new CarteCriteria();
        var copy = carteCriteria.copy();

        assertThat(carteCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(carteCriteria)
        );
    }

    @Test
    void carteCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var carteCriteria = new CarteCriteria();
        setAllFilters(carteCriteria);

        var copy = carteCriteria.copy();

        assertThat(carteCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(carteCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var carteCriteria = new CarteCriteria();

        assertThat(carteCriteria).hasToString("CarteCriteria{}");
    }

    private static void setAllFilters(CarteCriteria carteCriteria) {
        carteCriteria.matricule();
        carteCriteria.name();
        carteCriteria.surname();
        carteCriteria.email();
        carteCriteria.villeResidence();
        carteCriteria.birthdate();
        carteCriteria.telephone1();
        carteCriteria.telephone2();
        carteCriteria.isImprime();
        carteCriteria.pictureExtension();
        carteCriteria.dateDelivrance();
        carteCriteria.dateExpiration();
        carteCriteria.createdBy();
        carteCriteria.createdDate();
        carteCriteria.lastModifiedBy();
        carteCriteria.lastModifiedDate();
        carteCriteria.templateId();
        carteCriteria.distinct();
    }

    private static Condition<CarteCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getMatricule()) &&
                condition.apply(criteria.getName()) &&
                condition.apply(criteria.getSurname()) &&
                condition.apply(criteria.getEmail()) &&
                condition.apply(criteria.getVilleResidence()) &&
                condition.apply(criteria.getBirthdate()) &&
                condition.apply(criteria.getTelephone1()) &&
                condition.apply(criteria.getTelephone2()) &&
                condition.apply(criteria.getIsImprime()) &&
                condition.apply(criteria.getPictureExtension()) &&
                condition.apply(criteria.getDateDelivrance()) &&
                condition.apply(criteria.getDateExpiration()) &&
                condition.apply(criteria.getCreatedBy()) &&
                condition.apply(criteria.getCreatedDate()) &&
                condition.apply(criteria.getLastModifiedBy()) &&
                condition.apply(criteria.getLastModifiedDate()) &&
                condition.apply(criteria.getTemplateId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<CarteCriteria> copyFiltersAre(CarteCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getMatricule(), copy.getMatricule()) &&
                condition.apply(criteria.getName(), copy.getName()) &&
                condition.apply(criteria.getSurname(), copy.getSurname()) &&
                condition.apply(criteria.getEmail(), copy.getEmail()) &&
                condition.apply(criteria.getVilleResidence(), copy.getVilleResidence()) &&
                condition.apply(criteria.getBirthdate(), copy.getBirthdate()) &&
                condition.apply(criteria.getTelephone1(), copy.getTelephone1()) &&
                condition.apply(criteria.getTelephone2(), copy.getTelephone2()) &&
                condition.apply(criteria.getIsImprime(), copy.getIsImprime()) &&
                condition.apply(criteria.getPictureExtension(), copy.getPictureExtension()) &&
                condition.apply(criteria.getDateDelivrance(), copy.getDateDelivrance()) &&
                condition.apply(criteria.getDateExpiration(), copy.getDateExpiration()) &&
                condition.apply(criteria.getCreatedBy(), copy.getCreatedBy()) &&
                condition.apply(criteria.getCreatedDate(), copy.getCreatedDate()) &&
                condition.apply(criteria.getLastModifiedBy(), copy.getLastModifiedBy()) &&
                condition.apply(criteria.getLastModifiedDate(), copy.getLastModifiedDate()) &&
                condition.apply(criteria.getTemplateId(), copy.getTemplateId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
