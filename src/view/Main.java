package view;

import java.util.Random;
import java.util.concurrent.Semaphore;

import controller.OperacoesController;

public class Main {

	public static void main(String[] args) {

		OperacoesController[] operacoes = new OperacoesController[10];
		Semaphore semaforo = new Semaphore(2);
		
		for(int i = 0; i < 10; i++) {
			operacoes[i] = new OperacoesController(codigo(),saldo(),valor(), semaforo);
			operacoes[i].start();
		}
	}

	public static int codigo() {
		Random random = new Random();
		
		return random.nextInt();
	}
	
	public static float saldo() {
		return (float) Math.random() * 100;
	}
	
	public static float valor() {
		return (float) Math.random() * 100;
	}
}
