/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.circulation.Charging;

import com.ilmu.circulation.Charging.Patron;

public class TestPatron {
    public static void main(String[] args) {
        Patron patr = new Patron("015341");
        System.out.println("Patron Details");
        System.out.println("Patron name\t: " + patr.getMsPatronName());
        System.out.println("Patron Status :\t" + patr.getMsPatronStatus());
        System.out.println("Patron MembershipDate :\t" + patr.getMsMemDate());
        System.out.println("Patron Expiry Date : " + patr.getMsMemExpiryDate());
        System.out.println("Patron Branch :\t" + patr.getMsPatronBranch());
        System.out.println("Patron Branch :\t" + patr.getMsPatronElig());
        System.out.println("Patron Category Elig No :\t" + patr.getMsPatrCateEligNo());
        System.out.println("Patron Category :\t" + patr.getMsPatronCategory());
        System.out.println("Items on Loan :\t" + patr.getMsNoCircByPatron());
        System.out.println("Items Overdue :\t" + patr.getNoMsGetItemOverdue());
        System.out.println("Items Reserved :\t" + patr.getNoMsGetItemReserved());
        System.out.println("Items On hold :\t" + patr.getNoMsGetItemOnHold());
        System.out.println("Total Fines :\t" + patr.getMsGetTotalCharged());
        System.out.println("Total Fines Paid :\t" + patr.getMsGetTotalPaid());
        System.out.println("Total Fines Outstanding:\t" + patr.getMsGetTotalFinesOutstanding());
    }
}
