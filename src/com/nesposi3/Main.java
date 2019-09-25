package com.nesposi3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws CallManagerException {
        Scanner sc = new Scanner(System.in);
        VendingMachine vendingMachine = new VendingMachine();
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
