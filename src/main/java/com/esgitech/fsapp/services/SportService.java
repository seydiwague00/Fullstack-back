package com.esgitech.fsapp.services;

import com.esgitech.fsapp.model.Sport;
import com.esgitech.fsapp.repos.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportService {

    @Autowired
    private SportRepository sportRepository;

    public Sport getSport(String sportName) {
        return sportRepository.findByName(sportName);
    }

    public List<Sport> getSports() {
        return sportRepository.findAll();
    }

    public Sport addSport(Sport sport) {
        return sportRepository.save(sport);
    }

    public Sport updateSport(Sport sport) {
        return sportRepository.save(sport);
    }

    public void deleteSport(String sportName) {
        sportRepository.deleteByName(sportName);
    }
}
