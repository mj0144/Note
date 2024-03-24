package app.note.service;

import app.note.dao.ItemSpringDataRepository;
import app.note.entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private ItemSpringDataRepository itemSpringDataRepository;

    public Item getItem(Long id) {
        return itemSpringDataRepository.findById(id).orElse(null);
    }

    public List<Item> listItem() {
        return itemSpringDataRepository.findAll();
    }
}
