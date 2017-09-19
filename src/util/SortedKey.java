/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author servbeta11
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.TreeSet;
import java.util.Set;   

public class SortedKey {

    public void sortedProperties(Properties properties, File file, String comments) {
        Properties props = new Properties() {
            @Override
            public synchronized Enumeration<Object> keys() {
                return Collections.enumeration(
                          new TreeSet<Object>(super.keySet())
                 );
            }
        };

        props.putAll(properties);

        try {
            props.store(new FileOutputStream(file), comments);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sortedXml(Properties properties, File file, String comments, String encoding) {
        Properties props = new Properties() {
            @Override
            public Set<Object> keySet() {
                return Collections.unmodifiableSet(
                          new TreeSet<Object>(super.keySet())
                 );
            }
        };

        props.putAll(properties);

        try {
            props.storeToXML(new FileOutputStream(file), comments, encoding);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
