package com.zmfx.csc.repository;

import com.zmfx.csc.domain.Template;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Template entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TemplateRepository extends JpaRepository<Template, String>, JpaSpecificationExecutor<Template> {}
