package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class OperacoesController extends Thread{

	private static boolean permiteSaque = true;
	private static boolean permiteDeposito = true;
	private Semaphore semaforo;
	private int codigo;
	private float saldo;
	private float valor;
	
	public OperacoesController(int codigo, float saldo, float valor, Semaphore semaforo) {
		this.codigo = codigo;
		this.saldo = saldo;
		this.valor = valor;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		
		Random random = new Random();
		
		
		for(int i = 0; i < 2; i++) {
			int opc = random.nextInt(3);
		
			switch(opc) {
			case 1:
				try {
					semaforo.acquire();
					Saque(codigo, saldo, valor);
				} 
				catch (Exception e) {
				
				}
				semaforo.release();
				break;
		
			case 2:
				try {
					semaforo.acquire();
					Deposito(codigo, saldo, valor);
				} 
				catch (Exception e) {
				
				}
				semaforo.release();
				break;
			}
			}
		
	}
	
	public void Saque(int codigo, float saldo, float valor) {
		
		if(permiteSaque) {
			System.out.println("Thread #" + getId() + "-- Saque feito no valor de: " + valor);
		}
		
		permiteSaque = false;
		permiteDeposito = true;
	}
	
	public void Deposito(int codigo, float saldo, float valor) {
		
		if(permiteDeposito) {
			System.out.println("Thread #" + getId() + " -- Deposito feito no valor de: " + valor);
		}
		
		permiteDeposito = false;
		permiteSaque = true;
	}
}
