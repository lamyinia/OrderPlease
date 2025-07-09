package org.com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.com.entity.AddressBook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressBookService extends IService<AddressBook> {
    void addAddress(AddressBook addressBook);

    List<AddressBook> filter(AddressBook build);

    void setDefault(AddressBook addressBook);
}
