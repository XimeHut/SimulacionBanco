#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char Datos[100];
char Cliente[120];

char NoCuenta[20];
char Nombre[20];
char TipoCuenta[20];
char SaldoCuenta[20];

char cliente [120];

FILE *Archivo;


int ObtenerValor(char str[]){
	int numero;
	
	printf ("%s",str);
	scanf ("%d",&numero);
	
	return numero;
}


int EncontrarNoCuenta(char NoCuen[]){
	char NoC[20];
	char Nom[20];
	char Tip[20];
	char Sal[20];
	
	int Encontrado = 0;
	char DatosCliente[80];
		
	Archivo = fopen("BancoBueno.txt", "r");
	
	while((fgets(DatosCliente,80,Archivo)>0) && (Encontrado == 0)){
		
		strcpy(Cliente,DatosCliente);
		strcpy(NoC,strtok(DatosCliente,"_"));
		strcpy(Nom,strtok(NULL,"_"));
		strcpy(Tip,strtok(NULL,"_"));
		strcpy(Sal,strtok(NULL,"\n"));
		
		if(strcmp(NoCuenta,NoC) == 0){
			Encontrado = 1;
			
			strcpy(NoCuenta,NoC);
			strcpy(Nombre,Nom);
			strcpy(TipoCuenta,Tip);
			strcpy(SaldoCuenta,Sal);
		}
	}
	fclose(Archivo);
	return Encontrado;
}

void ObtenerDatos(char NoCuen[]){
		
	strcpy(NoCuenta,NoCuen);
	    
    printf("\nNOMBRE: ");
    gets(Nombre);
    
    printf("\n\n [INVERSION, AHORRO, CREDITO, HIPOTECA]\nTIPO DE CUENTA: ");
    gets(TipoCuenta);
    
    printf("\nSALDO INICIAL DE LA CUENTA: ");
    gets(SaldoCuenta);
    
    strcpy(Datos,"");
	strcat(Datos,NoCuenta);
	strcat(Datos,"_");
	strcat(Datos,Nombre);
	strcat(Datos,"_");
	strcat(Datos,TipoCuenta);
	strcat(Datos,"_");
	strcat(Datos,SaldoCuenta);
    strcat(Datos,"\n");
}

void CapturaFinal(){
	Archivo = fopen("BancoBueno.txt","a");
	
	fputs(Datos,Archivo);
	
	fclose(Archivo);
}

void Capturar(){
	char NoCuen[20];
	
	printf("\nNUMERO DE CUENTA: ");
	gets(NoCuen);
	gets(NoCuen);
	
	if(EncontrarNoCuenta(NoCuen) == 0){
		ObtenerDatos(NoCuen);
		CapturaFinal();
		printf("\n%s\n",Datos);
		strcpy(Datos,"");
	}
	else{
		system("cls");
		printf ("\nEL NUMERO DE CUENTA %s YA EXISTE\n\n",NoCuenta);
		strcpy(Datos,"");
	}
}

void ConsultaGeneral(){
	char NoC[20];
	char Nom[20];
	char Tip[20];
	char Sal[20];
    
	Archivo = fopen("BancoBueno.txt", "r");
    
	while(fgets(Datos,80,Archivo)>0){
		strcpy(NoC,strtok(Datos,"_"));
    	strcpy(Nom,strtok(NULL,"_"));
    	strcpy(Tip,strtok(NULL,"_"));
    	strcpy(Sal,strtok(NULL,"\n"));
    	
    	printf ("\n%s		%s		%s		%s\n",NoC,Nom,Tip,Sal);
	}
	printf ("\n");
	
	fclose(Archivo); 
}

void ConsultarPorNoCuenta(){
	char NoCuen[20];
		
	printf ("\nNUMERO DE CUENTA: ");
	gets(NoCuenta);
	gets(NoCuenta);
	
	if(EncontrarNoCuenta(NoCuen) == 0){
		printf("\nEL NUMERO DE CUENTA INTRODUCIDO NO SE ENCUENTRA.");
	}
	else{
		printf("\n%s		%s		%s		%s\n",NoCuenta,Nombre,TipoCuenta,SaldoCuenta);
	}
	printf ("\n");
}

