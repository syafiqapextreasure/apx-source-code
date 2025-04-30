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

import com.kmlink.ilmu.circulation.counterService.charging.model.Patron;
import com.kmlink.ilmu.circulation.counterService.charging.repository.PatronRepository;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatronController {
    private PatronRepository patronRepository;

    @RequestMapping(value={"/PatronElig"}, method={RequestMethod.GET})
    public Patron getPatron(@RequestParam(value="action", required=false) String action, @RequestParam(value="GL14PATR", required=false) String patronId) throws IOException {
        return this.patronRepository.retrievePatronDetailById(patronId);
    }
}
