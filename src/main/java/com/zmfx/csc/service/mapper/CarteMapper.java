package com.zmfx.csc.service.mapper;

import com.zmfx.csc.domain.Carte;
import com.zmfx.csc.domain.Template;
import com.zmfx.csc.service.dto.CarteDTO;
import com.zmfx.csc.service.dto.TemplateDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Carte} and its DTO {@link CarteDTO}.
 */
@Mapper(componentModel = "spring")
public interface CarteMapper extends EntityMapper<CarteDTO, Carte> {
    @Mapping(target = "template", source = "template", qualifiedByName = "templateLibelle")
    CarteDTO toDto(Carte s);

    @Named("templateLibelle")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "libelle", source = "libelle")
    TemplateDTO toDtoTemplateLibelle(Template template);
}
