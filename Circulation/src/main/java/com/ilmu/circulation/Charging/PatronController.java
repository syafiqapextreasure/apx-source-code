/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.ui.ModelMap
 *  org.springframework.web.bind.annotation.ModelAttribute
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestMethod
 *  org.springframework.web.servlet.ModelAndView
 */
package com.ilmu.circulation.Charging;

import com.ilmu.circulation.Charging.Patron;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class PatronController {
    @RequestMapping(value={"/01_Charging"}, method={RequestMethod.GET})
    public ModelAndView patron() {
        return new ModelAndView("patron", "command", (Object)new Patron());
    }

    @RequestMapping(value={"/Charging"}, method={RequestMethod.GET})
    public String ViewEligablePatron(@ModelAttribute(value="SpringWeb") Patron patr, ModelMap model, String GLPatr14) {
        model.addAttribute("name", (Object)patr.getMsPatronName(GLPatr14));
        return "result";
    }
}