void ConsultarPorTipoCuenta(){
	char NoC[20];
	char Nom[20];
	char Tip[20];
	char Sal[20];
	
	int cont = 0;
    	
	Archivo = fopen("BancoBueno.txt", "r");
		
	printf ("\n [INVERSION, AHORRO, CREDITO, HIPOTECA]\nTIPO DE CUENTA: ");
	gets(TipoCuenta);
	gets(TipoCuenta);
    
    while(fgets(Datos,80,Archivo)>0){
    	
    	strcpy(NoC,strtok(Datos,"_"));
    	strcpy(Nom,strtok(NULL,"_"));
    	strcpy(Tip,strtok(NULL,"_"));
    	strcpy(Sal,strtok(NULL,"\n"));
    	
    	if(strcmp(TipoCuenta,Tip) == 0){
    		printf ("\n%s		%s		%s		%s\n",NoC,Nom,Tip,Sal);
    		cont = 1;
		}
	}
	
	if(cont == 0){
    	printf ("\nEL TIPO DE CUENTA INTRODUCIDO NO SE ENCUENTRA, POSIBLE ERROR DE SINTAXIS.\n");
	}
	printf ("\n");
	
    fclose(Archivo);
}

void ConsultaD(){
    // 1. Abrir el archivo para leer los datos
    Archivo = fopen("BancoBueno.txt","r");
    
    // 2. Leer datos del archivo
    while(fgets(Datos,80,Archivo) > 0)
        printf("%s",Datos);
    
    // 3. Cerrar el archivo
    fclose(Archivo);
    
    system("pause");
    system("cls");
}


void ConsultarDatos(){
	int opcion;
	
	system("cls");
	printf ("CONSULTA\n");
	printf ("[1] CONSULTA GENERAL\n");
	printf ("[2] CONSULTA POR NUMERO DE CUENTA\n");
	printf ("[3] CONSULTA POR TIPO DE CUENTA\n");
	
	printf ("OPCION: ");
	scanf ("%d",&opcion);
	
	switch (opcion){
		case 1:
			ConsultaGeneral();
			break;
		case 2:
			ConsultarPorNoCuenta();
			break;
		case 3:
			ConsultarPorTipoCuenta();
			break;
		default:
			system ("cls");
			printf("\n FAVOR DE INTRODUCIR UN NUMERO VALIDO [1,2,3].\n\n");
			break;
	}
}

float Exponencial(float b, int x){
	int i;
	float e;
	
	e = 1;
	
	for (i = 1; i <= x; i++){
		e = e * b;
	}
	return e;
}

void DesplegarTablaSaldos(int Cantidad, float Interes, int Meses){
    int i;
	float aux,exp,aux2;
	
	i = 1;
	
	while (i <= Meses){
		exp = (1+Interes);
		aux = Exponencial(exp,i);
		aux2 = Cantidad*aux;
		printf ("%d			%.2f\n",i,aux2);
		i++;
	}
}

void CalcularSaldos(int Cantidad, int Meses){
	float Interes;
	
	if (Cantidad >= 1000 && Cantidad < 10000){
		Interes = 0.05;
		printf ("\nPorcentaje de Interes: 5");
	}
	if (Cantidad >= 10000 && Cantidad < 20000){
		Interes = 0.1;
		printf ("\nPorcentaje de Interes: 10");
	}
	if (Cantidad >= 20000){
		Interes = 0.15;
		printf ("\nPorcentaje de Interes: 15");
	}
	
	printf ("\n\n Tabla de Saldo por mes \nMes:			Saldo:\n");
	
	DesplegarTablaSaldos (Cantidad,Interes,Meses);
}

