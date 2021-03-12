package com.clase4.linktracker.controller;

import com.clase4.linktracker.exception.InvalidPasswordException;
import com.clase4.linktracker.exception.InvalidURLException;
import com.clase4.linktracker.exception.LinkNotFoundException;
import com.clase4.linktracker.exception.LinkTrackerBaseException;
import com.clase4.linktracker.model.ErrorDTO;
import com.clase4.linktracker.model.LinkDTO;
import com.clase4.linktracker.model.MetricDTO;
import com.clase4.linktracker.service.ILinkTrackerService;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Maxi Maubecin
 */
@RestController
public class LinkTrackerController{

    @Autowired
    private ILinkTrackerService service;

    /**
     *
     * Receives a URL inside a LinkDTO as a parameter and returns a LinkDTO
     *
     * @param linkDTO LinkDTO
     * @return LinkDTO
     * @throws InvalidURLException
     * @throws InvalidPasswordException
     */
    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public LinkDTO createLink(@RequestBody LinkDTO linkDTO) throws InvalidURLException, InvalidPasswordException {
        if (linkDTO.getPassword() == null)
            throw new InvalidPasswordException("The field password is required");
        return service.createLink(linkDTO);
    }

    /**
     * Receive a linkId and password and redirects to another page if found
     * @param linkId Integer
     * @param password String
     * @return RedirectView
     * @throws LinkNotFoundException
     * @throws InvalidURLException
     * @throws InvalidPasswordException
     */
    @GetMapping("/link/{linkId}")
    @ResponseStatus(value = HttpStatus.OK)
    public RedirectView redirectTo(@PathVariable Integer linkId, @RequestParam(required = true) String password) throws LinkNotFoundException, InvalidURLException, InvalidPasswordException {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(service.getUrl(linkId, password));
        return redirectView;
    }

    /**
     * Receives a linkId and returns metrics on the number of redirects
     * @param linkId Integer
     * @return MetricDTO
     * @throws LinkNotFoundException
     */
    @GetMapping("/metrics/{linkId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MetricDTO getRedirectCount(@PathVariable Integer linkId) throws LinkNotFoundException {
        return service.getRedirectCount(linkId);
    }

    /**
     * Receives a linkId to invalidate a URL in case it is found
     * @param linkId Integer
     * @return String
     * @throws LinkNotFoundException
     */
    @PostMapping("/invalidate/{linkId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String invalidateLink(@PathVariable Integer linkId) throws LinkNotFoundException {
        return service.invalidateLink(linkId);
    }

    @ExceptionHandler(LinkTrackerBaseException.class)
    public ResponseEntity<ErrorDTO> handleException(LinkTrackerBaseException ex){
        return  new ResponseEntity<>(ex.getErrorDTO(), ex.getStatus());
    }

}
