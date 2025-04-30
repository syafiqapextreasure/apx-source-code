/*
 * Decompiled with CFR 0.152.
 */
package com.ilmu.circulation.Charging;

import com.ilmu.circulation.Charging.Patron;
import java.util.Scanner;

public class Circulation_Application {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int process = 0;
        System.out.println("Please enter patronID");
        String msPatronID = input.nextLine();
        System.out.println("Please enter Accession Number");
        String AccessionNumber = input.nextLine();
        System.out.println("1.Patron Details");
        System.out.println("2.Charging");
        System.out.println("3.Discharging");
        System.out.println("4.Renewal");
        System.out.println("5.Reservation");
        System.out.println("Please enter the process you would like to do");
        process = input.nextInt();
        switch (process) {
            case 1: {
                Circulation_Application.patrondetails(msPatronID);
                break;
            }
            case 2: {
                Circulation_Application.charging();
                break;
            }
            case 3: {
                Circulation_Application.discharging();
                break;
            }
            case 4: {
                Circulation_Application.renewal();
                break;
            }
            case 5: {
                Circulation_Application.reservation();
            }
        }
    }

    public static void patrondetails(String msPatronID) {
        Patron patron = new Patron(msPatronID);
        patron.viewtable(msPatronID);
        Circulation_Application.printpatrondetails(patron);
    }

    public static void charging() {
    }

    public static void discharging() {
    }

    public static void renewal() {
    }

    public static void reservation() {
    }

    public static void printpatrondetails(Patron patron) {
        System.out.println("Patron ID" + patron.getMsPatronId());
        System.out.println("Patron Name" + patron.getMsPatronName());
        System.out.println("Patron Category " + patron.getMsPatronCategory());
        System.out.println("Patron Expiry Date  " + patron.getMsMemExpiryDate());
        System.out.println("No of Circulated Item" + patron.getMsNoCircByPatron());
        System.out.println("No of Reserved Item" + patron.getNoMsGetItemReserved());
        System.out.println("No of Lost Item" + patron.getMsGetLostItemCount());
        System.out.println("Total Fines" + patron.getMsNoCircByPatron());
    }
}
