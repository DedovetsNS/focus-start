package mocks.crud.task.service;

import java.util.List;
import java.util.ArrayList;
import mocks.crud.task.model.Address;
import mocks.crud.task.repository.CrudRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import static mocks.crud.task.service.TestUtils.TEST_ADDRESS;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTest {

    @Mock
    CrudRepository<Long, Address> addressRepository;

    private  AddressService addressService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        addressService = new AddressService(addressRepository);
    }

    @Test
    public void saveTest() {
        AddressService spy = spy(new AddressService(addressRepository));
        spy.save(TEST_ADDRESS);
        spy.save(TEST_ADDRESS);
        verify(addressRepository, times(2)).save(TEST_ADDRESS);
    }

    @Test
    public void findByIdTest() {
        when(addressRepository.findById(3L)).thenReturn(TEST_ADDRESS);
        Address result = addressService.findById(3L);
        assertEquals(result, TEST_ADDRESS);
        verify(addressRepository, times(1)).findById(3L);
    }

    @Test
    public void findAllTest() {
        List<Address> testListAddress = new ArrayList<>();
        testListAddress.add(TEST_ADDRESS);
        testListAddress.add(TEST_ADDRESS);
        testListAddress.add(TEST_ADDRESS);
        when(addressRepository.findAll()).thenReturn(testListAddress);
        List<Address> result = addressService.findAll();
        assertEquals(result, testListAddress);
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    public void updateTest() {
        when(addressRepository.update(TEST_ADDRESS)).thenReturn(TEST_ADDRESS);
        Address result = addressService.update(TEST_ADDRESS);
        verify(addressRepository, times(1)).update(TEST_ADDRESS);
    }

    @Test
    public void deleteTest() {
        AddressService spy = spy(new AddressService(addressRepository));
        spy.delete(TEST_ADDRESS);
        verify(addressRepository, times(1)).delete(TEST_ADDRESS);
    }
}