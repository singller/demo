package com.zjx.demo.designMode.factory.factorymethold.sharedemo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 65454
 */
@Component
public class ShareFactory {
    @Autowired
    private List<Share> shareFunctionList;

    public Share getShareFunction(String type) {
        for (Share shareFunction : shareFunctionList) {
            if (shareFunction.getShareFunctionType().equals(type)) {
                return shareFunction;
            }
        }
        return null;
    }


    public enum EnumShareType {
        SUCCESS_ORDER("successOrder");

        private String name;

        EnumShareType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
