package com.zjx.demo.designMode.responsibility;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author 65454
 */
@Component
public class ItemInfoHandler extends AbstractDataHandler<ItemInfoHandler.ItemInfo> {

    @Override
    protected ItemInfo doRequest(String query) {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.setItemId(1232112L);
        itemInfo.setItemName("测试商品");
        return itemInfo;
    }

    @Data
    public static class ItemInfo {
        private Long itemId;
        private String itemName;
    }
}
