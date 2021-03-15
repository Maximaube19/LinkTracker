package com.clase4.linktracker.service;

import com.clase4.linktracker.exception.InvalidPasswordException;
import com.clase4.linktracker.exception.InvalidURLException;
import com.clase4.linktracker.exception.LinkNotFoundException;
import com.clase4.linktracker.model.LinkDTO;
import com.clase4.linktracker.model.MetricDTO;
import com.clase4.linktracker.repository.ILinkTrackerRepository;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Maxi Maubecin
 */
@Service
public class LinkTrackerServiceImpl implements ILinkTrackerService{

    @Autowired
    private ILinkTrackerRepository repository;

    @Override
    public LinkDTO createLink(LinkDTO linkDTO) throws InvalidURLException {
        validateLink(linkDTO);
        return repository.createLink(linkDTO);
    }

    @Override
    public String getUrl(Integer linkId, String password) throws LinkNotFoundException, InvalidURLException, InvalidPasswordException {
        LinkDTO link = repository.getLink(linkId);
        validateLink(link);
        if(!link.getPassword().equals(password)){
            throw new InvalidPasswordException("The password is incorrect");
        }
        link.setRedirectCount(link.getRedirectCount()+1);
        repository.updateLink(link);
        return link.getUrl();
    }

    @Override
    public MetricDTO getRedirectCount(Integer linkId) throws LinkNotFoundException {
        LinkDTO link = repository.getLink(linkId);
        return new MetricDTO(link.getRedirectCount());
    }

    @Override
    public String invalidateLink(Integer linkId) throws LinkNotFoundException {
        LinkDTO link = repository.getLink(linkId);
        link.setValid(false);
        repository.updateLink(link);
        return "Link successfully invalidated";
    }

    private void validateLink(LinkDTO linkDTO) throws InvalidURLException {
        UrlValidator urlValidator = new UrlValidator();
        if (!urlValidator.isValid(linkDTO.getUrl())) {
            throw new InvalidURLException("The URL: " + linkDTO.getUrl() + " is not valid");
        }
    }
}
