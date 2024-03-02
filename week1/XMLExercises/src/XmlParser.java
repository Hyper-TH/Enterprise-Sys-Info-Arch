import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class XmlParser {

	public static void main(String[] args) {
		
		File file = new File("order.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		float totalVal = 0;
		float price = 0;
		int quantity = 0;
		
		try {
			
			/// Load the XML file...
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			System.out.println("Document root element is: " + doc.getDocumentElement().getNodeName());
			
			/// Get the list of XML nodes from the root element...
			NodeList rootChildren = doc.getDocumentElement().getChildNodes();
			
			/// Loop through the list of nodes under the root element...
			for (int i=0; i<rootChildren.getLength(); i++) { 				  				
				
				
				/// If there is whitespace between the elements in the file,
				/// it will be manifested as a "text" node, so filter these out...
				if (rootChildren.item(i).getNodeType() == Node.ELEMENT_NODE)
					System.out.print("\n" + rootChildren.item(i).getNodeName() + ": ");
				
				/// Every element will have at least one child node (the text value)... 
				NodeList subChildren = rootChildren.item(i).getChildNodes();				
								  
				
				/// Loop through the child nodes...
				for (int j=0; j<subChildren.getLength(); j++) {

					Node currentNode = subChildren.item(j);					
					
					// If we have more child elements, parse them...
					if (currentNode.hasChildNodes()){
											
						/// Print the element name and the text value - text is in a child text node...
						System.out.print(currentNode.getNodeName() + ": " + currentNode.getFirstChild().getNodeValue());						
						
						// If current node is a quantity
                        if (currentNode.getNodeName().equals("Quantity")) {

                            // Get the next sibling node (Price)
                            Node nextSibling = currentNode.getNextSibling();

                            // Loop to find the next sibling element (skip any text nodes or other non-element nodes)
                            while (nextSibling != null && nextSibling.getNodeType() != Node.ELEMENT_NODE) {
                                nextSibling = nextSibling.getNextSibling();
                            }
                            
                            // Check if the next sibling is an element node and its name is "Price"
                            if (nextSibling.getNodeName().equals("Price")) {
                            	
                            	// If quantity > 1
                            	if (Integer.parseInt(currentNode.getFirstChild().getNodeValue()) > 1) {
                            		
                            		 // Multiply the quantity by the price
                                    quantity = Integer.parseInt(currentNode.getFirstChild().getNodeValue());
                                    price = Float.parseFloat(nextSibling.getFirstChild().getNodeValue());

                                    totalVal += quantity * price;
                                    
                            	} else {
                            		price = Float.parseFloat(nextSibling.getFirstChild().getNodeValue());

                                    totalVal += price;
                            	}    
                            }
                        } 
					}
					/// If there are no child elements, this node is the text value...
					else
						System.out.print(currentNode.getNodeValue());	
				  }
				}						
						
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		
		System.out.println(totalVal);
	}

}