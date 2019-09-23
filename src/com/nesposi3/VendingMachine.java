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
        int newValue = this.value + totalN + totalD * +(totalQ);
        if (delta) {
            this.nickel += n;
            this.dime += d;
            this.quarter += q;
            this.value += totalD + totalN + totalQ;
            if (c > 0) {
                this.change = !this.change;
            }
            if (newValue >= 100) {
                //vend coffee
                this.value -= 100;
            }
            if (this.change) {
                getChange(true, this.value);
            }
        } else {
            if (newValue >= 100) {
                //vend coffee
                System.out.println("Coffee");
            }
            if (this.change) {
                this.getChange(false, this.value);
            }
        }
    }

    public void getChange(boolean delta, int value) {
        int q = 0;
        int n = 0;
        int d = 0;
        if (delta) {
            //this.value = 0;
        } else {
            System.out.println("Here is your change");
        }
    }

    @Override
    public String toString() {
        return "Current value: " + this.value + "\nNickels: " + this.nickel + " Dimes: " + this.dime + " Quarters: " + this.quarter;
    }
}
