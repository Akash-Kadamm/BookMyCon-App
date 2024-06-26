package com.dbMigration.demo.service;

import com.dbMigration.demo.mysql.MysqlAddressRepo;
import com.dbMigration.demo.payload.Address;
import com.dbMigration.demo.postgresql.PostgresqlAddressRepo;
import com.dbMigration.demo.utility.ResponseMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private MysqlAddressRepo mysqlAddressRepo;

    @Autowired
    private PostgresqlAddressRepo postgresqlAddressRepo;

    private static Logger logger = Logger.getLogger(AddressService.class);

    /*
     * Fetch Address by Id.
     *
     * @param address Id.
     * @return Address
     * */
    public Address getAddressById(int id) {
        Optional<Address> savedAddress = mysqlAddressRepo.findById(id);
        if (savedAddress.isEmpty()) {
            return null;
        }
        return savedAddress.get();
    }

    /*
     * Save Address in Postgresql database.
     *
     * @param Address
     * @return String message
     * */
    public ResponseMessage saveAddress(Address address) {
        postgresqlAddressRepo.save(address);
        return ResponseMessage.ADDRESS_RECORD_SAVED;
    }

    /**/
    public Address getAddressFromPostgresql(int addressId) {
        Optional<Address> savedAddress = postgresqlAddressRepo.findById(addressId);
        if (savedAddress.isEmpty()) {
            return null;
        }
        return savedAddress.get();
    }

    public String deleteAddress(int addressId) {
        postgresqlAddressRepo.deleteById(addressId);
        return ResponseMessage.ADDRESS_RECORD_DELETED.getMessage();
    }
}
