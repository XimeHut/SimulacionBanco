import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import java.util.StringTokenizer;

public class BancoBGUI extends JFrame implements ActionListener
{
    private JTextField tfClave, tfNombre, tfTipo,tfPrecio;
    private JButton    bCapturar, bConsultar, bConsultarTipo,bConsultarNo ,bCotizar,bSalir;
    private JTextArea  taDatos;
    private JPanel panel1, panel2;
    
    private BancoBAD bancoad = new BancoBAD();
    
    public BancoBGUI()
    {
        super("BANCO");
        
        // 1. Crear objetos de los atributos
        tfClave    = new JTextField();
        tfNombre     = new JTextField();
        tfTipo       = new JTextField();
        tfPrecio     = new JTextField();
        bCapturar   = new JButton("Capturar Cliente");
        bConsultar  = new JButton("Consultar Clientes");
        bConsultarTipo = new JButton("Consultar Tipo de Cuenta");
        bConsultarNo = new JButton ("Consultar No.Cuenta");
        //bCotizar=      new JButton ("Cotizar Auto");
        bSalir      = new JButton("Exit");
        taDatos     = new JTextArea(10,30);
        panel1      = new JPanel();
        panel2      = new JPanel();
        
        // 1.2 Adicionar addActionListener a los JButtons
        bCapturar.addActionListener(this);
        bConsultar.addActionListener(this);
        bConsultarTipo.addActionListener(this);
        bConsultarNo.addActionListener(this);
       // bCotizar.addActionListener(this);
        bSalir.addActionListener(this);
        
        // 2. Definir el Layout de los JPanels
        panel1.setLayout(new GridLayout(8,2));
        panel2.setLayout(new FlowLayout());
        
        // 3. Colocar los objetos en los JPanels
        panel1.add(new JLabel("NO. DE CUENTA: "));
        panel1.add(tfClave);
        panel1.add(new JLabel("NOMBRE DEL CLIENTE: "));
        panel1.add(tfNombre);
        panel1.add(new JLabel("TIPO DE CUENTA: "));
        panel1.add(tfTipo);
        panel1.add(new JLabel("SALDO:"));
        panel1.add(tfPrecio);
        panel1.add(bCapturar);
        panel1.add(bConsultar);
        panel1.add(bConsultarTipo);
        panel1.add(bConsultarNo);
        //panel1.add(bCotizar);
        panel1.add(bSalir);
        
        panel2.add(panel1);
        panel2.add(new JScrollPane(taDatos));
        
        // 4. Adicionar los JPanels al JFrame
        add(panel2);
        
        // 5. Visualizar el JFrame
        setSize(400,400);
        setVisible(true);
    }
     /*  public JPanel getPanel2(){ 
    	    return this.panel2;                   
    }*/
    
    
    private String obtenerDatos()//es privado por que solo lo va utilizar el GUI
    {
    	String datos ="";
    	
    	String clave=tfClave.getText();
    	String nombre=tfNombre.getText();
    	String tipo=tfTipo.getText();
    	String precio=tfPrecio.getText();
    	
    	if (clave.equals("") || nombre.isEmpty() || tipo.isEmpty() || precio.isEmpty()) //is empty me entrega un valor true o false , para checar si el usuario si anoto algo , comillas comillas pegado es importante
    		datos = "Vacio";                                            
    		
    	else 
    		
    	{
    		try{
    		int n = Integer.parseInt(precio);
    		datos= clave+"_"+nombre+"_"+tipo+"_"+precio;	
    		}
    		catch(NumberFormatException nfe)
    		{
    			datos = "NO_NUMERICO";
    		}
    		
    	}
    		
    
    	
    	return datos;
    }
    
    private void desplegarDatos(String datos)
    {
    	StringTokenizer st = new StringTokenizer(datos,"_");
    	
    	tfClave.setText(st.nextToken());
    	tfNombre.setText(st.nextToken());
    	tfTipo.setText(st.nextToken());
    	tfPrecio.setText(st.nextToken());
    	
    }
     
    
    public void actionPerformed(ActionEvent e)
    {
        String datos, resultado;
        
        if(e.getSource() == bCapturar)
        	
        {
        	//1.- Obtener los datos de los JTextFields
        	
        	datos = obtenerDatos();
        	
        	//2.-Capturar los datos a traves del objeto BiblioAD
        	if(datos.equals("Vacio"))
        		resultado = "Algun campo esta vacio..."; // PARA QUE EL USUARIO SEPA QUE ALGUN DATO FALTA
        		else 
        			if(datos.equals("NO_NUMERICO"))
        				taDatos.setText("Saldo debe ser numerico");
        				else
        				{
        						resultado = bancoad.capturar(datos);
					        	//3.-Desplegar el resultado de la transaccion
					        	taDatos.setText(resultado);
        				}
        		
        	
        	
            //taDatos.setText("Capturar Datos...");
        }
        
        if(e.getSource() == bConsultar)
        	
        {
        	datos = bancoad.consultar();
        	
            taDatos.setText(datos);
        }
        
        if(e.getSource() == bConsultarTipo)
        {
        	
            String mar = tfTipo.getText();
            datos = bancoad.consultarMarca(mar);
            taDatos.setText(datos);
            String auto = tfClave.getText();
            datos = bancoad.consultarClave(auto);
            
        }
        
        
        
          if(e.getSource() == bConsultarNo)
        {
           
            String auto = tfClave.getText();
            datos = bancoad.consultarClave(auto);
            
            if(datos.equals("NOT_FOUND"))
            	{
            	taDatos.setText("No se localizo la cuenta: "+auto);
            }
            else{
             taDatos.setText(datos);
            desplegarDatos(datos);
            }
        }
        
        
        
/*(e.getSource() == bCotizar)
        {
        	datos = obtenerDatos();
        	
        	if(datos.equals("Vacio"))
        	{
        		taDatos.setText("Algun campo esta vacio...");
        	}
        	else 
        		if(datos.equals("NO_NUMERICO"))
        		{
        			taDatos.setText("Precio debe ser numerico....");
        		}
        		
        		else
        		{
        	
        	
        try{
        	
        	
          String  strPlazo= JOptionPane.showInputDialog("Ingresa el numero de mese del plazo (12/24/48)");
          int plazo = Integer.parseInt(strPlazo);
          
          String cotizacion = bancoad.cotizarAuto(datos,plazo);
          taDatos.setText(cotizacion);
          }
          
          catch (NumberFormatException nfe)
          {
          	taDatos.setText("El plazo de pago debe ser numerico");
          }
          
          	}
          
          	
          */
        
        
        
        if(e.getSource() == bSalir)
        {
            System.exit(0);
        }
    }
    
    
    public static void main(String args[])
    {
        new BancoBGUI();
    }
}
