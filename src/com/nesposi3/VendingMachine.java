package com.nesposi3;

public class VendingMachine {
    private int nickel;
    private int dime;
    private boolean change;
    private int value;
    private int quarter;
    public VendingMachine(int n, int d, int q){
        this.nickel = n;
        this.dime = d;
        this.quarter = q;
        this.change = false;
    }
    public VendingMachine() {
        this.dime = 0;
        this.nickel = 0;
        this.quarter = 0;
        this.change = false;
    }

    /**
     * Entrypoint into the vending machine
     *
     * @param input raw input from the user
     * @throws CallManagerException
     */
    public void getInput(String input) throws CallManagerException {
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
        this.lambda();
        this.delta(n, d, q, c, w);
    }

    /**
     * Lambda function, based solely on current state, outputs what should happen
     *
     * @throws CallManagerException
     */
    private void lambda() throws CallManagerException {
        boolean coffeOnTick = false;
        int val = this.value;
        while (val >= 100) {
            //vend coffee
            System.out.println("Coffee");
            coffeOnTick = true;
            val -= 100;
        }
        if (this.change) {
            this.getChangeLambda(coffeOnTick);
        }
    }

    /**
     * Delta function. Called after the lambda function in order to change state accordingly to what it determines
     * All params are records of the input set
     *
     * @param n
     * @param d
     * @param q
     * @param c
     * @param w
     * @throws CallManagerException
     */
    private void delta(int n, int d, int q, int c, int w) throws CallManagerException {
        // The vending machine must check to see these conditions before it adjusts value
        // React to lambda conditions
        while (this.value >= 100) {
            //vend as many coffees as it can
            this.value -= 100;
        }
        if (this.change) {
            this.getChangeDelta();
        }

        // Then adjust state based on input
        int totalN = n * 5;
        int totalD = d * 10;
        int totalQ = q * 25;
        if (c > 0) {
            this.change = true;
        }
        this.nickel += n;
        this.dime += d;
        this.quarter += q;
        this.value += totalD + totalN + totalQ;
    }

    /**
     * Method that adjusts the state if the user gets change
     *
     * @throws CallManagerException
     */
    private void getChangeDelta() throws CallManagerException {
        int q = 0;
        int n = 0;
        int d = 0;
        int val = this.value;
        while (q * 25 <= val - 25) {
            q++;
        }
        val -= (q * 25);
        while (d * 10 <= val - 10) {
            d++;
        }
        val -= (d * 10);
        while (n * 5 <= val - 5) {
            n++;
        }
        val -= (n * 5);
        if (val != 0 || (q > this.quarter) || (d > this.dime) || (n > this.nickel)) {
            throw new CallManagerException("Insufficient coinage in vending machine to dispense change");
        }
        this.value = 0;
        this.quarter -= q;
        this.nickel -= n;
        this.dime -= d;
        // We want the change flag to reset after every time the user gets change
        this.change = false;

    }

    /**
     * Method that provides output if the user asks for change
     *
     * @param coffeeOnTick If the user also recieves coffee on the same tick, so value will need to be adjusted
     * @throws CallManagerException
     */
    private void getChangeLambda(boolean coffeeOnTick) throws CallManagerException {
        int q = 0;
        int n = 0;
        int d = 0;
        int val = this.value;
        if (coffeeOnTick) {
            while (val >= 100) {
                val -= 100;
            }
        }
        while (q * 25 <= val - 25) {
            q++;
        }
        val -= (q * 25);
        while (d * 10 <= val - 10) {
            d++;
        }
        val -= (d * 10);
        while (n * 5 <= val - 5) {
            n++;
        }
        val -= (n * 5);
        if (val != 0 || (q > this.quarter) || (d > this.dime) || (n > this.nickel)) {
            throw new CallManagerException("Insufficient coinage in vending machine to dispense change");
        }
        String change = "Here is your change:";
        if (q > 0) {
            change += " " + q + " quarters";
        }
        if (d > 0) {
            change += " " + d + " dimes";
        }
        if (n > 0) {
            change += " " + n + " nickels";
        }
        System.out.println(change);


    }


    @Override
    public String toString() {
        return "Current value: " + this.value + "\nNickels: " + this.nickel + " Dimes: " + this.dime + " Quarters: " + this.quarter + " Change: " + this.change;
    }
}
