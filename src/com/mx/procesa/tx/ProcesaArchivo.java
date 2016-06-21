package com.mx.procesa.tx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ProcesaArchivo {
	static String ENCABEZADO = "NU_CTE ,BIN TDC,CD_EMAIL,ULT_4DIG,NB_CTE,NB_NOMBRE,Indicador Base ,CLAVE DE TEXTO U OFERTA,CLAVE DE ARTE O DISEÑO ,NODO,SEGMENTO,CAT_PROMO_1,TASA_POND_1,CUOTA_ANUAL_1,CAT_PROMO_2,TASA_POND_2,CUOTA_ANUAL_2,CAT_PROMO_3,TASA_POND_3,CUOTA_ANUAL_3,Folio consecutivo de Campaña,COSECHA,PRODUCTO ,GENERO ,IMPORTE OFERTADO EFI,IM PAGO MENSUAL ,IM PAGO MENSUAL POR CADA MIL,CELULA EFECTIVO,TASA MENSUAL EFI,TASA ANUAL EFI,PLAZO EN MESES EFI,IMPORTE OFERTADO CCI ó PPF,TASA MENSUAL CCI ó PPF,TASA ANUAL CCI ó PPF,PLAZO EN MESES CCI ó PPF,IM_PAGO_MENSUAL CCI  ó PPF_FIJO,IM_PAGO_MENSUAL CCI  ó PPF_ACUMULADO,IM PAGO MENSUAL POR CADA MIL CCI ó PPF,IMPORTE OFERTADO MSI,TASA MENSUAL MSI,TASA ANUAL MSI,PLAZO EN MESES MSI,IM_PAGO_MENSUAL MSI_FIJO,IM_PAGO_MENSUAL MSI_ACUMULADO,IM PAGO MENSUAL POR CADA MIL MSI,MTO_INT_BMER_BT,MTO_INT_OTRO_BT,MTO_AHORRO_BT,MTO_INT_BMER_CCI,MTO_INT_OTRO_CCI,MTO_AHORRO_CCI,FECHA DE CORTE,FECHA LIMITE DE PAGO,LINEA_PROP,LINEA_ANT,TASA_MENS_ACTUAL_PBPM,TASA_ANUAL_ACTUAL_PBPM,TASA_MENS_ENTRADA_PBPM,TASA_ANUAL_ENTRADA_PBPM,POR_REDUC_TASA,PRODUCTO_ORIGEN,NUEVO_PRODUCTO,POR_REEMBOLSO,MONTO_MAX_REEM,TASA_MENSUAL_SALDO1 ,TASA_ANUAL_SALDO1 ,TASA_MENSUAL_SALDO2,TASA_ANUAL_SALDO2,TASA_MENSUAL_SALDO3,TASA_ANUAL_SALDO3,SALDO REQUERIDO 1,SALDO REQUERIDO 2,SALDO REQUERIDO 3";
//	private static final String PATTERN_EMAIL = "[^A-Za-z0-9.@_-~#]";
	static public void procesaArchivo( List<String> listaArchivosStr, String path) {
		System.out.println("listaArchivosStr: "+ listaArchivosStr.toString());
		
		
		for(String fileName : listaArchivosStr)
		{
			generaArchivoCVS(fileName, path);
		}
		

	}
	
	public static Boolean generaArchivoCVS(String fileName, String path){
		// Open the file
				FileInputStream fstream;
				String res = fileName.substring(0, fileName.length()-3);
				String archivoParceado = path+res+"_RES.csv";
				FileWriter fw = null;
				List<String> listaPruebas = new ArrayList<String>();
				try {
					fstream = new FileInputStream(path + fileName);
					fw = new FileWriter(archivoParceado);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter salArch = new PrintWriter(bw);
					
					BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

					String strLine;
					int delimitadores[] = {0,12,18,98,102,162,182,188,208,228,238,248,254,260,275,281,287,302,308,314,329,339,349,369,375,390,405,420,427,433,439,445,460,466,472,478,493,508,523,538,544,550,556,571,586,601,616,631,646,661,676,691,696,701,711,721,736,742,757,763,769,784,799,805,820,826,832,838,844,850,856,871,886,901};

					int flag = 0;
					
					
					salArch.print(ENCABEZADO+"\n");
					// Read File Line By Line
					while ((strLine = br.readLine()) != null) {
						// Print the content on the console
//						String[] tokens = strLine.split(delims);
						String lineItems = "";
						
						for(int i =0 ; i< delimitadores.length-1 ;i++){
//							System.out.println(tokens[i]);
							String item = strLine.substring(delimitadores[i], delimitadores[i+1]);
							lineItems+=item+"\t";
						}
						salArch.print(lineItems+"\n");
						if(flag <= 3){
							flag++;
						} 
						
					}

					
					br.close();
					salArch.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				generaArchivoPrueba(fileName, path, listaPruebas);
				return null;
	}
	
	public static Boolean generaArchivoPrueba(String fileName, String path, List<String> lista){
		System.out.println("\n\nCreando arvhivo de prueba.... lista con " +lista.size() +" elemntos. Archivo: "+fileName);
		
		
		
		String archivoPba = path+fileName+"_PBA.csv";
		FileWriter fw = null;
		try {
			fw = new FileWriter(archivoPba);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salArch = new PrintWriter(bw);
			salArch.print(ENCABEZADO+"\n");
			
			for(String item: lista){
				//Sustituir email
				
				salArch.print(item+"\n");
//				System.out.println(item);
//				String[] listaIni = item.split("@");
//				
////				String item2 = item.replaceFirst(PATTERN_EMAIL, "ubaldocj@hotmai.com");
//				System.out.println(listaIni[0]);
//				System.out.println(listaIni[1]);
			}
			salArch.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
