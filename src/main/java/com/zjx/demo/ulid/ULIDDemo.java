package com.zjx.demo.ulid;

import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;

/**
 * @author zjx
 */
public class ULIDDemo {


    public static void main(String[] args) {
        Ulid ulid = UlidCreator.getUlid();
        System.out.println(ulid);
        System.out.println(ulid.getInstant());
    }
}
