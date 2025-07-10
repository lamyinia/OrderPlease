package org.com.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.com.context.BaseContext;
import org.com.entity.AddressBook;
import org.com.result.Result;
import org.com.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/addressBook")
@RequiredArgsConstructor
public class AddressBookController {
    private final AddressBookService addressBookService;

    @PostMapping
    public Result addAddress(@RequestBody AddressBook addressBook){
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBookService.addAddress(addressBook);
        return Result.success();
    }

    @PutMapping
    public Result updateAddress(@RequestBody AddressBook addressBook){
        addressBookService.updateById(addressBook);
        return Result.success();
    }

    @PutMapping("/default")
    public Result setDefault(@RequestBody AddressBook addressBook){
        addressBookService.setDefault(addressBook);
        return Result.success();
    }

    @DeleteMapping("/")
    public Result deleteById(Long id){
        addressBookService.removeById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<AddressBook>> list(){
        List<AddressBook> result = addressBookService.filter(AddressBook.builder().userId(BaseContext.getCurrentId()).build());
        return Result.success(result);
    }

    @GetMapping("/{id}")
    public Result<AddressBook> selectById(@PathVariable Long id){
        AddressBook result = addressBookService.getById(id);
        return Result.success(result);
    }

    @GetMapping("/default")
    public Result<AddressBook> selectDefault(){
        List<AddressBook> results = addressBookService.filter(AddressBook.builder().userId(BaseContext.getCurrentId()).build());
        for (AddressBook result : results) {
            if (result.getIsDefault() == 1){
                return Result.success(result);
            }
        }

        return Result.error("没有查询到默认地址");
    }
}
