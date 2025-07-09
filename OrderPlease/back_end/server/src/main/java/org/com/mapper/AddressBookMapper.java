package org.com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;
import org.com.entity.AddressBook;

import java.util.List;

public interface AddressBookMapper extends BaseMapper<AddressBook> {
    List<AddressBook> filter(AddressBook build);

    @Update("update address_book set is_default = 0 where user_id = #{userId}")
    void updateAllNotDefault(Long userId);
}
