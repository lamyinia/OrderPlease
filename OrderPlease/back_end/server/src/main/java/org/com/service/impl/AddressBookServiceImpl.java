package org.com.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.com.constant.StatusConstant;
import org.com.context.BaseContext;
import org.com.entity.AddressBook;
import org.com.mapper.AddressBookMapper;
import org.com.service.AddressBookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
    @Autowired
    AddressBookMapper addressBookMapper;

    @Override
    public void addAddress(AddressBook addressBook) {
        addressBookMapper.insert(addressBook);
    }

    @Override
    public List<AddressBook> filter(AddressBook build) {
        return addressBookMapper.filter(build);
    }

    @Override
    @Transactional
    public void setDefault(AddressBook addressBook) {
        addressBookMapper.updateAllNotDefault(BaseContext.getCurrentId());

        addressBook.setUserId(BaseContext.getCurrentId());
        addressBook.setIsDefault(StatusConstant.DISABLE);
        AddressBook metaAddressBook = new AddressBook();
        BeanUtils.copyProperties(addressBook, metaAddressBook);
        metaAddressBook.setIsDefault(StatusConstant.ENABLE);

        this.update(metaAddressBook, new LambdaUpdateWrapper<AddressBook>().setEntity(addressBook));
    }
}
