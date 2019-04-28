package com.gmail.nsalahub.servises.converter.impl.sale;

import com.gmail.nsalahub.reposetory.model.sale.UserSale;
import com.gmail.nsalahub.servises.converter.UserSaleConverter;
import com.gmail.nsalahub.servises.converter.impl.UserConverterImpl;
import com.gmail.nsalahub.servises.model.sale.UserSaleDTO;

public class UserSaleConverterImpl implements UserSaleConverter {

    private static UserSaleConverterImpl instance;

    private UserSaleConverterImpl(){}

    public static UserSaleConverterImpl getInstance(){
        if (instance == null){
            instance = new UserSaleConverterImpl();
        }
        return instance;
    }

    @Override
    public UserSale fromDTO(UserSaleDTO userSaleDTO) {
        UserSale userSale = new UserSale();
        userSale.setId(userSaleDTO.getId());
        return userSale;
    }

    @Override
    public UserSaleDTO toDTO(UserSale userSale) {
        UserSaleDTO userSaleDTO = new UserSaleDTO();
        userSaleDTO.setId(userSale.getId());
        return userSaleDTO;
    }
}
