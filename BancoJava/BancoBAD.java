import java.io.PrintWriter; 
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.StringTokenizer;
import javax.swing.ImageIcon;



public class BancoBAD //tiene que ser public por que se va utitilizar en otros lados
{
	private PrintWriter archivoOut;
	private BufferedReader archivoIn;
	private BufferedReader archivoEntrada;
	
	private ImageIcon  arregloImages[];//solo esta apuntando a null
	private int numeroAutos; //este siempre se iniciliza con 0 
	
	public String captura (String datos)
	{
		String resultado="";
		
		
		try
		{
				//1.- Abrir el arcvhivo para guardar los datos
				
				archivoOut = new PrintWriter(new FileWriter("Clientes.txt",true)); //el true me ayuda que no se sobre escriba la linea
				
				
				//2.- Almacenar o escribir los datos en el archivo
				
				archivoOut.println(datos); 
				
				//3.-Cerrar el archivo y enviar el resultado
				archivoOut.close(); // esto es importante por que pone una marca de fin de archivo
				
				resultado = "Captura correcta de los datos";	
		}
		catch(IOException ioe)
		{
			resultado = "Error: "+ioe;
			System.out.println("Error: "+ioe);
		}
		
		return resultado;
	}
	
	public String capturar (String datos){
		String respuesta="";
		String str="";
		boolean encontrado=false;
		
		StringTokenizer st = new StringTokenizer(datos,"_");
		String clave  =st.nextToken();
		String marca  =st.nextToken();
		String tipo  =st.nextToken();
		String strPrecio  =st.nextToken();
		
		try{
			//1.-Abrir archivo de datos
			archivoEntrada = new BufferedReader (new FileReader ("Clientes.txt")) ;
			
			
			
			//2.-Buscar la Clave
			
			while(archivoEntrada.ready()&& encontrado==false)
			{
				str= archivoEntrada.readLine();
				st = new StringTokenizer(str,"_");
				String cve = st.nextToken();
				
				if(clave.equals(cve))
				{
					respuesta = "No de cuenta ya existe";
						encontrado= true;
				}
			}
			archivoEntrada.close();
			if(encontrado == false )
			{
				respuesta = captura(datos);
			}
			
		}
		catch(IOException ioe)
			{
			System.out.println("Error "+ioe);
			respuesta = "Error \n"+ioe;
		}
		return respuesta;
	}
	
	
	
	
	/*	public ImageIcon[]  obtenerImages()
		{
			return arregloImages;
		}
		
			private void crearArregloImages(String datos) //es private por que solo va ser llamado en los metdos del ad 
		{
			StringTokenizer st , st2;
			String strImagen ,strAutos,clave,marca,tipo,precio;
			int i=0;
		     //1.Crear el arreglo de images
		     arregloImages = new ImageIcon[numeroAutos];
		     
		     //2.-Obtener las imagenes jpg a partir del String Datos
		     
		     st = new StringTokenizer (datos,"\n"); //le estoy quitando el delimitador
		     
		     while(st.hasMoreTokens()) //miesntras tengas token , ponerle st2 para quitar los guiones
		     {
		     	strAutos= st.nextToken(); //ahora va tokenizar linea por linea
		     	
		     	st2 = new StringTokenizer(strAutos,"_");
		     	clave = st2.nextToken();
		     	marca = st2.nextToken();
		     	tipo = st2.nextToken();
		     	precio = st2.nextToken();
		     	
		     	strImagen = "images/"+tipo+".jpg";
		     	
		     arregloImages[i] = new ImageIcon(getClass().getResource(strImagen));
		    System.out.println(strImagen); //para ver que me esta entregando
			System.out.println(arregloImages[i]);
		     		
		     		i++;
		     }
			
		}*/
		
		public String consultar()
	
		{
			String datos="";
			
			try{
				//1.- Abir el archivo para leer los datos
				
				archivoIn = new BufferedReader(new FileReader("Clientes.txt"));
				
				//2.- Procesar los datos
				//	numeroAutos= 0;
				while(archivoIn.ready()){ //lo posiciona al principio , metodo que entrega un valor true o false cuando tiene datos que leer
				                     
				datos = datos + archivoIn.readLine() + "\n"; //readLine lee el sting hasta encontrar la marca de fin de linea \n para hacer el salto de linea
				//numeroAutos++;
				}
				
				//3.-Cerrar el archivo
				archivoIn.close();
				//mando los datos a crearArregloImages
			//	crearArregloImages(datos);	
				}
			catch (FileNotFoundException fnfe)
			{
			
			datos= "Error: " +fnfe;
			System.out.println("Error: "+fnfe);
		    }
		    
		    catch(IOException ioe)
		    	
		    {
		    	
			    datos = "Error: "+ioe;
				System.out.println("Error: "+ioe);
		    }
				
		
			
			return datos;
			
		}
		
