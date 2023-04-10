package com.bookmycon.service;


import com.bookmycon.model.Areas;
import com.bookmycon.model.UserLayout;
import com.bookmycon.repository.AreasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class AreasService {


    @Autowired
    AreasRepository areasRepository;

    Logger logger=Logger.getLogger(AreasService.class);

//    public AreasService(AreasRepository areasRepository) {
//    }

    public List<Areas> findAll() {

        logger.debug("Finding all areas: ");
        return areasRepository.findAll();
    }

    public Optional<Areas> findById(int id) {

        logger.debug("Finding area by id: {} " + id);
        return areasRepository.findById(id);
    }

    public List<Areas> findByUserLayout(UserLayout userLayout){

        logger.debug("Finding areas by user layout: {} " + userLayout);
        return  areasRepository.findByUserLayout(userLayout);
    }

    public void addAreas(Areas areas) {

        System.out.println(areas);
        logger.debug("Adding area: {}" + areas);
        areasRepository.save(areas);
    }

    public Optional<Areas> getAreasById(int id) {

        logger.debug("Getting area by id: {} " + id);
        return areasRepository.findById(id);
    }

    public void deleteAreas(int id) {

        logger.debug("Deleting area by id: {} " + id);
        areasRepository.deleteById(id);
    }

    public void editAreas(int id, Areas areas ) {

        logger.debug("Editing area by id: {}" + id + "new area: {}" + areas);
        areasRepository.save(areas);
    }


}
