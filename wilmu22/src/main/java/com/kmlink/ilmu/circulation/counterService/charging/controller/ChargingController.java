/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestMethod
 *  org.springframework.web.bind.annotation.RequestParam
 *  org.springframework.web.bind.annotation.RestController
 */
package com.kmlink.ilmu.circulation.counterService.charging.controller;

import com.kmlink.ilmu.circulation.counterService.charging.model.Charging;
import com.kmlink.ilmu.circulation.counterService.charging.service.ChargingService;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChargingController {
    ChargingService chargingService;
    Charging charging;

    @RequestMapping(value={"/ChargingController"}, method={RequestMethod.GET})
    public Charging Charging(@RequestParam(value="name", required=false) String name, @RequestParam(value="patronid", required=false) String patronId, @RequestParam(value="action", required=false) String action) throws IOException {
        this.charging = this.chargingService.validateCharging(name, patronId, action);
        return this.charging;
    }
}
