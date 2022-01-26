package com.example.team1.service;

import com.example.team1.DAO.Dao.AddressDao;
import com.example.team1.entity.Address;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestAddressService {

    @Mock
    private AddressDao addressDao;

    @InjectMocks
    private AddressService addressService;

    private Address address1;
    private Address address2;

    static List<Address> list;
    static {
        list = new ArrayList<>();
        list.add(Address.builder().id(1).addressLine1("line1").addressLine2("line2").city("city1").state("state1").zipcode("11").build());
        list.add(Address.builder().id(2).addressLine1("line1").addressLine2("line2").city("city2").state("state2").zipcode("22").build());
        list.add(Address.builder().id(3).addressLine1("line1").addressLine2("line2").city("city3").state("state3").zipcode("33").build());
        list.add(Address.builder().id(4).addressLine1("line1").addressLine2("line2").city("city4").state("state4").zipcode("44").build());
    }

    @BeforeEach
    public void setup(){
        address1 = Address.builder().id(1).addressLine1("line1").addressLine2("line2").city("city1").state("state1").zipcode("11").build();
        address2 = Address.builder().id(2).addressLine1("line1").addressLine2("line2").city("city2").state("state2").zipcode("22").build();
        lenient().when(addressDao.findAllAddress()).thenReturn(list);

        Address address = list.get(0);
        lenient().when(addressDao.getAddressById(1)).thenReturn(address);
        lenient().when(addressDao.getAddressById(100)).thenReturn(new Address());

    }

    @Test(expected=NullPointerException.class)
    public void findAllTest(){
        assertEquals(list, addressService.findAllAddr());
        verify(addressDao, times(1)).findAllAddress();
    }

    @Test(expected=NullPointerException.class)
    public void findByIdTest(){
        assertEquals(list.get(0), addressService.findById(1));
        verify(addressDao, times(1)).getAddressById(1);
    }
}
