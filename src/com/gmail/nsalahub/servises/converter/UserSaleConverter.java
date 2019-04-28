package com.gmail.nsalahub.servises.converter;

import com.gmail.nsalahub.reposetory.model.sale.UserSale;
import com.gmail.nsalahub.servises.model.sale.UserSaleDTO;

public interface UserSaleConverter {

    UserSale fromDTO(UserSaleDTO userSaleDTO);

    UserSaleDTO toDTO (UserSale userSale);
}
