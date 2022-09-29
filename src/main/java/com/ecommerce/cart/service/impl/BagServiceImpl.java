package com.ecommerce.cart.service.impl;

import com.ecommerce.cart.enumeration.FormPayment;
import com.ecommerce.cart.model.Bag;
import com.ecommerce.cart.model.Item;
import com.ecommerce.cart.model.Restaurant;
import com.ecommerce.cart.repository.BagRepository;
import com.ecommerce.cart.repository.ItemRepository;
import com.ecommerce.cart.repository.ProductRepository;
import com.ecommerce.cart.resource.dto.ItemDto;
import com.ecommerce.cart.service.BagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BagServiceImpl implements BagService {

    private final BagRepository bagRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;

    @Override
    public Item includeItemBag(ItemDto itemDto) {

        Bag bag = viewBag(itemDto.getBagId());

        if (bag.isClosed()){
            throw new RuntimeException("Esta compra já está fechada.");
        }

        Item insertItem = Item.builder()
                .quantity(itemDto.getQuantity())
                .bag(bag)
                .product(productRepository.findById(itemDto.getProductId()).orElseThrow(
                        () -> {
                            throw new RuntimeException("Este produto não existe.");
                        }
                ))
                .build();

        List<Item> itemsBag = bag.getItens();
        if (itemsBag.isEmpty()){
            itemsBag.add(insertItem);
        } else {
            Restaurant currentRestaurant = itemsBag.get(0).getProduct().getRestaurant();
            Restaurant restaurantToInsertItem = insertItem.getProduct().getRestaurant();
            if (currentRestaurant.equals(restaurantToInsertItem)){
                itemsBag.add(insertItem);
            } else {
                throw new RuntimeException("Não é possível adicionar produtos de restaurantes diferentes. Feche ou esvazie a sacola.");
            }
        }

        List<Double> valueOfItems = new ArrayList<>();
        for (Item itemBag : itemsBag) {
            double totalValueOfItems = itemBag.getProduct().getUnitaryValue() * itemBag.getQuantity();
            valueOfItems.add(totalValueOfItems);
        }

        double totalValueBag = valueOfItems.stream()
                .mapToDouble(totalValueOfEachItem -> totalValueOfEachItem)
                .sum();

        bag.setAmounts(totalValueBag);
        bagRepository.save(bag);

        return insertItem;
    }

    @Override
    public Bag viewBag(Long id) {
        return bagRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Esta sacola não existe.");
                }
        );
    }

    @Override
    public Bag closeBag(Long id, int numberFormPayment) {
        Bag bag = viewBag(id);

        if (bag.getItens().isEmpty()){
            throw new RuntimeException("Inclua itens na sacola.");
        }

        FormPayment formPayment = numberFormPayment == 0 ? FormPayment.MONEY : FormPayment.MACHINE;
        bag.setFormPayment(formPayment);
        bag.setClosed(true);

        return bagRepository.save(bag);
    }
}
