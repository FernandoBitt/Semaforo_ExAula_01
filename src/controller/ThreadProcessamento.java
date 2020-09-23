package controller;

import java.util.concurrent.Semaphore;

public class ThreadProcessamento extends Thread{
	
	private int id;
	private Semaphore semaforo;
	private int quantOp;
	private int time1, time2, time3;
	
	public ThreadProcessamento(int id, Semaphore semaforo) {
		this.id=id;
		this.semaforo=semaforo;
	}

	@Override
	public void run() {
		calculo();
		try {
			semaforo.acquire();
			transacao();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		
	}

	private void calculo() {
		
		if(id % 3==1) {
			quantOp=2;
			time1=801;
			time2=200;
		}else if (id % 3 ==2) {
			quantOp=3;
			time1=1001;
			time2=500;
		}else if (id %3 ==0) {
			quantOp=3;
			time1=1001;
			time2=1000;
		}
			
		int cont = 1;
		while (cont <= quantOp) {
			int tempo = (int) (Math.random() * time1) + time2;
			System.out.println("Calculando - Thread id: " + id);
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread id: " + id + " Calculado");
			cont++;
		}
	}

	private void transacao() {
		if(id % 3==1) {
			quantOp=2;
			time3=1001;
		}else if (id % 3 ==2) {
			quantOp=3;
			time3=1501;
		}else if (id %3 ==0) {
			quantOp=3;
			time3=1501;
		}
		
		int cont = 1;
		while (cont <= quantOp) {
			int tempo = (int) (Math.random() * time3);
			System.out.println("Transação BD - Thread id: " + id);
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread id: " + id + " Transação finalizada");
			cont++;
		}
	}
}
