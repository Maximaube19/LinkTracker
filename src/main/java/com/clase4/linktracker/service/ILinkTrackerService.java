package com.clase4.linktracker.service;

import com.clase4.linktracker.exception.InvalidPasswordException;
import com.clase4.linktracker.exception.InvalidURLException;
import com.clase4.linktracker.exception.LinkNotFoundException;
import com.clase4.linktracker.model.LinkDTO;
import com.clase4.linktracker.model.MetricDTO;

/**
 * @author Maxi Maubecin
 */
public interface ILinkTrackerService {
    LinkDTO createLink(LinkDTO linkDTO) throws InvalidURLException;

    String getUrl(Integer linkId, String password) throws LinkNotFoundException, InvalidURLException, InvalidPasswordException;

    MetricDTO getRedirectCount(Integer linkId) throws LinkNotFoundException;

    String invalidateLink(Integer linkId) throws LinkNotFoundException;
}
