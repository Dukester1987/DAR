/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Functions;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;
import static java.util.stream.Collectors.joining;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author ldulka
 */
public class readXMLFile {
    public static ArrayList<String> fileStructure;
    private static String currentDir = "";
    private static Stack<String> dirStructure;

    private static void checkDir(String currentDir) {
        File f = new File(currentDir);
        if(!f.exists()){
            f.mkdir();
        }
    }
    public String version;

    public readXMLFile() {
        fileStructure = new ArrayList<>();
        dirStructure = new Stack<>();
        //dirStructure.push("");        
    }
    
    
    public void readFile(String url){
        try {

//            File file = new File("changelog/version_manifest.xml");
            File file = new File(url);

            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                                 .newDocumentBuilder();

            Document doc = dBuilder.parse(file);

            version = doc.getDocumentElement().getAttribute("version");

            if (doc.hasChildNodes()) {

                printNote(readNodes(doc.getChildNodes()));
        }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    
    private static void printNote(ArrayList<String> nodes) {
        nodes.forEach(System.out::println);
    }

    public static ArrayList<String> readNodes(NodeList nodeList) {
        
        for (int count = 0; count < nodeList.getLength(); count++) {

            Node tempNode = nodeList.item(count);

            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                String path = "";
                boolean addDir = false;
                boolean addFile = false;
                // get node name and value
//                System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
//                System.out.println("Node Value =" + tempNode.getTextContent());
                if(tempNode.getNodeName().startsWith("dir")){     
                    addDir = true;
                } else if(tempNode.getNodeName().startsWith("file")){
                    addFile = true;
                }

                if (tempNode.hasAttributes()) {

                    // get attributes names and values
                    NamedNodeMap nodeMap = tempNode.getAttributes();
                    for (int i = 0; i < nodeMap.getLength(); i++) {
                            Node node = nodeMap.item(i);                            
                            if(addDir){
                                dirStructure.push(node.getNodeValue());
                                //System.out.println(dirStructure);                               
                                //System.out.println(node.getNodeValue());
                                currentDir += node.getNodeValue()+"/";
                                checkDir(currentDir);
                                addDir = false;
                            } else if(addFile){
                                if(!dirStructure.empty())
                                    currentDir = dirStructure.stream().collect(joining("/"))+"/";
                                path += currentDir+node.getNodeValue();
                                fileStructure.add(path);
                                addFile = false;
                            }
                            if(!path.isEmpty()){
                                //System.out.println(path);
                            }
//                            System.out.println("attr name : " + node.getNodeName());
//                            System.out.println("attr value : " + node.getNodeValue());

                    }

                }

                if (tempNode.hasChildNodes()) {

                    // loop again if has child nodes
                    readNodes(tempNode.getChildNodes());

                }
                    
//                System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
                    if(tempNode.getNodeName().startsWith("dir")){
                        currentDir = "";
                        dirStructure.pop();
                    }
                

            }

        } 
        return fileStructure;
    }
}