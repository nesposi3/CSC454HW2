package com.nesposi3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws CallManagerException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Set initial number of nickels, dimes, and quarters");
        String initial = sc.nextLine();
        String[] initials = initial.split(" ");
        int n = Integer.parseInt(initials[0]);
        int d = Integer.parseInt(initials[1]);
        int q = Integer.parseInt(initials[2]);

        VendingMachine vendingMachine = new VendingMachine(n,d,q);
        while(true){
            System.out.println(vendingMachine.toString());
            String command = sc.nextLine();
            if(command.equalsIgnoreCase("exit")){
                break;
            }else{
                vendingMachine.getInput(command);
            }

        }
    }
}
