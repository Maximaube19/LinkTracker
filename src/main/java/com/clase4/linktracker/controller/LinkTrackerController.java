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

@RestController
public class LinkTrackerController{

    @Autowired
    private ILinkTrackerService service;

    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public LinkDTO createLink(@RequestBody LinkDTO linkDTO) throws InvalidURLException, InvalidPasswordException {
        if (linkDTO.getPassword() == null)
            throw new InvalidPasswordException("¡El campo password es obligatorio!");
        return service.createLink(linkDTO);
    }

    @GetMapping("/link/{linkId}")
    @ResponseStatus(value = HttpStatus.OK)
    public RedirectView redirectTo(@PathVariable Integer linkId, @RequestParam(required = true) String password) throws LinkNotFoundException, InvalidURLException, InvalidPasswordException {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(service.getUrl(linkId, password));
        return redirectView;
    }

    @GetMapping("/metrics/{linkId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MetricDTO getRedirectCount(@PathVariable Integer linkId) throws LinkNotFoundException {
        return service.getRedirectCount(linkId);
    }

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
