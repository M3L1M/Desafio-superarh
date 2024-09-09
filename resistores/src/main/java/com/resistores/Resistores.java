package com.resistores;

import java.util.Scanner;

public class Resistores {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String ohm = scanner.nextLine();
		ohm=ohm.split(" ")[0];
		if(ohm.contains("k")) {
			System.out.println(retornar(Float.parseFloat(ohm.split("k")[0])*1000));
		}else if(ohm.contains("M")) {
			System.out.println(retornar(Float.parseFloat(ohm.split("M")[0])*1000000));
		}else {
			System.out.println(retornar(Float.parseFloat(ohm)));
		}
	}

	public static String retornar(float ohm) {
		String ohms = ohm+ "";
		System.out.println("Aqui = "+ohm);
		String ohmsReturn = "";
		String[] cores = {
				// 0 1 2 3 4
				"preto", "marrom", "vermelho", "laranja", "amarelo",
				// 5 6 7 8 9
				"verde", "azul", "violeta", "cinza", "branco" };
		for (int i = 0; i < 2; i++) {
			ohmsReturn += cores[Integer.parseInt(ohms.substring(i, i + 1))] + " ";
		}
		ohmsReturn += cores[ohms.length()-4] + " ";

		return ohmsReturn + "dourado";
	}
}
