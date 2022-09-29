package com.ecommerce.cart.service;

import com.ecommerce.cart.model.Bag;
import com.ecommerce.cart.model.Item;
import com.ecommerce.cart.resource.dto.ItemDto;

public interface BagService {

    Item includeItemBag(ItemDto itemDto);
    Bag viewBag(Long id);
    Bag closeBag(Long id, int formPayment);

}
