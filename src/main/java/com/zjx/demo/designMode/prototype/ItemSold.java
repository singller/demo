package com.zjx.demo.designMode.prototype;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import org.assertj.core.util.Lists;

import java.util.*;

/**
 * @author 65454
 */
@Getter
@Setter
public class ItemSold implements Cloneable {

    private Long itemId;
    private Long sold;
    private List<SkuSold> skuSolds;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static List<ItemSold> getResultFromDb(Date updateTime) {
        ArrayList<ItemSold> result = Lists.newArrayList();
        ItemSold itemSold = new ItemSold();
        itemSold.setSold(1L);
        itemSold.setItemId(1L);
        itemSold.setSkuSolds(Collections.singletonList(new SkuSold(3L, 3L)));
        result.add(itemSold);
        return result;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        HashMap<Long, ItemSold> resultMap = Maps.newHashMap();
        List<ItemSold> resultFromDb = ItemSold.getResultFromDb(new Date());
        for (ItemSold itemSold : resultFromDb) {
            ItemSold clone = (ItemSold) itemSold.clone();
            System.out.println("clone itemSold sku ======" + clone.getSkuSolds());
            System.out.println("itemSold sku ======" + itemSold.getSkuSolds());
            System.out.println("clone ======" + clone + "=======" + JSON.toJSONString(clone));
            System.out.println("itemSold ======" + itemSold + "=======" + JSON.toJSONString(clone));

            resultMap.put(itemSold.getItemId(), clone);
        }
    }
}
