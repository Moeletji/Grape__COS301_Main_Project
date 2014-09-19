/*
 * This project is property of team Grape, COS 301 Department of Computer Science, University of Pretoria, 2014.
 * This code can be publicly used as long as it is sorced well and the authors are well stated.
 * Please note that the code may contain external code which is well sourced. Please do source that particular
 * code with the correct authors/owners.
 */

package financialmarketsimulator.marketData;

 import java.io.File;  
 import java.io.FileReader;  
 import java.io.FileWriter;  
 import java.io.IOException;  
 import java.util.Iterator;  
 import java.util.List;  
 import com.thoughtworks.xstream.XStream;  

 public final class XStreamTranslator {  
     
     private XStream xstream = null;  
     public XStreamTranslator(){  
         xstream = new XStream();  
         xstream.ignoreUnknownElements();  
}  
     /**
      * 
      * @param obj
      * @param fileName
     * @param fieldsToIgnore
      * @return
      * @throws IOException 
      */
     public boolean storeObject(Object obj, String fileName) throws IOException
     {
         if (obj != null || fileName.isEmpty())
         {
            //String temp = xstream.toXML(obj);
            String newFileName = "Persistent Storage\\Participants\\"+fileName+".xml";
            toXMLFile(obj, newFileName);
            return true;
         }
         
         return false;
     }
     /**  
      * Convert a any given Object to a XML String  
      * @param object  
      * @return  
      */  
     public String toXMLString(Object object) {  
         return xstream.toXML(object);   
     }  
     /**  
      * Convert given XML to an Object  
      * @param xml  
      * @return  
      */  
     public Object toObject(String xml) {  
         return (Object) xstream.fromXML(xml);  
     }  
     /**  
      * return this class instance  
      * @return  
      */  
     public static XStreamTranslator getInstance(){  
         return new XStreamTranslator();  
     }  
     /**  
      * convert to Object from given File   
      * @param xmlFile  
      * @return  
      * @throws IOException   
      */  
     public Object toObject(File xmlFile) throws IOException {  
         return xstream.fromXML(new FileReader(xmlFile));  
     }  
     /**  
      * create XML file from the given object with custom file name  
      * @param fileName   
      * @param file  
      * @throws IOException   
      */  
     public void toXMLFile(Object objTobeXMLTranslated, String fileName ) throws IOException {  
         FileWriter writer = new FileWriter(fileName);  
         xstream.toXML(objTobeXMLTranslated, writer);  
         writer.close();  
     }  
     public void toXMLFile(Object objTobeXMLTranslated, String fileName, List omitFieldsRegXList) throws IOException {  
         xstreamInitializeSettings(objTobeXMLTranslated, omitFieldsRegXList);  
         toXMLFile(objTobeXMLTranslated, fileName);      
     }      
     /**  
      * @  
      * @param objTobeXMLTranslated  
      */  
     public void xstreamInitializeSettings(Object objTobeXMLTranslated, List omitFieldsRegXList) {  
         if(omitFieldsRegXList != null && omitFieldsRegXList.size() > 0){  
             Iterator itr = omitFieldsRegXList.iterator();  
             while(itr.hasNext()){  
                 String omitEx = itr.next().toString();  
                 xstream.omitField(objTobeXMLTranslated.getClass(), omitEx);  
             }  
         }   
     }  
     /**  
      * create XML file from the given object, file name is generated automatically (class name)  
      * @param objTobeXMLTranslated  
      * @throws IOException  
      * @throws XStreamTranslateException   
      */  
     public void toXMLFile(Object objTobeXMLTranslated) throws IOException {  
         toXMLFile(objTobeXMLTranslated,objTobeXMLTranslated.getClass().getName()+".xml");  
     }  
 }

