package edu.unisabana.tyvs.domain.service;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import edu.unisabana.tyvs.domain.model.Gender;
import edu.unisabana.tyvs.domain.model.Person;
import edu.unisabana.tyvs.domain.model.RegisterResult;

public class RegistryTest {

    @Test
    public void shouldReturnValidWhenPersonIsAliveAdultAndNotDuplicated() {
        // Arrange
        Registry registry = new Registry();
        Person person = new Person("Andres", 12345, 30, Gender.MALE, true);

        // Act
        RegisterResult result = registry.registerVoter(person);

        // Assert
        assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void shouldReturnDeadWhenPersonIsNotAlive() {
        // Arrange
        Registry registry = new Registry();
        Person person = new Person("Carlos", 54321, 40, Gender.MALE, false);

        // Act
        RegisterResult result = registry.registerVoter(person);

        // Assert
        assertEquals(RegisterResult.DEAD, result);
    }

    @Test
    public void shouldReturnUnderageWhenPersonIs17YearsOld() {
        // Arrange
        Registry registry = new Registry();
        Person person = new Person("Luis", 10001, 17, Gender.MALE, true);

        // Act
        RegisterResult result = registry.registerVoter(person);

        // Assert
        assertEquals(RegisterResult.UNDERAGE, result);
    }

    @Test
    public void shouldReturnValidWhenPersonIs18YearsOld() {
        // Arrange
        Registry registry = new Registry();
        Person person = new Person("Maria", 10002, 18, Gender.FEMALE, true);

        // Act
        RegisterResult result = registry.registerVoter(person);

        // Assert
        assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void shouldReturnValidWhenPersonIs120YearsOld() {
        // Arrange
        Registry registry = new Registry();
        Person person = new Person("Pedro", 10003, 120, Gender.MALE, true);

        // Act
        RegisterResult result = registry.registerVoter(person);

        // Assert
        assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void shouldReturnInvalidAgeWhenPersonHasNegativeAge() {
        // Arrange
        Registry registry = new Registry();
        Person person = new Person("Laura", 10004, -1, Gender.FEMALE, true);

        // Act
        RegisterResult result = registry.registerVoter(person);

        // Assert
        assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void shouldReturnInvalidAgeWhenPersonIsOlderThan120() {
        // Arrange
        Registry registry = new Registry();
        Person person = new Person("Roberto", 10005, 121, Gender.MALE, true);

        // Act
        RegisterResult result = registry.registerVoter(person);

        // Assert
        assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void shouldReturnDuplicatedWhenPersonIdWasAlreadyRegistered() {
        // Arrange
        Registry registry = new Registry();
        Person firstPerson = new Person("Sofia", 777, 25, Gender.FEMALE, true);
        Person duplicatedPerson = new Person("Sofia Duplicada", 777, 25, Gender.FEMALE, true);

        // Act
        RegisterResult firstResult = registry.registerVoter(firstPerson);
        RegisterResult secondResult = registry.registerVoter(duplicatedPerson);

        // Assert
        assertEquals(RegisterResult.VALID, firstResult);
        assertEquals(RegisterResult.DUPLICATED, secondResult);
    }

    @Test
    public void shouldReturnInvalidWhenPersonIsNull() {
        // Arrange
        Registry registry = new Registry();

        // Act
        RegisterResult result = registry.registerVoter(null);

        // Assert
        assertEquals(RegisterResult.INVALID, result);
    }

    @Test
    public void shouldReturnInvalidWhenPersonIdIsZero() {
        // Arrange
        Registry registry = new Registry();
        Person person = new Person("Persona sin documento", 0, 25, Gender.UNIDENTIFIED, true);

        // Act
        RegisterResult result = registry.registerVoter(person);

        // Assert
        assertEquals(RegisterResult.INVALID, result);
    }
}