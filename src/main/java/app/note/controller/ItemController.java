package app.note.controller;

import app.note.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ItemController {

    private ItemService itemService;

    // 상품 리스트.
    @GetMapping("/items")
    public void listItem() {
        itemService.listItem();
    }

    // 상품 구매


}
