/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.stereotype.Controller
 *  org.springframework.web.bind.annotation.RequestMapping
 */
package com.kmlink.ilmu.shared.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmailBrowserController {
    @RequestMapping(value={"/create-reserve"})
    public String Create_Reserve() {
        System.out.println("in spring controller /create-reserve");
        return "jsp/shared/Create_ModalReserve";
    }
}