int Inversion(int Cantidad){
    
	int Meses;
	
	if (Cantidad < 1000){
		printf("\nUN MINIMO DE $1000 ES REQUERIDO PARA INVERTIR, LA CUENTA:\n%s		%s		%s		%s\nNO TIENE SALDO SUFICIENTE\n\n",NoCuenta,Nombre,TipoCuenta,SaldoCuenta);
	}
	else{
		Meses = ObtenerValor ("\n INTRODUZCA MESES A CALCULAR: ");
		CalcularSaldos(Cantidad, Meses);
	}    
}

void ProyeccionSaldo(){
	char NoCuen[20];
	int Meses = 0;
	int cont = 0;
		
	printf ("\nNO. CUENTA PARA PROYECCION: ");
	gets(NoCuenta);
	gets(NoCuenta);
	
	if(EncontrarNoCuenta(NoCuen) == 0){
		printf("\nEL NUMERO DE CUENTA INTRODUCIDO NO SE ENCUENTRA.\n");
	}
	else{
		printf("PRONOSTICO DE CUENTA: %s		%s		%s		%s\n",NoCuenta,Nombre,TipoCuenta,SaldoCuenta);
		if(strcmp(TipoCuenta,"INVERSION") == 0){
			cont = 1;
			int Cantidad = atof(SaldoCuenta);
			Inversion(Cantidad);
		}
		if (cont == 0){
			printf("\n SU CUENTA NO ES DE INVERSION , NO SE PUEDE HACER EL PRONOSTICO");
		}
	}
}

void capturarDeposito(char cliente[]){
	
	Archivo = fopen("Depositos.txt","a");
	
	fputs(cliente,Archivo);
	
	fclose(Archivo);
}


void Deposito(){
	
	int cantidadDeposito;
	int ncantidad;
	char strDeposito [10];
	char SaldoTotal [10];
	char dep[120];
	int cont=0;
	
	printf ("\nNO.CUENTA PARA EL  DEPOSITO: ");
	gets(NoCuenta);
	gets(NoCuenta);
	
	if(EncontrarNoCuenta(NoCuenta) == 0){
		printf("\nEL NUMERO DE CUENTA INTRODUCIDO NO SE ENCUENTRA.\n");
	}
	else{
		printf("CANTIDAD A DEPOSITAR: ");
		scanf("%d", &cantidadDeposito);
		
		if(strcmp(TipoCuenta, "INVERSION") == 0 || strcmp(TipoCuenta, "AHORRO") == 0){
			
			ncantidad = atoi(SaldoCuenta);
			ncantidad = ncantidad + cantidadDeposito;
			
			sprintf(strDeposito, " DEP:%d", cantidadDeposito);
			sprintf(SaldoTotal, "TOT:%d", ncantidad); 
			
			strcpy(dep, strtok(Cliente, "\n"));
			strcat(dep, "_");
			strcat(dep, strDeposito);
			strcat(dep, "_");
			strcat(dep, SaldoTotal);
			strcat(dep, "\n");	
			
			printf("CONFIRMACION DE DEPOSITO: \n%s", dep);
			cont++;

		}
		
		if(cont==0)
		{
			
			ncantidad = atoi(SaldoCuenta);
			ncantidad = ncantidad - cantidadDeposito;
			
			sprintf(strDeposito, "%d", cantidadDeposito);
			sprintf(SaldoTotal, "%d", ncantidad); 
			
			strcpy(dep, strtok(Cliente, "\n"));
			strcat(dep, "_");
			strcat(dep, strDeposito);
			strcat(dep, "_");
			strcat(dep, SaldoTotal);
			strcat(dep, "\n");	
			
			printf("DATOS DEPOSITO: \n %s", dep);
			
		}
	}
	capturarDeposito(dep);
}

void capturarRetiro(char cliente[]){
	
	Archivo = fopen("Retiros.txt","a");
	
	fputs(cliente,Archivo);
	
	fclose(Archivo);
	
}

