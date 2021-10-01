
package lab5_fp2_valentinacham;

import java.util.Scanner;

public class DemoBatalla {
    public static void main(String[] args) {
	Nave[] misNaves = new Nave[10];
	Scanner sc = new Scanner(System.in);

	String nomb;
        int col;
	int fil, punt;
	boolean est;

	for (int i = 0; i < misNaves.length; i++) {
            System.out.println("Nave " + (i + 1));
            System.out.print("Nombre: ");
            nomb = sc.next();
            System.out.print("Fila: ");
            fil = sc.nextInt();
            System.out.print("Columna: ");
            col = sc.nextInt();
            System.out.print("Estado: ");
            est = sc.nextBoolean();
            System.out.print("Puntos: ");
            punt = sc.nextInt();

            // Se crea un objeto Nave y se asigna su referencia a misNaves
            misNaves[i] = new Nave();
            misNaves[i].setNombre(nomb);
            misNaves[i].setFila(fil);
            misNaves[i].setColumna(col);
            misNaves[i].setEstado(est);
            misNaves[i].setPuntos(punt);
	}

	System.out.println("\nNaves creadas:");
        mostrarNaves(misNaves);
        mostrarPorNombre(misNaves);
        mostrarPorPuntos(misNaves);
        System.out.println("\nNave con mayor número de puntos: " + mostrarMayorPuntos(misNaves));
        
        System.out.println("\nNombre de la nave buscada: ");
        String nombre = sc.next();
        int pos = busquedaLinealNombre(misNaves,nombre);
        if (pos==-1)
            System.out.println("No encontrado");
        else {
            System.out.println("Los datos de la nave son: ");
            System.out.printf("%10s%20d%20d%10d\n", misNaves[pos].getNombre(), misNaves[pos].getFila(), misNaves[pos].getColumna(), misNaves[pos].getPuntos());
        }
        
        //Espaciado
        System.out.println();
            
	ordenarPorPuntosBurbuja(misNaves);
        mostrarNaves(misNaves);
        ordenarPorNombreBurbuja(misNaves);
        mostrarNaves(misNaves);
        
        System.out.println("\nNombre de la nave buscada: ");
        nombre = sc.next();
        //mostrar los datos de la nave con dicho nombre, mensaje de “no encontrado” en caso contrario
        pos = busquedaBinariaNombre(misNaves,nombre);
        if (pos==-1)
            System.out.println("No encontrado");
        else {
            System.out.println("Los datos de la nave son: ");
            System.out.printf("%10s%20d%20d%10d\n", misNaves[pos].getNombre(), misNaves[pos].getFila(), misNaves[pos].getColumna(), misNaves[pos].getPuntos());
        }
        ordenarPorPuntosSeleccion(misNaves);
        mostrarNaves(misNaves);
        ordenarPorPuntosInsercion(misNaves);
        mostrarNaves(misNaves);
        ordenarPorNombreSeleccion(misNaves);
        mostrarNaves(misNaves);
        ordenarPorNombreInsercion(misNaves);
        mostrarNaves(misNaves);
    }

	// Método para mostrar todas las naves
	public static void mostrarNaves(Nave[] flota) {
            System.out.printf( "%10s%20s%20s%10s\n", "Nombre", "Fila", "Columna", "Puntos" );
            for( int contador = 0; contador < 10; contador++)
                System.out.printf("%10s%20d%20d%10d\n", flota[contador].getNombre(), flota[contador].getFila(), flota[contador].getColumna(), flota[contador].getPuntos()); 
	}