		public String consultarClave(String auto)
		{
			
				String datos = "";
				String strCliente="";
				StringTokenizer st;
				String clave, nombre, tipo,precio;
				
				boolean encontrado=false;
				
				try 
				{
					
				
				//1.-Abrir el archivo para leer
				archivoIn = new BufferedReader ( new FileReader("Clientes.txt"));
				
				
				
				//2.-Buscar libros de la editorial indicada
				//	numeroAutos=0;
				while(archivoIn.ready()) //el ready es un tipo boolenao que te avisi cuando hay una marca de fin de archivo
					{
					strCliente = archivoIn.readLine();
					 st = new StringTokenizer(strCliente,"_"); // el _ es el delimitador , lo que toma por default es el espacio en blanco
					 clave = st.nextToken();
					 nombre = st.nextToken();
					 tipo = st.nextToken();
					 precio = st.nextToken();
					 
					 if (clave.equals(auto))
					 {
					 	datos= datos + strCliente + "\n";
					 	encontrado=true;
					 //		numeroAutos++;
					 }
					 
					}
				
				//3.- Cerrar el archivo
				if (!encontrado)
					datos = "NOT_FOUND";
			//	else //es else para que no cree el arreglo con datos vacios por que truena
			//	crearArregloImages(datos); //
			
			}
			catch (FileNotFoundException fnfe)
			{
					datos= "NOT_FOUND";
					System.out.println("Error: "+fnfe);
			}
			 catch(IOException ioe)
		    	
		    {
		    	
			    datos = "NOT_FOUND";
				System.out.println("Error: "+ioe);
		    }
				
			return datos;
		}
		
		
		public String consultarMarca(String mar)
		{
			
			
				String datos = "";
				String strCliente="";
				StringTokenizer st;
				String clave, nombre, tipo,precio;
				
				boolean encontrado=false;
				
				try 
				{
					
				
				//1.-Abrir el archivo para leer
				archivoIn = new BufferedReader ( new FileReader("Clientes.txt"));
				
				
				
				//2.-Buscar libros de la editorial indicada
				numeroAutos=0;
				while(archivoIn.ready()) //el ready es un tipo boolenao que te avisi cuando hay una marca de fin de archivo
					{
					 strCliente = archivoIn.readLine();
					 st = new StringTokenizer(strCliente,"_"); // el _ es el delimitador , lo que toma por default es el espacio en blanco
					 clave = st.nextToken();
					nombre = st.nextToken();
					 tipo = st.nextToken();
					 precio = st.nextToken();
					 
					 if (tipo.equals(mar))
					 {
					 	datos= datos + strCliente + "\n";
					 	encontrado=true;
					    numeroAutos++;
					 }
					 
					}
				
				//3.- Cerrar el archivo
					archivoIn.close();
				if (!encontrado)
					datos = "NOT_FOUND";
			//	else //es else para que no cree el arreglo con datos vacios por que truena
			//	crearArregloImages(datos); //
			
			}
			catch (FileNotFoundException fnfe)
			{
					datos= "NOT_FOUND";
					System.out.println("Error: "+fnfe);
			}
			 catch(IOException ioe)
		    	
		    {
		    	
			    datos = "NOT_FOUND";
				System.out.println("Error: "+ioe);
		    }
				
			return datos;
		
			
			
		}
		
		public String cotizarAuto(String datos , int plazo){
			
			String cotiza = "COTIZACION AUTOMOVIL: \n";
			String strInteres="";
			float precioTotal=0, mensualidad=0,interes=0;
			
		StringTokenizer st = new StringTokenizer(datos,"_");
		String clave  =st.nextToken();
		String nombre  =st.nextToken();
		String tipo  =st.nextToken();
		float precio = Float.parseFloat(st.nextToken());
		
		if (plazo == 12)
		{
			interes = (float)(0.10);
			precioTotal = precio * interes + precio;
			mensualidad = precioTotal/plazo;
			strInteres = "10 %";
			
		}
		
			if (plazo == 24)
		{
			interes = (float)(0.15);
			precioTotal = precio * interes + precio;
			mensualidad = precioTotal/plazo;
			strInteres = "15 %";
			
		}
			if (plazo == 48)
		{
			interes = (float)(0.20);
			precioTotal = precio * interes + precio;
			mensualidad = precioTotal/plazo;
			strInteres = "20 %";
		}
		
		
	   cotiza=cotiza+"Marca: "+nombre+"\nTipo: "+tipo+"\nPrecio Contado: "+precio+"\nPlazo"+plazo+"Meses\nIntereses: "+strInteres+"\nPrecio con interes"+precioTotal+"\nMensualidad: "+mensualidad;
	
			
			
			return cotiza;
		}
	
	
	}
