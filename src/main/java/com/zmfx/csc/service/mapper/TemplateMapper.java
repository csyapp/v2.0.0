package com.zmfx.csc.service.mapper;

import com.zmfx.csc.domain.Template;
import com.zmfx.csc.service.dto.TemplateDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Template} and its DTO {@link TemplateDTO}.
 */
@Mapper(componentModel = "spring")
public interface TemplateMapper extends EntityMapper<TemplateDTO, Template> {}
