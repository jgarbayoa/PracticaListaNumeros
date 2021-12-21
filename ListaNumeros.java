/**
 * Un objeto de esta clase
 * guarda una lista de números enteros
 * 
 * La clase incluye una serie de métodos de instancia
 * para hacer operaciones sobre la lista
 * y dos  métodos estáticos para trabajar con
 * arrays de dos dimensiones
 *
 * @author - Juan Garbayo
 */
import java.util.Arrays;
import java.util.Random;
public class ListaNumeros {
    public static final int DIMENSION = 10;
    public static final int ANCHO_FORMATO = 6;
    public static final char CAR_CABECERA = '-';

    private static final Random generador = new Random();

    private static int[] lista;

    private static int pos;

    /**
     * Constructor de la clase ListaNumeros
     * Crea e inicializa adecuadamente los
     * atributos
     *
     * @param n el tamaño máximo de la lista
     */
    public ListaNumeros(int n) {
        lista = new int[n];
        for (int i = 0; i < lista.length; i++) {
            lista[i] = Integer.MIN_VALUE;
        }
        pos = 0;
    }

    /**
     * Añade un valor al final de la lista 
     * siempre que no esté completa
     *
     * @param numero el valor que se añade.  
     * @return true si se ha podido añadir, false en otro caso
     */
    public boolean addElemento(int numero) {
        if (pos < lista.length) {
            lista[pos] = numero;
            pos++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return true si la lista está completa, false en otro caso
     * Hacer sin if
     */
    public boolean estaCompleta() {
        return pos == lista.length;
    }

    /**
     * @return true si la lista está vacía, false en otro caso.
     * Hacer sin if
     */
    public boolean estaVacia() {
        return pos == 0;
    }

    /**
     * @return el nº de elementos realmente guardados en la lista
     */
    public int getTotalNumeros() {
        if (pos == 0) {
            return 0;
        } else {
            return pos;
        }
    }

    /**
     * Vacía la lista
     */
    public void vaciarLista() {
        for (int i = 0; i < pos; i++) {
            lista[i] = 0;
        }
    }

    /**
     * @return una cadena con representación textual de la lista 
     * (leer enunciado)
     * 
     * Si la lista está vacía devuelve ""
     */
    public String toString() {
        if (pos == 0) {
            return "";
        } else {
            String str = "";
            str += pintarCabecera();
            str += pintarNumeros();
            str += pintarCabecera();
            return str; 
        }
    }

    private String pintarNumeros() {
        String numero = "";
        for (int i = 0; i < getTotalNumeros(); i++) {
            numero += Utilidades.centrarNumero(lista[i], ANCHO_FORMATO);
        }
        numero += "\n";
        return numero;
    }

    private String pintarCabecera() {
        String guiones = "";
        for (int i = 0; i < getTotalNumeros(); i++) {
            for (int j = 0; j < ANCHO_FORMATO; j++) {
                guiones += CAR_CABECERA;
            }
        }
        guiones += "\n";
        return guiones;
    }

    /**
     * Mostrar en pantalla la lista
     */
    public void escribirLista() {
        System.out.println(this.toString());
    }

    /**
     *  
     * @return el segundo valor máximo en la lista
     * Cuando no haya un segundo máximo el método 
     * devolverá el valor Integer.MIN_VALUE
     * 
     * Si lista = {21, -5, 28, -7, 28, 77, 77, -17, 21, 15, 28, 28, 77} se devuelve 28
     * Si lista = {21, -5, 28, -7, 77} se devuelve 28
     * Si lista = {77, 21} se devuelve 21
     * Si lista = {21} se devuelve Integer.MIN_VALUE
     * Si lista = {21, 21, 21, 21} se devuelve Integer.MIN_VALUE
     * 
     * No se puede usar ningún otro array auxiliar ni hay que ordenar previamente
     * la lista
     */
    public int segundoMaximo() {       
        int primerMax = Integer.MIN_VALUE;
        int segundoMax = Integer.MIN_VALUE;
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] > primerMax) {
                segundoMax = primerMax;
                primerMax = lista[i];
            } else if (lista[i] > segundoMax && lista[i] < primerMax) {
                segundoMax = lista[i];
            }
        }
        return segundoMax;
    }

    /**
     * El método coloca los valores que son segundos máximos al principio de
     * la lista respetando el orden de aparición del resto de elementos
     * 
     * No se puede usar ningún otro array auxiliar ni hay que ordenar previamente
     * la lista
     * 
     * Si lista = {21, -5, 28, -7, 28, 77, 77, -17, 21, 15, 28, 28, 77} 
     * lista queda  {28, 28, 28, 28, 21, -5, -7, 77, 77, -17, 21, 15, 77} y se devuelve true
     * 
     * Si lista = {77, 21} lista queda {21, 77} y se devuelve true
     * Si lista = {21} lista queda igual y se devuelve false
     * Si lista = {21, 21, 21, 21} lista queda igual y se devuelve false
     * 
     * @return true si se han colocado los segundos máximos
     *          false si no se han colocado los segundos máximos porque no había ninguno
     */
    public boolean segundosMaximosAlPrincipio() {
        int vecesSegundoMaximo = 0;
        int siguiente = lista.length - 1;
        if (segundoMaximo() == Integer.MIN_VALUE) {
            return false;
        } else {
            for (int i = 0; i < lista.length; i++) {
                if (lista[i] == segundoMaximo()) {
                    vecesSegundoMaximo++;
                }
            }
            for (int i = lista.length - 1; i >= 0; i--) {
                if (lista[i] != segundoMaximo()) {
                    lista[siguiente] = lista[i];
                    siguiente--;
                }
            }
            for (int i = 0; i < vecesSegundoMaximo; i++) {
                lista[i] = segundoMaximo();
            }
            return true;
        }
    }

    /**
     * @param numero búsqueda binaria de  numero en lista
     * @return devuelve -1 si no se encuentra o la posición en la que aparece
     *  
     * El array original lista no se modifica
     * Para ello crea  previamente una copia
     * de lista y trabaja  con la copia
     *  
     * Usa exclusivamente métodos de la clase Arrays
     */
    public int buscarBinario(int numero) {
        int[] copia = Arrays.copyOf(lista, lista.length);
        if (Arrays.binarySearch(copia, numero) >= 0) {
            for (int pos = 0; pos < lista.length; pos++) {
                if (lista[pos] == numero) {
                    return pos;
                }
            }
        } return -1;
    }

    /**
     * 
     * @return devuelve un array bidimensional de enteros de tamaño DIMENSION
     * inicializado con valores aleatorios entre 0 y 10 inclusive
     * 
     * Estos valores van a representar el brillo de una zona del espacio
     * 
     */
    public static int[][] crearBrillos() {
        int[][] Brillos = new int[DIMENSION][DIMENSION];
        for (int i = 0; i < Brillos.length; i++) {
            for (int j = 0; j < Brillos[i].length; j++) {
                Brillos[i][j] = generador.nextInt(10);
            }
        }
        return Brillos;
    }

    /**
    * @param  un array bidimensional brillos 
    * @return un nuevo array bidimensional de valores booleanos
    * de las mismas dimensiones que el array brillos con
    * valores true en las posiciones donde hay estrellas
    * 
    * Una posición f,c del array brillos es una estrella 
    * si la suma del valor de los brillos de sus cuatro vecinos 
    * (arriba, abajo, derecha e izquierda) es mayor que 30
    * 
    * Nota -  No hay estrellas en los bordes del array brillos
    */
    public static boolean[][] detectarEstrellas(int[][] brillos) {
        boolean[][] arrayEstrellas = new boolean[DIMENSION][DIMENSION];
        int arriba = 0;
        int abajo = 0;
        int derecha = 0;
        int izquierda = 0;
        int total = 0;
        for (int i = 1; i < brillos.length-1; i++) {
            for (int j = 1; j < brillos[i].length-1; j++) {
                arriba = brillos[i-1][j];
                abajo = brillos[i+1][j];
                derecha = brillos[i][j+1];
                izquierda = brillos[i][j-1];
                total = arriba + abajo + izquierda + derecha;
                if (total > 30) {
                    arrayEstrellas[i][j] = true;
                } else {
                    arrayEstrellas[i][j] = false;
                }
            }
        }
        return arrayEstrellas;
    }
}