void Retiro(){
	
	int cantidadRetiro;
	int ncantidad;
	char strRetiro [10];
	char SaldoTotal [10];
	char dep[120];
	int cont=0;
	
	printf ("\nNo CUENTA PARA EL RETIRO: ");
	gets(NoCuenta);
	gets(NoCuenta);
	
	if(EncontrarNoCuenta(NoCuenta) == 0){
		printf("\nEL NUMERO DE CUENTA INTRODUCIDO NO SE ENCUENTRA.\n");
	}
	
	else{
	
		if(strcmp(TipoCuenta, "INVERSION") == 0 || strcmp(TipoCuenta, "AHORRO") == 0){
			
			printf("CANTIDAD A RETIRAR: ");
			scanf("%d", &cantidadRetiro);
		
			
			ncantidad = atoi(SaldoCuenta);
			ncantidad = ncantidad - cantidadRetiro;
			
			sprintf(strRetiro, "%d", cantidadRetiro);
			sprintf(SaldoTotal, "%d", ncantidad); 
			
			strcpy(dep, strtok(Cliente, "\n"));
			strcat(dep, "_");
			strcat(dep, strRetiro);
			strcat(dep, "_");
			strcat(dep, SaldoTotal);
			strcat(dep, "\n");	
			
			printf("DATOS DEL RETIRO: \n %s", dep);
			cont++;

		}
		
		if(strcmp(TipoCuenta, "CREDITO") == 0){
			
			printf("CANTIDAD A RETIRAR: ");
			scanf("%d", &cantidadRetiro);
		
			
			ncantidad = atoi(SaldoCuenta);
			ncantidad = ncantidad + cantidadRetiro;
			
			sprintf(strRetiro, "%d", cantidadRetiro);
			sprintf(SaldoTotal, "%d", ncantidad); 
			
			strcpy(dep, strtok(Cliente, "\n"));
			strcat(dep, "_");
			strcat(dep, strRetiro);
			strcat(dep, "_");
			strcat(dep, SaldoTotal);
			strcat(dep, "\n");	
			
			printf("DATOS RETIRO: \n%s", dep);
			cont++;

		}
		
		if(cont==0)
		{
			
			printf("CUENTA ES DE HIPOTECA, SOLO SE HACEN RETIROS\n");
			
		}
	}
	capturarRetiro(dep);
	
	system("pause");
	system("cls");
}

void ConsultaR(){
	
	 // 1. Abrir el archivo para leer los datos
    Archivo = fopen("Retiros.txt","r");
    
    // 2. Leer datos del archivo
    while(fgets(Datos,80,Archivo) > 0)
        printf("%s",Datos);
    
    // 3. Cerrar el archivo
    fclose(Archivo);
    
    system("pause");
    system("cls");
	
}

void Opciones(){
	int opcion;
	
	do{
		printf("Aministracion de Clientes de un Banco \n");
		printf("[1] CAPTURAR DE  DATOS\n");
		printf("[2] OPCIONES DE CONSULTA DE DATOS\n");
		printf("[3] Clientes con Inversion-Proyecto Saldo\n");
		printf("[4] Deposito de Cuenta\n");
		printf("[5] Consulta de Depositos\n");
		printf("[6] Retiro de Cuentas\n");
		printf("[7] Consulta de Retiros\n");
		printf("[8] Exit\n");
		printf("Opcion: ");
		scanf("%d",&opcion);
			switch(opcion){
			case 1:	Capturar();break;			
			case 2:	ConsultarDatos();break;
			case 3: ProyeccionSaldo();break;
			case 4:	Deposito();break;
			case 5: ConsultaD();break;
			case 6: Retiro();break;
			case 7: ConsultaR();break;
			case 8:
			    system("cls");
				printf("\nSE HA SALIDO DEL PROGRAMA\n\n");
				exit(0);
				
		    default:
				system("cls");
				printf("\nFAVOR DE SELLECIONAR UN NUMERO VALIDO [1,2,3].\n\n");
				break;
			}
				    system("pause");
					system("cls");		
		   }while(opcion != 8);

}

int main(){
	Opciones();
}
