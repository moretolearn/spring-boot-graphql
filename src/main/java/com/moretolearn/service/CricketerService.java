package com.moretolearn.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import com.moretolearn.model.Cricketer;
import com.moretolearn.model.Group;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CricketerService {
	
    private List<Cricketer> cricketers = new ArrayList<>();
    AtomicInteger id = new AtomicInteger(0);

    public List<Cricketer> findAll() {
        return cricketers;
    }

    public Optional<Cricketer> findOne(Integer id) {
        return cricketers.stream()
                .filter(cricketer -> cricketer.id() == id).findFirst();
    }

    public Cricketer create(String name, GregorianCalendar calendar, Group group) {
        Cricketer cricketer = new Cricketer(id.incrementAndGet(), name, calendar, group);
        cricketers.add(cricketer);
        return cricketer;
    }

    public Cricketer delete(Integer id) {
        Cricketer cricketer = cricketers.stream().filter(c -> c.id() == id)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("No Cricketer"));
        cricketers.remove(cricketer);
        return cricketer;
    }

    public Cricketer update(Integer id, String name, Group group) {
        Cricketer updatedCricketer = new Cricketer(id, name,new GregorianCalendar(), group);
        Optional<Cricketer> optional = cricketers.stream()
                .filter(c -> c.id() == id).findFirst();

        if (optional.isPresent()) {
            Cricketer player = optional.get();
            int index = cricketers.indexOf(player);
            cricketers.set(index, updatedCricketer);
        } else {
            throw new IllegalArgumentException("Invalid Cricketer");
        }
        return updatedCricketer;
    }

    @PostConstruct
    private void init() {
    	cricketers.add(new Cricketer(id.incrementAndGet(), "MS Dhoni", new GregorianCalendar(),Group.A));
    	cricketers.add(new Cricketer(id.incrementAndGet(), "Rohit Sharma",new GregorianCalendar(), Group.B));
        cricketers.add(new Cricketer(id.incrementAndGet(), "Jasprit Bumrah",new GregorianCalendar(), Group.C));
        cricketers.add(new Cricketer(id.incrementAndGet(), "Rishabh pant",new GregorianCalendar(), Group.D));
        cricketers.add(new Cricketer(id.incrementAndGet(), "Suresh Raina",new GregorianCalendar(), Group.E));
    }
}