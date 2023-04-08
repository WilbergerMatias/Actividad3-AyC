package actividad1;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Scanner;

public class Solucion {
	
	
	protected static class MaxHeap { 
		private int[] Heap;
	    private int size;
	    private int maxsize;

	    public MaxHeap(int size) {
	        this.maxsize = size;
	        this.size = 0;
	        Heap = new int[this.maxsize + 1];
	        Heap[0] = Integer.MAX_VALUE;
	    }

	    private int parent(int pos) {
	        return pos / 2;
	    }

	    private int leftChild(int pos) {
	        return (2 * pos)  ;
	    }

	    private int rightChild(int pos) {
	        return (2 * pos) + 1;
	    }


	    private void swap(int fpos, int spos) {
	        int tmp;
	        tmp = Heap[fpos];
	        Heap[fpos] = Heap[spos];
	        Heap[spos] = tmp;
	    }

	    private void downHeapify(int pos) {
	        if (pos >= (size / 2) && pos <= size)
	            return;

	        if (Heap[pos] < Heap[leftChild(pos)] ||
	                Heap[pos] < Heap[rightChild(pos)]) {

	            if (Heap[leftChild(pos)] > Heap[rightChild(pos)]) {
	                swap(pos, leftChild(pos));
	                downHeapify(leftChild(pos));
	            } else {
	                swap(pos, rightChild(pos));
	                downHeapify(rightChild(pos));
	            }
	        }
	    }
	    private void heapifyUp(int pos) {
	        int temp = Heap[pos];
	        while(pos>0 && temp > Heap[parent(pos)]){
	            Heap[pos] = Heap[parent(pos)];
	            pos = parent(pos);
	        }
	        Heap[pos] = temp;
	    }


	    public void insert(int element) {
	        Heap[++size] = element;


	        int current = size;
	        heapifyUp(current);

	    }

	    public void print() {
	        for (int i = 1; i <= size / 2; i++) {
	            System.out.print(+ Heap[i] + ": L- " +
	                    Heap[2 * i] + " R- " + Heap[2 * i + 1]);
	            System.out.println();
	        }
	    }

	    public int extractMax() {
	        int max = Heap[1];
	        Heap[1] = Heap[size--];
	        if (size>0)
	        	downHeapify(1);
	        return max;
	    }
	    
	    public boolean isEmpty() {
	    	return size==0;
	    }
	}
	
	
	
		protected static ArrayList<Integer> Solucion;
		static MaxHeap ordenado;
		static float cotaSuperior;
		static float cotaInferior;
		static int n=0;
		static int CasosPrueba;
		public static void main(String args[]) {
			try (Scanner scanner = new Scanner(System.in)) {
				boolean solucionado = false;
				String lineCasosPrueba = scanner.nextLine();
				String [] arrCasosPrueba = lineCasosPrueba.split(" ");
				CasosPrueba = Integer.parseInt(arrCasosPrueba[0]);				
				while (CasosPrueba>0 && !solucionado) {
					String line = scanner.nextLine();
					String [] numycota = line.split(" ");
					String line2 = scanner.nextLine();
					String [] paquetes = line2.split(" ");									
					
						Solucion = new ArrayList<Integer>();
						n = Integer.parseInt(numycota[0]);
						//System.out.println(n);
						ordenado = new MaxHeap(n);
						cotaSuperior = Integer.parseInt(numycota[1]);
						//System.out.println(cotaSuperior);
						float result = cotaSuperior/2;
						cotaInferior = (int) Math.ceil(result);
						//System.out.println(cotaInferior);
				
						if (n!=0) {
							ordenar(paquetes, ordenado, n);
							solucionado = obtenerYmostrarSolucion(cotaSuperior, cotaInferior, ordenado);		
						}
						CasosPrueba--;
					}
				}							
			}
		
		
		private static boolean obtenerYmostrarSolucion(float CS, float CI, MaxHeap ordenado) {
			// TODO Auto-generated method stub
			int cargaActual=0;
			while (!ordenado.isEmpty() && CI>cargaActual) {
				int x = ordenado.extractMax();
				if (!(cargaActual+x>CS)) {
					Solucion.add(x);
					cargaActual+=x;
				}
				
			} 
			if (Solucion.isEmpty() || CI>cargaActual) {
				System.out.println("-1");
				return false;
			}else {
				System.out.println(Solucion.size());
				System.out.println(Solucion.toString());
				return true;
			}
							
		}


		private static void ordenar(String args[], MaxHeap ordenado, int n) {			
			for (int i = 0; i<n; i++) {				
				ordenado.insert(Integer.parseInt(args[i++]));
			}			
		}
		
}
