package mocks.crud.task.service;

import mocks.crud.task.model.Address;
import mocks.crud.task.model.Person;
import mocks.crud.task.repository.CrudRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static mocks.crud.task.service.TestUtils.TEST_ADDRESS;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @Mock
    AddressService addressService;
    @Mock
    CrudRepository<Long, Person> personRepository;


    private Person testPerson1;
    private Person testPerson2;
    private Person testPerson3;
    private PersonService personService;
    private List<Person> testList;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        personService = new PersonService(addressService,personRepository);

        testPerson1 = new Person("Dima",24,new Address(1L,"Murmansk"));
        testPerson2 = new Person("Masha",33,new Address(2L,"Samara"));
        testPerson3 = new Person("Sasha",41,new Address(3L,"Magadan"));

        testPerson1.addRelatives(testPerson2);
        testPerson1.addRelatives(testPerson3);

        testList = new ArrayList<>();
        testList.add(testPerson2);
        testList.add(testPerson3);
    }

    @Test
    public void findAllRelativesTest() {
        Person testPerson1 = spy(new Person("Dima",24,new Address(1L,"Murmansk")));
        when(testPerson1.getRelatives()).thenReturn((testList));
        List<Person> result = personService.findAllRelatives(testPerson1);
        verify(testPerson1, times(1)).getRelatives();
        assertEquals(result,testList);
    }

    @Test
    public void getAddressTest() {
        Person testPerson1 = spy(new Person("Dima",24,new Address(1L,"Murmansk")));
        when(testPerson1.getAddress()).thenReturn((TEST_ADDRESS));
        Address result = personService.getAddress(testPerson1);
        verify(testPerson1, times(1)).getAddress();
        assertEquals(result,TEST_ADDRESS);
    }

    @Test
    public void saveTest() {
        personService.save(testPerson1);
        verify(personRepository, times(1)).save(testPerson1);
    }

    @Test
    public void findByIdTest() {
        personService.findById(3L);
        verify(personRepository, times(1)).findById(3L);
    }

    @Test
    public void findAllTest() {
        personService.findAll();
        verify(personRepository, times(1)).findAll();
    }

    @Test
    public void updateTest()
    {
        personService.update(testPerson1);
        verify(personRepository, times(1)).update(testPerson1);
    }

    @Test
    public void deleteTest() {
        personService.delete(testPerson1);
        verify(personRepository, times(1)).delete(testPerson1);
    }
}