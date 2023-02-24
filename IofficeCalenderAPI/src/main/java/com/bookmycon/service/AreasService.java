package com.bookmycon.service;


import com.bookmycon.model.Areas;
import com.bookmycon.model.UserLayout;
import com.bookmycon.repository.AreasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreasService {


    @Autowired
    AreasRepository areasRepository;


    public List<Areas> findAll() {
        return areasRepository.findAll();
    }

    public Optional<Areas> findById(int id) {
        return areasRepository.findById(id);
    }

    public List<Areas> findByUserLayout(UserLayout userLayout){
        return  areasRepository.findByUserLayout(userLayout);
    }

    public void addAreas(Areas areas) {

        System.out.println(areas);
        areasRepository.save(areas);
    }

    public Optional<Areas> getAreasById(int id) {
        return areasRepository.findById(id);
    }

    public void deleteAreas(int id) {
        areasRepository.deleteById(id);
    }

    public void editAreas(int id, Areas areas ) {
        areasRepository.save(areas);
    }


}
