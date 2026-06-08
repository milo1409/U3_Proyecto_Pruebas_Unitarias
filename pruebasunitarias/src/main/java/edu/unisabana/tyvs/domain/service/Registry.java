package edu.unisabana.tyvs.domain.service;

import java.util.HashSet;
import java.util.Set;

import edu.unisabana.tyvs.domain.model.Person;
import edu.unisabana.tyvs.domain.model.RegisterResult;

public class Registry {

    private static final int MIN_VALID_AGE = 18;
    private static final int MAX_VALID_AGE = 120;

    private final Set<Integer> registeredIds = new HashSet<Integer>();

    public RegisterResult registerVoter(Person p) {
        if (p == null) {
            return RegisterResult.INVALID;
        }

        if (p.getId() <= 0) {
            return RegisterResult.INVALID;
        }

        if (!p.isAlive()) {
            return RegisterResult.DEAD;
        }

        if (p.getAge() < 0 || p.getAge() > MAX_VALID_AGE) {
            return RegisterResult.INVALID_AGE;
        }

        if (p.getAge() < MIN_VALID_AGE) {
            return RegisterResult.UNDERAGE;
        }

        if (registeredIds.contains(p.getId())) {
            return RegisterResult.DUPLICATED;
        }

        registeredIds.add(p.getId());

        return RegisterResult.VALID;
    }
}