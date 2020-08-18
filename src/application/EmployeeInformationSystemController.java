package application;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class EmployeeInformationSystemController {
	
	 @FXML
	    private TextField textFieldID;

	    @FXML
	    private TextField textFieldName;

	    @FXML
	    private TextField textFieldSalary;

	    @FXML
	    private TextArea textAreaList;

	    @FXML
	    private DatePicker textFieldDate;

	    @FXML
	    private TextField textFieldSearch;

	    @FXML
	    private TextArea textAreaSearchResult;
    
  
	    public void initialize() {
	         

	    }
	    // ArrayList to stor the employees records
	    //ArrayList list = new ArrayList();
	    ArrayList IDs = new ArrayList();
	    ArrayList names = new ArrayList();
	    ArrayList dates = new ArrayList();
	    ArrayList salaries = new ArrayList();
	    
   // Add ActionEvent method
    @FXML
    void add(ActionEvent event)
    {
    	textAreaSearchResult.setText("");
    	try
    	{
    		if (validate ()) {
    		int ID = Integer.parseInt(textFieldID.getText());
    		
        	String name = textFieldName.getText().toString().toUpperCase();
        	
        	String joiningDate = textFieldDate.getValue().toString();
        	
        	double salary = Double.parseDouble(textFieldSalary.getText());
        	
        	
        		if (!IDs.contains( trimID( textFieldID.getText().toString() ) ) ) 
            	{
            		    int id = trimID( textFieldID.getText().toString() );
            			IDs.add(id);
                		names.add(name);
                		dates.add(joiningDate);
                		salaries.add(salary);
                		textAreaSearchResult.setText("Employee Added. ");
            		}
            	else
            	{
            		textAreaSearchResult.setText("ID is already used. ");
            	}
        	}
    	} catch (Exception e) {
    		e.getMessage();
    	}
    }
    
    // ActionEvent method to display the employee list in TextArea
    @FXML
    void display(ActionEvent event) {
    	    textAreaSearchResult.setText("");
    		textAreaList.setText("Employee ID          Employee Name                     Joining Date                   Salary");
        	textAreaList.appendText("\n");
    		for (int i=0; i<IDs.size(); i++ ) {
    			
    			textAreaList.appendText(IDs.get(i)+ "                        ");
    			textAreaList.appendText(names.get(i)+ "               ");
    			textAreaList.appendText(dates.get(i)+ "               ");
    			textAreaList.appendText(salaries.get(i).toString()+ "\n");
    		}
}    
   
    // Search method
    @FXML
    void search(ActionEvent event) {
    	try
    	{  	
    		boolean found = false;
    		//while(found) {
    			if (names.contains(textFieldSearch.getText().toString().toUpperCase()))
        		{
        			textAreaSearchResult.setText("Employee exists.");
        			found =true;
        			//break;
        		}
    			else 
					textAreaSearchResult.setText("Employee does not exist.");
    			
    			if (!names.contains(textFieldSearch.getText().toString().toUpperCase()))
        		{
    				if (IDs.contains(trimID( textFieldSearch.getText().toString() ) ) )
                		textAreaSearchResult.setText("Employee exists.");
    				else 
    					textAreaSearchResult.setText("Employee does not exist.");
        		}
    			
    	}
    	 catch (Exception e )
    	{
    		e.getMessage();
    	}
    }
    // a method to reset values 
    @FXML
    void clear(ActionEvent event) {
    	textFieldID.setText("");
    	textFieldName.setText("");
    	textFieldDate.setValue(null);
    	textFieldSalary.setText("");
    	textAreaSearchResult.setText("");
    	textAreaList.setText("");
    	textFieldSearch.setText("");
    	
    }
    
    // a method to read only 3-digit ID 
    public int trimID (String id)
    {
    	String newID=id;
    	if ( id.trim().length() > 3) {
    		 newID = id.trim().substring(0, 3);
    	}
    	return Integer.parseInt(newID);
    }
    
    
    public boolean validate ( ) {
		boolean valid = true;
		if (textFieldID.getText().equals("") )
		{
			textAreaSearchResult.appendText("Error: Enter Employee ID!\n");
			valid = false;
		}
		if (textFieldName.getText().equals("") )
		{
			textAreaSearchResult.appendText("Error: Enter Employee Name!\n");
			valid = false;
		}
		if (textFieldSalary.getText().equals("") )
		{
			textAreaSearchResult.appendText("Error: Enter Salary!\n");
			valid = false;
		}
		if (textFieldDate.getValue() == null)
		{
			textAreaSearchResult.appendText("Error: Enter Date!\n");
			valid = false;
		}
		
		return valid;
	}
}
