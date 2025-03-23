package com.esgitech.fsapp.execute;

import com.esgitech.fsapp.model.Sport;
import com.esgitech.fsapp.repos.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SportDataInitializer implements CommandLineRunner {

    @Autowired
    private SportRepository sportRepository;

    @Override
    public void run(String... args) throws Exception {
        sportRepository.save(new Sport(null, "Football"));
        sportRepository.save(new Sport(null, "Basketball"));
        sportRepository.save(new Sport(null, "Tennis"));
        sportRepository.save(new Sport(null, "Handball"));
    }
}
