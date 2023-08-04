package hello.itemservice.domain.multipart;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository("multipartRepository")
public class ItemRepository {
    private Long seq = 0L;
    private final Map<Long, Item> store = new HashMap<>();

    public Item save(Item item) {
        item.setId(++seq);
        store.put(seq, item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }
}
