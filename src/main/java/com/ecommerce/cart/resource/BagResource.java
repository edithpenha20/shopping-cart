package com.ecommerce.cart.resource;

import com.ecommerce.cart.model.Bag;
import com.ecommerce.cart.model.Item;
import com.ecommerce.cart.resource.dto.ItemDto;
import com.ecommerce.cart.service.BagService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(value="/ecommerce/sacolas")
@RestController
@RequestMapping("/ecommerce/sacolas")
@RequiredArgsConstructor
public class BagResource {

    private final BagService bagService;

    @PostMapping
    public Item includeItemBag(@RequestBody ItemDto itemDto) {
        return bagService.includeItemBag(itemDto);
    }

    @GetMapping("/{id}")
    public Bag viewBag(@PathVariable("id") Long id){
        return bagService.viewBag(id);
    }

    @PatchMapping("/fecharSacola/{bagId}")
    public Bag closeBag(@PathVariable("bagId") Long bagId,
                               @RequestParam("formPayment") int formPayment) {
        return bagService.closeBag(bagId, formPayment);
    }
}