	// Método para mostrar todas las naves de un nombre que se pide por teclado
	public static void mostrarPorNombre(Nave[] flota) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Ingrese el nombre de la nave que se quiere buscar: ");
            String nombreBuscar = sc.next();
            System.out.printf( "%10s%20s%20s%10s\n", "Nombre", "Fila", "Columna", "Puntos" );
            for( int contador = 0; contador < 10; contador++)
                if (nombreBuscar.equalsIgnoreCase(flota[contador].getNombre()))
                 System.out.printf("%10s%20d%20d%10d\n", flota[contador].getNombre(), flota[contador].getFila(), flota[contador].getColumna(), flota[contador].getPuntos());
	}
        
        //ARCHIVO DEL ANTERIOR LABORATORIO PARA ORDENARLOS ALEATORIAMENTE
        public static void mostrarAleatorio(Nave[] flota) {
            int a, b;
            for (int i=0; i<12; i++){
                String almacen;
                int almacenPunto;
                a = (int) (Math.random()*10);
                b = (int) (Math.random()*10);
                almacen = flota[a].getNombre();
                almacenPunto = flota[a].getPuntos();
                flota[a].setNombre(flota[b].getNombre());
                flota[b].setNombre(almacen);
                flota[b].setPuntos(almacenPunto);
            }
            System.out.printf( "%10s%20s%20s%10s\n", "Nombre", "Fila", "Columna", "Puntos" );
            for( int contador = 0; contador < 10; contador++)
                System.out.printf("%10s%20d%20d%10d\n", flota[contador].getNombre(), flota[contador].getFila(), flota[contador].getColumna(), flota[contador].getPuntos());
	}
        
	// Método para mostrar todas las naves con un número de puntos inferior o igual
	// al número de puntos que se pide por teclado
	public static void mostrarPorPuntos(Nave[] flota) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Ingrese puntaje máximo: ");
            int puntajeMax = sc.nextInt();
            System.out.printf( "%10s%20s%20s%10s\n", "Nombre", "Fila", "Columna", "Puntos" );
            for( int contador = 0; contador < flota.length; contador++)
                if (flota[contador].getPuntos()<=puntajeMax)
                    System.out.printf("%10s%20d%20d%10d\n", flota[contador].getNombre(), flota[contador].getFila(), flota[contador].getColumna(), flota[contador].getPuntos()); 
	}

	// Método que muestra las naves de forma matricial
	public static void mostrarMatrizDeNaves(Nave[] flota) {
            Nave MatrizOrdenada[][] = DemoBatalla.Bidimensional(flota);
            System.out.println("\nMATRIZ");
            for(int i=0;i<10;i++){
                    for (int j=0;j<10;j=j+10)
                    {
    			System.out.printf( "%-8s%-2d%-2d%-8s%-2d%-2d%-8s%-2d%-2d%-8s%-2d%-2d%-8s%-2d%-2d%-8s%-2d%-2d%-8s%-2d%-2d%-8s%-2d%-2d%-8s%-2d%-2d%-8s%-2d%-2d\n",
                                MatrizOrdenada[i][j].getNombre(),MatrizOrdenada[i][j].getFila(),MatrizOrdenada[i][j].getColumna(),
                                MatrizOrdenada[i][j+1].getNombre(),MatrizOrdenada[i][j+1].getFila(),MatrizOrdenada[i][j+1].getColumna(),
                                MatrizOrdenada[i][j+2].getNombre(),MatrizOrdenada[i][j+2].getFila(),MatrizOrdenada[i][j+2].getColumna(),
                                MatrizOrdenada[i][j+3].getNombre(),MatrizOrdenada[i][j+3].getFila(),MatrizOrdenada[i][j+3].getColumna(),
                                MatrizOrdenada[i][j+4].getNombre(),MatrizOrdenada[i][j+4].getFila(),MatrizOrdenada[i][j+4].getColumna(),
                                MatrizOrdenada[i][j+5].getNombre(),MatrizOrdenada[i][j+5].getFila(),MatrizOrdenada[i][j+5].getColumna(),
                                MatrizOrdenada[i][j+6].getNombre(),MatrizOrdenada[i][j+6].getFila(),MatrizOrdenada[i][j+6].getColumna(),
                                MatrizOrdenada[i][j+7].getNombre(),MatrizOrdenada[i][j+7].getFila(),MatrizOrdenada[i][j+7].getColumna(),
                                MatrizOrdenada[i][j+8].getNombre(),MatrizOrdenada[i][j+8].getFila(),MatrizOrdenada[i][j+8].getColumna(),
                                MatrizOrdenada[i][j+9].getNombre(),MatrizOrdenada[i][j+9].getFila(),MatrizOrdenada[i][j+9].getColumna());
                    }   
    		System.out.print("\n");
            }
	}
        
    public static Nave[][] Bidimensional(Nave[] flota){
        Nave MatrizOrdenada[][] = new Nave[10][10];
        Nave encontrar;
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                for (int k=0; k<10;k++){
                    encontrar = flota[k];
                    if (encontrar.getFila()==i && encontrar.getColumna()==j)
                        MatrizOrdenada[i][j] = encontrar;
                    if (k==9 && MatrizOrdenada[i][j]==null){
                        MatrizOrdenada[i][j] = new Nave();
                        MatrizOrdenada[i][j].setFila(0);
                        MatrizOrdenada[i][j].setColumna(0);
                        MatrizOrdenada[i][j].setEstado(true);
                    }
                }
            }
        }
        return MatrizOrdenada;
    }
    
    //Método que devuelve la Nave con mayor número de Puntos
    public static String mostrarMayorPuntos(Nave[] flota){
        Nave Mayor = flota[0];
        for (int i=1; i<flota.length;i++){
            if (Mayor.getPuntos()< flota[i].getPuntos()){
                Mayor = flota[i];
            }  
        }
        return "Su nombre es " +  Mayor.getNombre() + ". Pertenece a la fila " + Mayor.getFila() + " y la comlumna " + Mayor.getColumna() + ". La cantidad de puntos es: " + Mayor.getPuntos();
    }   
        
    //Método para buscar la primera nave con un nombre que se pidió por teclado
    public static int busquedaLinealNombre(Nave[] flota, String s){
        for (int i=0;i<flota.length;i++){
            if ((flota[i].getNombre()).equalsIgnoreCase(s))
                return i;
        }
        return -1;
    }
    
    //Método que ordena por número de puntos de menor a mayor
    public static void ordenarPorPuntosBurbuja(Nave[] flota){
        System.out.println("ORDENAR POR PUNTOS: Burbuja");
        for (int i=1; i<flota.length;i++)
            for(int j=0;j<flota.length-i;j++)
                if((flota[j].getPuntos())>(flota[j+1].getPuntos()))
                    intercambiar(flota, j, j+1);
    }
    
    //UN INTERCAMBIAR PARA LOS ORDENAMIENTO BURBUJA
    private static void intercambiar(Nave[] flota, int i, int j){
        Nave temp = new Nave();
        temp = flota[i];
        flota[i] = flota[j];
        flota[j] = temp;
    }
    
    //Método que ordena por nombre de A a Z
    public static void ordenarPorNombreBurbuja(Nave[] flota){
        for (int i=1; i<flota.length;i++)
            for(int j=0;j<flota.length-i;j++)
                if(0<(flota[j].getNombre()).compareToIgnoreCase(flota[j+1].getNombre()))
                    intercambiar(flota, j, j+1);
    }
    //Método para buscar la primera nave con un nombre que se pidió por teclado
    public static int busquedaBinariaNombre(Nave[] flota, String s){
        int alta, baja, media;
        baja=0;
        alta=flota.length-1;
        while (baja<=alta){
            media = (alta+baja)/2;
            if (0==s.compareTo(flota[media].getNombre()))
                return media;
            else if (0<s.compareTo(flota[media].getNombre()))
                baja = media+1;
            else
                alta=media+1;
        }
        return -1;
    }
    
    //Método que ordena por número de puntos de menor a mayor
    public static void ordenarPorPuntosSeleccion(Nave[] flota){
        //Encontrar el menor
        Nave menor = new Nave();
        int indice=0;
        for (int i=0; i<flota.length;i++){
            //Determinar el menor
            menor = flota[i];
            for (int j=i; j<flota.length;i++)
                if (menor.getPuntos()>flota[j].getPuntos()){
                    menor = flota[j];
                    indice = j;
                }
            intercambiar(flota, indice, i);
        }
    }
    
    //Método que ordena por nombre de A a Z
    public static void ordenarPorNombreSeleccion(Nave[] flota){
        //Encontrar el menor
        Nave menor = new Nave();
        int indice=0;
        for (int i=0; i<flota.length;i++){
            //Determinar el menor
            menor = flota[i];
            for (int j=i; j<flota.length;i++)
                if (0<(menor.getNombre()).compareToIgnoreCase(flota[j].getNombre())){
                    menor = flota[j];
                    indice = j;
                }
            intercambiar(flota, indice, i);
        }
    }
    
    //Método que muestra las naves ordenadas por número de puntos de mayor a menor
    public static void ordenarPorPuntosInsercion(Nave[] flota){
        int i, j;
        Nave ordenando;
        for (i = 1; i < flota.length; i++){
            ordenando = flota[i];
            j = i - 1;
            //Para ir retrocediendo hasta que el puntaje ordenado sea mayor
            //que los ordenados
            while ((j >= 0) && (ordenando.getPuntos() < flota[j].getPuntos())){
                flota[j + 1] = flota[j];
                j--;
            }
        flota[j + 1] = ordenando;
        }
    }
    
    //Método que muestra las naves ordenadas por nombre de Z a A
    public static void ordenarPorNombreInsercion(Nave[] flota){
        int i, j;
        Nave ordenando;
        for (i = 1; i < flota.length; i++){
            ordenando = flota[i];
            j = i - 1;
            //Para ir retrocediendo hasta que el nombre anterior
            //sea lexicográficamente primero
            while ((j >= 0) && (0<ordenando.getNombre().compareToIgnoreCase(flota[j].getNombre()))){
                flota[j + 1] = flota[j];
                j--;
            }
        flota[j + 1] = ordenando;
        }
    }
}
