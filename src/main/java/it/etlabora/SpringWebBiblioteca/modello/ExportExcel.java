package it.etlabora.SpringWebBiblioteca.modello;

import com.aspose.cells.License;
import java.util.ArrayList;
import java.util.List;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

public class ExportExcel {
	License cellsLicense = new License(); 
	//cellsLicense.setLicense("Aspose.Cells.lic");
	// Initialize an array which will be filled using the sample data
    ArrayList<String> worldLargestCities = new ArrayList<String>();
 // Create a workbook class object to store exported data from a list
    Workbook wbLargestCities = new Workbook();
    
    public void licencia() {
    	//cellsLicense.setLicense("Aspose.Cells.lic");
    }
    
    public void createRow(List<Libro> listLibro) {
    worldLargestCities.add("Tokyo-Yokohama");
    worldLargestCities.add("Jakarta");
    worldLargestCities.add("Delhi, DL-UP-HR");
    worldLargestCities.add("Manila");
    worldLargestCities.add("Seoul-Incheon");
    worldLargestCities.add("Shanghai, SHG-JS-ZJ");
    worldLargestCities.add("Karachi");

}
    
    public void getWorksheet () {
    	// Get a reference to the first worksheet in the workbook where data is to be exported
        Worksheet wsLargestCities = wbLargestCities.getWorksheets().get(0);

        // Fill the data from the list into the worksheet starting from a specified cell
        try {
			wsLargestCities.getCells().importArrayList(worldLargestCities, 1, 1, true);
			 // Save the output Excel file containing the exported list
	        wbLargestCities.save("ListDataExportedToExcel.xlsx");
			//wbLargestCities.save("F:\\curriculo\\Portafolio\\Biblioteca\\ListDataExportedToExcel.xlsx");
	        //F:\curriculo\Portafolio\Biblioteca
	        //System.out.println("creato ListDataExportedToExcel.xlsx");
	        System.err.println("creato ListDataExportedToExcel.xlsx");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       
    }
    

}
