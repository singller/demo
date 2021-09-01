package com.zjx.demo.designMode.responsibility;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author 65454
 */
@Component
public class SkuInfoHandler extends AbstractDataHandler<SkuInfoHandler.SkuInfo> {

    @Override
    protected SkuInfo doRequest(String query) {

        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setSkuId(34434L);
        skuInfo.setSkuName("测试SKU");
        return skuInfo;
    }

    @Data
    public static class SkuInfo {
        private Long skuId;
        private String skuName;
    }
}
