/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.stereotype.Controller
 *  org.springframework.web.bind.annotation.RequestMapping
 */
package com.wilmu.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping(value={"/view-products"})
    public String viewProducts() {
        return "view-products";
    }

    @RequestMapping(value={"/add-products"})
    public String addProducts() {
        return "add-products";
    }
}
