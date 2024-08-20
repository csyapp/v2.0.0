package com.zmfx.csc.repository;

import com.zmfx.csc.domain.Carte;
import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Carte entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CarteRepository extends JpaRepository<Carte, String>, JpaSpecificationExecutor<Carte> {
    Integer countCartesByCreatedDateBetween(Instant createdDate, Instant createdDate2);

    @Query("SELECT e FROM Carte e LEFT JOIN FETCH e.template WHERE e.matricule = ?1")
    Carte findByIdWithAssociations(String matricule);

    Carte findByMatricule(String matricule);

    @Query(
        "SELECT " +
        "e.lastModifiedDate ,count(*) " +
        "FROM Carte e " +
        "WHERE TO_CHAR(e.lastModifiedDate, 'YYYY') = ?1  AND TO_CHAR(e.lastModifiedDate, 'MM') = ?2  AND e.isImprime = true " +
        "GROUP BY e.lastModifiedDate "
    )
    List<Object[]> getReportingJourTrue(String annee, String mois);

    @Query(
        "SELECT " +
        "e.lastModifiedDate ,count(*) " +
        "FROM Carte e " +
        "WHERE TO_CHAR(e.lastModifiedDate, 'YYYY') = ?1  AND TO_CHAR(e.lastModifiedDate, 'MM') = ?2  AND e.isImprime = false " +
        "GROUP BY e.lastModifiedDate "
    )
    List<Object[]> getReportingJourFalse(String annee, String mois);

    @Query(
        "SELECT " +
        "TO_CHAR(e.lastModifiedDate, 'MM') ,count(*)  " +
        "FROM Carte e " +
        "WHERE TO_CHAR(e.lastModifiedDate, 'YYYY') = ?1  AND e.isImprime = false " +
        "GROUP BY TO_CHAR(e.lastModifiedDate, 'MM') "
    )
    List<Object[]> getReportingMoisFalse(String annee);

    @Query(
        "SELECT " +
        "TO_CHAR(e.lastModifiedDate, 'MM') ,count(*) " +
        "FROM Carte e " +
        "WHERE TO_CHAR(e.lastModifiedDate, 'YYYY') = ?1  AND e.isImprime = true " +
        "GROUP BY TO_CHAR(e.lastModifiedDate, 'MM') "
    )
    List<Object[]> getReportingMoisTrue(String annee);

    @Query(
        "SELECT " +
        "TO_CHAR(e.lastModifiedDate, 'YYYY') ,count(*)  " +
        "FROM Carte e " +
        "WHERE TO_CHAR(e.lastModifiedDate, 'YYYY') >= ?1 AND TO_CHAR(e.lastModifiedDate, 'YYYY') <= ?2  AND e.isImprime = false " +
        "GROUP BY TO_CHAR(e.lastModifiedDate, 'YYYY') "
    )
    List<Object[]> getReportingAnneeFalse(String anneeInf, String anneeSup);

    @Query(
        "SELECT " +
        "TO_CHAR(e.lastModifiedDate, 'YYYY') ,count(*)  " +
        "FROM Carte e " +
        "WHERE TO_CHAR(e.lastModifiedDate, 'YYYY') >= ?1 AND TO_CHAR(e.lastModifiedDate, 'YYYY') <= ?2  AND e.isImprime = true " +
        "GROUP BY TO_CHAR(e.lastModifiedDate, 'YYYY') "
    )
    List<Object[]> getReportingAnneeTrue(String anneeInf, String anneeSup);
}
