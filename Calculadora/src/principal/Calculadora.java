package principal;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import menu.Menu;
import operaciones.Operaciones;

public class Calculadora{
	private static final Logger LOGGER = Logger.getLogger(Calculadora.class.getName());
	
    public static void main(String[] args) { 
    	Handler consoleHandler = null;
        Handler fileHandler  = null;
        try{
            //Crear consoleHandler y fileHandler
            consoleHandler = new ConsoleHandler();
            fileHandler  = new FileHandler("./ficheroLog.log");
             
            //Asignar handlers al objeto LOGGER
            LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);
             
            //Establecer niveles a handlers y LOGGER
            consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);
            LOGGER.setLevel(Level.ALL);
             
           // LOGGER.config("Configuración hecha.");
            LOGGER.log(Level.SEVERE,"ERROR nivel severo");
            //Eliminamos handler de la consola
            //LOGGER.removeHandler(consoleHandler);
             
            LOGGER.log(Level.FINE, "Nivel de log cambiado a FINE");
			
        }catch(IOException exception){
            LOGGER.log(Level.SEVERE, "Ocurrió un error en FileHandler.", exception);
        }
         
        LOGGER.finer("Ejemplo con log FINE en LOGGER completado.");
         
        int resultado = 0;
        String operacion = "";
        int[] operandos = new int [2];
        
        Menu menu = new Menu();
        Operaciones operaciones = new Operaciones();
        
        do{
            operandos = menu.pedirNumeros();
            operacion = menu.menuOpciones();
            
            if (operacion.equalsIgnoreCase("+")){
                resultado = operaciones.sumar(operandos[0], operandos[1]);
                System.out.println ("Resultado: " + resultado);
            } else if (operacion.equalsIgnoreCase("-")){
                resultado = operaciones.restar(operandos[0], operandos[1]);
                System.out.println ("Resultado: " + resultado);
            } else if (operacion.equalsIgnoreCase("*")){
                resultado = operaciones.multiplicar(operandos[0], operandos[1]);
                System.out.println ("Resultado: " + resultado);
                LOGGER.log(Level.FINE,"Multiplicacion, numero 1:"+operandos[0]+",numero ");
            } else if (operacion.equalsIgnoreCase("/"))
            {try {
            	 resultado = operaciones.dividir(operandos[0], operandos[1]);
                System.out.println ("Resultado: " + resultado);
                LOGGER.log(Level.FINE,"División, numero 1:"+operandos[0]+",numero ");
            }catch(ArithmeticException e) {
            	LOGGER.log(Level.WARNING,"Division entre cero",e);
            	System.out.println("ERROR ");
            }
               
            } else if (operacion.equalsIgnoreCase("%")){
                resultado = operaciones.resto(operandos[0], operandos[1]);
                System.out.println ("Resultado: " + resultado);
            } else {
                System.out.println ("Operaci�n no v�lida");
            }
        }   while (menu.repetir());
        consoleHandler.close();
        fileHandler.close();
    }
}