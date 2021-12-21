import java.util.Arrays;
/**
 *  Código que testea la clase ListaNumeros
 */
public class TestListaNumeros
{

    /**
     * Punto de entrada a la aplicación
     * Contiene código para probar los métodos de ListaNumeros
     */
    public static void main(String[] args) {

        ListaNumeros lista = new ListaNumeros(20);

        testAdd(lista);
        testToString(lista);

        testSegundoMaximo(lista);

        System.out.println();
        lista.vaciarLista();
        lista.addElemento(9);
        testToString(lista);

        testSegundoMaximo(lista);
        lista.addElemento(9);
        testToString(lista);
        testSegundoMaximo(lista);    
        System.out.println();

        separador();

        lista.vaciarLista();
        testAdd(lista);

        testSegundosPrincipio(lista);
        separador();

        int numero = 2;
        testBuscarBinario(lista, numero);       
        numero = 21;
        testBuscarBinario(lista, numero); 

        separador();

        testDetectarEstrellas();

    }

    public static void testAdd(ListaNumeros lista) {
        int[] valores = {21, -5, 28, -7, 28, 77, 77, -17, 21, 15, 28, 28, 77};
        //int[] valores = {21, -5, 28, -7, 77};
        //int[] valores = {77, 21};
        //int[] valores = {21};
        //int[] valores = {21, 21, 21, 21};
        for (int i = 0; i < valores.length; i++) {
            lista.addElemento(valores[i]);
        }

    }

    public static void testToString(ListaNumeros lista) {
        System.out.println(lista.toString());
    }

    public static void testSegundoMaximo(ListaNumeros lista) {        
        int segundo = lista.segundoMaximo();
        if (segundo == Integer.MIN_VALUE) {
            System.out.println("No hay segundo máximo\n");
        }
        else {
            System.out.println("El valor segundo máximo es : " + segundo);
        }

    }

    public static void testSegundosPrincipio(ListaNumeros lista) {
        System.out.println("Después de colocar los segundos máximos al principio");
        boolean exito = lista.segundosMaximosAlPrincipio();
        if (!exito) {
            System.out.println("No había segundo máximo\n");
        }
        else {
            System.out.println(lista.toString());

        }

    }

    public static void testBuscarBinario(ListaNumeros lista, int numero) {
        System.out.println("Haciendo una búsqueda binaria");
        System.out.println(lista.toString());
        System.out.println("El valor " + numero + " existe en lista? " + (lista.buscarBinario(numero) != -1));
    }

    public static void testDetectarEstrellas() {
        System.out.println("Detectando estrellas en el espacio");
        int[][] brillos = ListaNumeros.crearBrillos();

        for (int[] f : brillos) {
            Arrays.stream(f).forEach(num -> System.out.printf("%7d", num));
            System.out.println();
        }

        System.out.println();
        boolean[][] estrellas = ListaNumeros.detectarEstrellas(brillos);
        for (boolean[] f : estrellas) {
            java.util.stream.IntStream.range(0, f.length)
            .mapToObj(i -> f[i]).forEach(n -> System.out.printf("%7b", n));
            System.out.println();
        }
    }

    public static void separador() {
        System.out.println("\n*************************************************\n");
    }
}