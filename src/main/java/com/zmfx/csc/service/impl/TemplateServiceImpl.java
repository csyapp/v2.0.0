package com.zmfx.csc.service.impl;

import com.zmfx.csc.domain.Template;
import com.zmfx.csc.repository.TemplateRepository;
import com.zmfx.csc.service.TemplateService;
import com.zmfx.csc.service.dto.TemplateDTO;
import com.zmfx.csc.service.mapper.TemplateMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.zmfx.csc.domain.Template}.
 */
@Service
@Transactional
public class TemplateServiceImpl implements TemplateService {

    private static final Logger log = LoggerFactory.getLogger(TemplateServiceImpl.class);

    private final TemplateRepository templateRepository;

    private final TemplateMapper templateMapper;

    public TemplateServiceImpl(TemplateRepository templateRepository, TemplateMapper templateMapper) {
        this.templateRepository = templateRepository;
        this.templateMapper = templateMapper;
    }

    @Override
    public TemplateDTO save(TemplateDTO templateDTO) {
        log.debug("Request to save Template : {}", templateDTO);
        Template template = templateMapper.toEntity(templateDTO);
        template = templateRepository.save(template);
        return templateMapper.toDto(template);
    }

    @Override
    public TemplateDTO update(TemplateDTO templateDTO) {
        log.debug("Request to update Template : {}", templateDTO);
        Template template = templateMapper.toEntity(templateDTO);
        template.setIsPersisted();
        template = templateRepository.save(template);
        return templateMapper.toDto(template);
    }

    @Override
    public Optional<TemplateDTO> partialUpdate(TemplateDTO templateDTO) {
        log.debug("Request to partially update Template : {}", templateDTO);

        return templateRepository
            .findById(templateDTO.getLibelle())
            .map(existingTemplate -> {
                templateMapper.partialUpdate(existingTemplate, templateDTO);

                return existingTemplate;
            })
            .map(templateRepository::save)
            .map(templateMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TemplateDTO> findOne(String id) {
        log.debug("Request to get Template : {}", id);
        return templateRepository.findById(id).map(templateMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Template : {}", id);
        templateRepository.deleteById(id);
    }
}
