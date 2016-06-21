package com.mx.procesa.tx;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PrincipalTXT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Inciando proceso de datos...");
		// Aquí la carpeta que queremos explorar
//      String path = "C:/"; 
		
      String path = ""; 
      
      
		if(args.length>0){
			System.out.println("Directorio: " + args[0]);
			path = args[0];
		} else{
			 path = "/Archivosmail/"; 
		}
		

        String files;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles(); 
        
        List<String> listaArchivosStr = new ArrayList<String>();

        for (int i = 0; i < listOfFiles.length; i++) 
        {

            if (listOfFiles[i].isFile()) 
            {
                files = listOfFiles[i].getName();
                if (files.endsWith(".txt") || files.endsWith(".TXT"))
                {
                	listaArchivosStr.add(files);
                    System.out.println(files);
                }
            }
        }
        System.out.println("Fin");
		
		ProcesaArchivo.procesaArchivo(listaArchivosStr, path);
	}

}
