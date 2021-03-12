package com.clase4.linktracker.repository;

import com.clase4.linktracker.exception.LinkNotFoundException;
import com.clase4.linktracker.model.LinkDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LinkTrackerRepositoryImpl implements ILinkTrackerRepository{

    private List<LinkDTO> listadoLinks;

    public LinkTrackerRepositoryImpl() {
        this.listadoLinks = new ArrayList<>();
    }

    @Override
    public LinkDTO createLink(LinkDTO linkDTO) {
        linkDTO.setLinkId(listadoLinks.size() + 1);
        listadoLinks.add(linkDTO);
        return linkDTO;
    }

    @Override
    public LinkDTO getLink(Integer linkId) throws LinkNotFoundException {
        for (LinkDTO link: listadoLinks){
            if (link.getLinkId().equals(linkId) && link.isValid())
                return link;
        }
        throw new LinkNotFoundException("No se encontró el Link con ID: " + linkId);
    }

    @Override
    public boolean updateLink(LinkDTO linkDTO) throws LinkNotFoundException {
        for (int i=0; i < listadoLinks.size(); i++){
            if (listadoLinks.get(i).getLinkId().equals(linkDTO.getLinkId())) {
                listadoLinks.set(i, linkDTO);
                return true;
            }
        }
        throw new LinkNotFoundException("No se encontró el Link con ID: " + linkDTO.getLinkId());
    }
}
