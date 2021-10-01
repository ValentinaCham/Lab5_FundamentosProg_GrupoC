
package lab5_fp2_valentinacham;

public class Nave {
    private String nombre="";
    private int fila;
    private int columna;
    private boolean estado;
    private int puntos=0;

    // Metodos mutadores
    public void setNombre(String n) {
	nombre = n;
    }

    public void setFila(int f) {
	fila = f;
    }

    public void setColumna(int c) {
	columna = c;
    }

    public void setEstado(boolean e) {
	estado = e;
    }

    public void setPuntos(int p) {
	puntos = p;
    }

    // Metodos accesores
    public String getNombre() {
	return nombre;
    }

    public int getFila() {
	return fila;
    }

    public int getColumna() {
        return columna;
    }

    public boolean getEstado() {
	return estado;
    }

    public int getPuntos() {
	return puntos;
    }
    
    // Completar con otros métodos necesarios
}
