package com.nesposi3;

public class VendingMachine {
    private int nickel;
    private int dime;
    private boolean change;
    private int value;
    private int quarter;

    public VendingMachine() {
        this.dime = 0;
        this.nickel = 0;
        this.quarter = 0;
        this.change = false;
    }

    public void getInput(String input) {
        int n = 0;
        int d = 0;
        int q = 0;
        int c = 0;
        int w = 0;
        for (int i = 0; i < input.length(); i++) {
            char charAt = input.charAt(i);
            if (charAt == 'q') {
                q++;
            } else if (charAt == 'd') {
                d++;
            } else if (charAt == 'n') {
                n++;
            } else if (charAt == 'c') {
                c++;
            } else if (charAt == 'w') {
                w++;
            }
        }
        this.output(n, d, q, c, w);
        this.changeState(n, d, q, c, w);
    }

    private void changeState(int n, int d, int q, int c, int w) {
        this.determineNewState(true, n, d, q, c, w);
    }

    private void output(int n, int d, int q, int c, int w) {
        this.determineNewState(false, n, d, q, c, w);
    }

    private void determineNewState(boolean delta, int n, int d, int q, int c, int w) {
        int totalN = n * 5;
        int totalD = d * 10;
        int totalQ = q * 25;
        if (delta) {
            // The vending machine must check to see these conditions before it adjusts value
            if (this.value >= 100) {
                //vend coffee
                this.value -= 100;
            }
            if (this.change) {
                getChange(true);
            }

            if (c > 0) {
                this.change = true;
            }
            this.nickel += n;
            this.dime += d;
            this.quarter += q;
            this.value += totalD + totalN + totalQ;
        } else {
            if (this.value >= 100) {
                //vend coffee
                System.out.println("Coffee");
            }
            if (this.change) {
                this.getChange(false);
            }
        }
    }

    private void getChange(boolean delta) {
        int q = 0;
        int n = 0;
        int d = 0;
        if (delta) {
            this.value = 0;
            // We want the change flag to reset after every time the user gets change
            this.change = false;
        } else {
            System.out.println("Here is your change");
        }
    }

    @Override
    public String toString() {
        return "Current value: " + this.value + "\nNickels: " + this.nickel + " Dimes: " + this.dime + " Quarters: " + this.quarter + " Change: " + this.change;
    }
}
