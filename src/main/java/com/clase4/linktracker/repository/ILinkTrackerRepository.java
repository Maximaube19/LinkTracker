package com.clase4.linktracker.repository;

import com.clase4.linktracker.exception.InvalidURLException;
import com.clase4.linktracker.exception.LinkNotFoundException;
import com.clase4.linktracker.model.LinkDTO;

public interface ILinkTrackerRepository {

    LinkDTO createLink(LinkDTO linkDTO);

    LinkDTO getLink(Integer linkId) throws LinkNotFoundException;

    boolean updateLink(LinkDTO linkDTO) throws LinkNotFoundException;
}
